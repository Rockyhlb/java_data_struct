
import java.util.Stack;

/**
 * @BelongsProject: test-20240301
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/3/1 8:42
 * @Description: TODO
 * @Author: code_hlb
 */
public class SortDemo {
    // 快速排序
    public void quickSort(int[] nums) {
        quick(nums,0,nums.length-1);
    }
    private void quick(int[] nums,int start,int end) {
        if (start >= end) {
            return;
        }
        // 1、采用三数取中法防止歪脖子树
        int mid = midOfThree(nums,start,end);
        swap(nums,start,mid);
        // 2、由于树的大部分节点都集中在最下面几层，因此我们可以在此时进行插入排序，从而减少递归次数
        if (end - start + 1 <= 15) {
            insertSortOfRange(nums,start,end);
            return;
        }
        int pivot = partition1(nums,start,end);
        quick(nums,start,pivot-1);
        quick(nums,pivot+1,end);
    }
    // 非递归实现快排，最常用的方式
    public void quickSortNor(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        int end = nums.length - 1;

        int pivot = partition(nums, start,end);
        // 当前基准左边还有元素时
        if (pivot - 1 > start) {
            // 下次取出作为 left
            stack.push(start);
            // 下次取出作为 right
            stack.push(pivot-1);
        }
        // 当前基准右边还有元素时
        if (pivot + 1 < end) {
            stack.push(pivot+1);
            stack.push(end);
        }
        while (!stack.isEmpty()) {
            // 由于栈先进后出的特性，因此先取出的元素作为 end
            end = stack.pop();
            // 后取出的元素作为 start
            start = stack.pop();
            pivot = partition(nums,start,end);
            if (pivot - 1 > start) {
                stack.push(start);
                stack.push(pivot - 1);
            }
            if (pivot + 1 < end) {
                stack.push(pivot + 1);
                stack.push(end);
            }
        }
    }
    private int midOfThree(int[] nums,int left,int right) {
        int mid = (right-left)/2;
        if (nums[left] > nums[right]) {
            if (nums[mid] > nums[left]) {
                return left;
            }else if (nums[mid] < nums[right]) {
                return right;
            }else {
                return mid;
            }
        }else {
            if (nums[mid] > nums[right]) {
                return right;
            }else if (nums[mid] < nums[left]) {
                return left;
            }else {
                return mid;
            }
        }
    }
    private void insertSortOfRange(int[] nums,int start,int end) {
        for (int i = start+1; i <= end; i++) {
            int tmp = nums[i];
            int j = i-1;
            for (; j >= start; j--) {
                if (nums[j] > tmp) {
                    nums[j+1] = nums[j];
                }else {
                    break;
                }
            }
            nums[j+1] = tmp;
        }
    }
    private int partition(int[] nums,int left,int right) {
        // 1、挖坑法
        int key = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            // 此时右边的值比key小，将右边的值给到左边
            nums[left] = nums[right];
            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return left;
    }
    private int partition1(int[] nums,int left,int right) {
        // 2、霍尔法
        int key = nums[left];
        int i = left;
        while (left <= right) {
            while (left < right && nums[right] > key) {
                right--;
            }
            while (left < right && nums[left] < key) {
                left++;
            }
            // 将较大值与较小值交换
            swap(nums,left,right);
        }
        // 将中值key移动到left和right相遇位置
        swap(nums,left,i);
        // 返回新的基准：因为当前left的左边一定比num[left]小，右边一定比nums[left]大，所以返回left
        return left;
    }

    private void swap(int[] arrays,int i,int j) {
        int tmp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = tmp;
    }
    // 归并排序,分治法的典型例子，属于最常用的外部排序
    public void mergeSort(int[] nums) {
        mergeSortFunc(nums,0,nums.length - 1);
    }
    private void mergeSortFunc(int[] nums,int left,int right) {
        if (left >= right) {
            // 递归的退出条件
            return;
        }
        int mid = (left + right) / 2;
        // 归：通过将数组逐步分解成多个子部分
        mergeSortFunc(nums,left,mid);
        mergeSortFunc(nums,mid + 1,right);
        // 并：通过逐步将子部分合并有序，最终使整体有序
        merge(nums,left,right,mid);
    }
    private void merge(int[] nums,int left,int end,int mid) {
        // 合并部分：可以看成是两个有序数组的合并
        int s1 = left;
        int s2 = mid + 1;
        int[] tmpArray = new int[end - left + 1];
        int k = 0; // 控制临时数组的下标
        while (s1 <= mid && s2 <= end) {
            if (nums[s1] < nums[s2]) {
                tmpArray[k++] = nums[s1++];
            }else {
                tmpArray[k++] = nums[s2++];
            }
        }
        // 当s1未遍历完时,将剩余元素都添加到临时数组中
        while (s1 <= mid) {
            tmpArray[k++] = nums[s1++];
        }
        // 当s2未遍历完时
        while (s2 <= end) {
            tmpArray[k++] = nums[s2++];
        }
        // 将临时数组中的元素添加到原数组中
        for (int i = 0; i < tmpArray.length; i++) {
            nums[left + i] = tmpArray[i];
        }
    }
}
