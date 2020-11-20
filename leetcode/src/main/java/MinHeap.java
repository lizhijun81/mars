

/*
 * 最小堆
 */

import java.util.Arrays;
import java.util.Random;

public class MinHeap {

    private int[] items;

    private int size;

    public MinHeap(int cap) {
        this.items = new int[cap];
        this.size = -1;
    }

    public void offer(int e) {
        if (size >= items.length -1) {
            System.out.println("已满");
        }

        this.size++;

        items[this.size] = e;
        up(this.size);
    }

    private void up(int index) {
        int pIndex = (index - 1) / 2;
        if (pIndex < 0) {
            return;
        }

        if (items[pIndex] <= items[index]) {
            return;
        }

        if (items[pIndex] > items[index]) {
            int temp = items[index];
            items[index] = items[pIndex];
            items[pIndex] = temp;
        }
        up(pIndex);
    }

    public int poll() {
        if (size < 0) {
            return -1;
        }

        int result = this.items[0];
        this.items[0] = this.items[this.size];
        this.size--;
        down(0);
        return result;
    }

    private void down(int index) {
        if (size < 0) {
            return;
        }

        int c = this.items[index];
        int lcIndex = index * 2 + 1;
        if (lcIndex > size) {
            return;
        }

        int lc = this.items[lcIndex];

        int rcIndex = index * 2 + 2;
        if (rcIndex <= size) {// 存在右节点
            int rc = this.items[rcIndex];
            if (lc > rc) {// 左节点大于右节点，找到最小的节点；
                lc = rc;
                lcIndex = rcIndex;
            }
        }

        if (c <= lc) {// 当前节点小于最小的节点则不替换
            return;
        }

        this.items[index] = lc;
        this.items[lcIndex] = c;
        down(lcIndex);
    }

    public boolean offer_poll(int e) {
        if (size < this.items.length - 1) {// 小于等于数组长度，则直接添加到数组
            this.offer(e);
            return true;
        }

        int result = this.items[0];// 大于数组长度
        if (result >= e) {// 如果堆顶的元素大于添加的元素，则直接返回；否则将堆顶的元素替换成插入的元素后，对插入的元素下沉
            return false;
        }

        this.items[0] = e;
        down(0);
        return true;
    }

    public int[] getItems() {
        return items;
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);

        int offers_length = 10;

        Random random = new Random();
        int[] offers = new int[offers_length];
        for (int i = 0; i < offers_length; i++) {
            offers[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(offers));

        for (int i = 0; i < offers_length; i++) {
            heap.offer_poll(offers[i]);
        }
        System.out.println(Arrays.toString(heap.getItems()));

//        Arrays.sort(offers);
//        System.out.println(Arrays.toString(offers));
//
//        for (int i = 0; i < offers.length; i++) {
//            System.out.println(heap.poll());
//            System.out.println(Arrays.toString(heap.getItems()));
//        }

    }
}

// 4, 6, 18, 22, 22, 26, 27, 28, 33, 35, 39, 57, 68, 77, 81, 87, 87, 91, 92, 94
//                                       39, 68, 57, 87, 81, 91, 77, 94, 92, 87

//         11,
//     45,     14,
//   58, 76, 92, 92,
// 79, 72, 98

//          6,
//      12,     56,
//    12,  53, 63, 81,
//  91, 39, 57