package algorithm;

import java.util.Arrays;
import java.util.Collections;

/**
 * 归并排序
 * 参考链接：https://www.cnblogs.com/chengxiao/p/6194356.html
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1,191};//{8, 4, 5, 7, 1, 3, 6, 2};//{11, 2, 55, 8, 6,13,4,3,0};
        sort(nums);
        Collections.singletonList(nums).forEach(num -> System.out.print(Arrays.toString(num) + " "));
    }

    static void sort(int[] nums) {
        // 为了避免在递归过程中频繁申请内存资源
        int[] tmpNums = new int[nums.length];
        doSort(nums, 0, nums.length - 1, tmpNums);
    }

    static void doSort(int[] nums, int low, int high, int[] tmpNums) {
        // 这里差值有0、1两种可能，
        if (high - low <= 1) {
            if (nums[low] > nums[high]) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
            }
            return;
        }

        int mid = (low + high) / 2;
        doSort(nums, low, mid, tmpNums);
        doSort(nums, mid + 1, high, tmpNums);
        merge(nums, low, mid, high, tmpNums);
    }

    static void merge(int[] nums, int low, int mid, int high, int[] tmpNums) {
        int i = mid + 1;
        int j = low;
        int k = 0;
        while (k < tmpNums.length && j <= mid && i <= high) {
            if (nums[j] > nums[i]) {
                tmpNums[k++] = nums[i++];
            } else {
                tmpNums[k++] = nums[j++];
            }
        }
        while (j <= mid) {
            tmpNums[k++] = nums[j++];
        }
        while (i <= high) {
            tmpNums[k++] = nums[i++];
        }
        // 需要将临时数组中的变量复制到原来的数组中
        for (k = 0, j = low; j <= high; k++, j++) {
            nums[j] = tmpNums[k];
        }
    }
}
