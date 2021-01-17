package algorithm;

import java.util.Arrays;
import java.util.Collections;

/**
 * 归并排序
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int[] nums = {8, 4, 5, 7, 1, 3, 6, 2};//{11, 2, 55, 8, 6,13,4,3,0};
        sort(nums, 0, nums.length - 1);
        Collections.singletonList(nums).forEach(num -> System.out.print(Arrays.toString(num) + " "));
    }

    static void sort(int[] nums, int low, int high) {
        if (high - low <= 1) {
            if (nums[low] > nums[high]) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
            }
            return;
        }

        int mid = (low + high) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    static void merge(int[] nums, int low, int mid, int high) {
        int[] tmpNums = new int[high - low + 1];
        int j = mid + 1;
        int i = 0;
        int k = low;
        for (; i < tmpNums.length && k <= mid && j <= high; i++) {
            if (nums[k] > nums[j]) {
                tmpNums[i] = nums[j++];
            } else {
                tmpNums[i] = nums[k++];
            }
        }
        while (k <= mid) {
            tmpNums[i++] = nums[k++];
        }
        while (j <= high) {
            tmpNums[i++] = nums[j++];
        }
        // 需要将临时数组中的变量复制到原来的数组中
        for (i = 0, k = low; k <= high; i++, k++) {
            nums[k] = tmpNums[i];
        }
    }
}
