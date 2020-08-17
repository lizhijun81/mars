// 二分查找

public class BinarySearch {
    public int find(int[] array, int target) {
        if(array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (target > array[mid]) {// r
                left = mid + 1;
            } else if (target < array[mid]) {// l
                right = mid - 1;
            }
        }
        return -1;
    }
}
