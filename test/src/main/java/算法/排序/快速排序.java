package 算法.排序;

/**
 * 快速排序是一种分而治之的算法，每次基于基值将数组按大小分为左右两个子数组，然后再从两个子数组中取得分别取基值，再按大小分别分成两个子数组
 */
public class 快速排序 {

    public static void main(String[] args) {
        int arrs[] = {3, 5, 2, 1, 4, 2};
        quickSort(arrs, 0 , arrs.length - 1);
    }

    public static void quickSort(int[] arrs, int left, int right) {
        if (left >= right) {// 左边界大于有边界， 或者左边界和有边界指向同一个值，直接退出
            return;
        }

        int base = arrs[left]; // 基值
        int l = left; // 左边界
        int r = right; // 右边界
        while (l < r) {// 确定基值所在位置
            while (l < r && arrs[r] > base) {// 从右往左查，找到比基值小的位置
                r--;
            }
            if (l < r) {// 右边比基值大的往左边放
                arrs[l] = arrs[r];
                l++;
            }

            while (l < r && arrs[l] < base) {// 从左往右查，找到比基值大的位置
                l++;
            }
            if (l < r) {// 左边比基值大的往右边放
                arrs[r] = arrs[l];
                r--;
            }
        }

        // 结束时找到基值所在数组中的位置
        arrs[l] = base;
        print(arrs);

        // 左子数组排序
        quickSort(arrs, left, l - 1);
        // 右子树组排序
        quickSort(arrs, l + 1, right);
    }

    public static void print(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(arrs[i] + ",");
        }
        System.out.println("");
    }

}
