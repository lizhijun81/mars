package test;

import java.util.Arrays;
import java.util.List;

import static io.netty.util.internal.ObjectUtil.checkPositiveOrZero;

public class FullBTree {

    private static final int INTEGER_SIZE_MINUS_ONE = Integer.SIZE - 1;

    static int maxOrder = 11;
    static byte[] memoryMap = new byte[4096];
    static byte[] depthMap = new byte[memoryMap.length];// depthMap = new byte[4096]

    private byte unusable = 12;

    static int pageShifts = 13;

    ///////////////////////


    static final long[] bitmap = new long[8];// 位图 标记 那些page中的段已经被使用
    static int bitmapLength = 8;// 位图的长度
    static int nextAvail = 0;
    static int maxNumElems;

    static {
        int memoryMapIndex = 1;
        for (int d = 0; d <= maxOrder; ++ d) {
            int depth = 1 << d;
            for (int p = 0; p < depth; ++ p) {
                // in each level traverse left to right and set value to the depth of subtree
                memoryMap[memoryMapIndex] = (byte) d;
                depthMap[memoryMapIndex] = (byte) d;
                memoryMapIndex ++;
            }
        }


        ///////////
        maxNumElems = (8 * 1024) / 32;
        bitmapLength = maxNumElems >>> 6;// 一个long类型是 64 位；
        if ((FullBTree.maxNumElems & 63) != 0) {// 最少保证有一个bitmap进行标记，page中的段的使用
            bitmapLength ++;
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(memoryMap));
//        System.out.println(Arrays.toString(depthMap));

        FullBTree fullBTree = new FullBTree();
        // poolChunk 中 分配内存小于PageSize的情况
        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));
//        System.out.println(fullBTree.allocateNode(11));

        // pageSize 中 段的分配 分配
//        fullBTree.allocate();
//        fullBTree.allocate();

        // poolChunk 中 分配内存大于PageSize的情况

        System.out.println(fullBTree.normalizeCapacity(2 * 8192));;
        System.out.println(fullBTree.normalizeCapacity(8192));
        System.out.println(fullBTree.allocateRun(fullBTree.normalizeCapacity(2 * 8192)));
        System.out.println(fullBTree.allocateRun(fullBTree.normalizeCapacity(8192)));
    }

    void allocate() {
        final int bitmapIdx = getNextAvail();
        int q = bitmapIdx >>> 6;
        int r = bitmapIdx & 63;
        assert (bitmap[q] >>> r & 1) == 0;
        bitmap[q] |= 1L << r;

        System.out.println(Arrays.toString(bitmap));
    }

    private int getNextAvail() {
        int nextAvail = FullBTree.nextAvail;// 0
        if (nextAvail >= 0) {
            FullBTree.nextAvail = -1;
            return nextAvail;
        }
        return findNextAvail();
    }

    private int findNextAvail() {
        final long[] bitmap = FullBTree.bitmap;
        final int bitmapLength = FullBTree.bitmapLength;
        for (int i = 0; i < bitmapLength; i ++) {
            long bits = bitmap[i];
            if (~bits != 0) {
                return findNextAvail0(i, bits);
            }
        }
        return -1;
    }

    private int findNextAvail0(int i, long bits) {
        final int maxNumElems = FullBTree.maxNumElems;
        final int baseVal = i << 6;

        for (int j = 0; j < 64; j ++) {// j 标识 一个long 里边的第几位
            if ((bits & 1) == 0) {// 当前位置的标识为零，表示对应page的段没有别分配
                int val = baseVal | j; // n[bitmap的长度] * 64 + j = 第几位是可用的
                if (val < maxNumElems) {
                    return val;
                } else {
                    break;
                }
            }
            bits >>>= 1;// 不满足当前j位置的标志为零，则右移一位
        }
        return -1;
    }


    /////////////////////////////////////////////////////////////////////////////////////

    public long allocateRun(int normCapacity) {
        int d = maxOrder - (log2(normCapacity) - pageShifts);
        int id = allocateNode(d);
        if (id < 0) {
            return id;
        }
        return id;
    }

    private static int log2(int val) {
        // compute the (0-based, with lsb = 0) position of highest set bit i.e, log2
        return INTEGER_SIZE_MINUS_ONE - Integer.numberOfLeadingZeros(val);
    }

    // 优先分配左子树
    public int allocateNode(int d) {
        int id = 1;
        byte val = value(id);
        if (val > d) { // unusable
            return -1;
        }
        while (val < d) { // id & initial == 1 << d for all ids at depth d, for < d it is 0
            id <<= 1;
            val = value(id);
            if (val > d) {
                id ^= 1;
                val = value(id);
            }
        }
        setValue(id, unusable);
        updateParentsAlloc(id);
        return id;
    }

    private byte value(int id) {
        return memoryMap[id];
    }

    // 左右子节点分配都会导致父节点的depth+1；
    // 当左右子树都完全分配后父节点的depth=maxOrder+1，即父节点下的所有叶子节点都被分配
    private void updateParentsAlloc(int id) {
        while (id > 1) {
            int parentId = id >>> 1;
            byte val1 = value(id);
            byte val2 = value(id ^ 1);
            byte val = val1 < val2 ? val1 : val2;
            setValue(parentId, val);
            id = parentId;
        }
    }

    private void setValue(int id, byte val) {
        memoryMap[id] = val;
    }


    public int normalizeCapacity(int reqCapacity) {

        int normalizedCapacity = reqCapacity;
        normalizedCapacity --;
        normalizedCapacity |= normalizedCapacity >>>  1;
        normalizedCapacity |= normalizedCapacity >>>  2;
        normalizedCapacity |= normalizedCapacity >>>  4;
        normalizedCapacity |= normalizedCapacity >>>  8;
        normalizedCapacity |= normalizedCapacity >>> 16;
        normalizedCapacity ++;

        if (normalizedCapacity < 0) {
            normalizedCapacity >>>= 1;
        }

        return normalizedCapacity;

    }
}


// 0000 0000 0000 0000 0001 1111 1111 1111
// 1111 1111 1111 1111 1110 0000 0000 0000



// 0000 0000 0000 0000 0000 0000 0000 1111
//                                  1 0001

// 1111 1111 1111 1111 1111 1111 1111 0000
//                                  1 0001  // 17
// 0000 0000 0000 0000 0000 0000 0010 0000  // 20=32

/*

PoolSubpage<T>[] tinySubpagePools = new PoolSubpage[32];
for (int i = 0; i < tinySubpagePools.length; i ++) {
    tinySubpagePools[i] = newSubpagePoolHead(8K);
}
private PoolSubpage<T> newSubpagePoolHead(int pageSize) {
    PoolSubpage<T> head = new PoolSubpage<T>(8K);
    head.prev = head;
    head.next = head;
    return head;
}

    tinySubpagePools 的 结构
    0
    1
    2
    3
    4
 */
//
//
//
//
//
//
//


//                             256
//            512                             513
//    1024             1025            1026            1027
// 2048(12)    2049(11)    2050(11)    2051(11)    2052(11)    2053(11)    2054(11)    2055(11)


// 8192 = 2^13 -> 8192 >>> 10 -> 2^3 = 8;  8 long -> 8*2^64

// 8 * 2^10  ->


// 100 -> 112
//