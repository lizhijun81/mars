package 算法.排序;


import java.util.Arrays;

/**
 * 选择排序：每次选择最小、大的放到对应位置；其效率较差：O(n^2)
 */
public class 选择排序 {

    public static void main(String[] args) {
        int arrs[] = {8, 10, 7, 5, 20};

        for (int i = 0; i < arrs.length; i++) {
            int min = arrs[i];
            int minIndex = i;
            for (int j = i + 1; j < arrs.length; j++) {// 每次查找剩余数组中的最小值
                if (arrs[j] > min) {
                    continue;
                }
                min = arrs[j];
                minIndex = j;
            }
            arrs[minIndex] = arrs[i];// 交换每次查找的最小值
            arrs[i] = min;

            print(arrs);
        }
    }

    public static void print(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(arrs[i] + ",");
        }
        System.out.println("");
    }
}
