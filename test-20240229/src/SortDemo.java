import java.security.cert.CRLException;
import java.util.Collections;

/**
 * @BelongsProject: test-20240229
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/29 13:57
 * @Description: 排序算法
 * @Author: code_hlb
 */
public class SortDemo {
    // 冒泡排序
    public void bubbleSort(int[] nums) {
        // 控制次数(冒泡排序的比较次数即为数组长度-1)
        for (int i = 0; i < nums.length-1; i++) {
            // 标记此次循环是否被交换
            boolean flag = false;
            // 开始比较
            for (int j = 0; j < nums.length-i-1; j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
                    flag = true;
                }
            }
            // 当没有发生交换时，退出循环
            if (!flag) {
                break;
            }
        }
    }
    private static void swap(int[] nums,int i,int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    // 插入排序
    public void insertSort(int[] nums) {
        // 从第二个数字开始往前比较
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            // 从前一个数开始比较
            int j = i-1;
            for (; j >= 0; j--) {
                if (nums[j] > tmp) {
                    // 将较大值往后移
                    nums[j+1] = nums[j];
                }else {
                    break;
                }
            }
            // 最后填补坑位
            nums[j+1] = tmp;
        }
    }
    // 希尔排序
    public void shellSort(int[] nums) {
        int gap = nums.length;
        while (gap > 1) {
            gap /= 2;
            shell(nums,gap);
        }
    }
    private void shell(int[] nums,int gap) {
        // shell 排序采用分组比较的方法，先局部有序，再全局有序
        for (int i = gap; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i - gap;
            // j-=gap：保证每次都是一组元素进行比较
            for (; j >= 0; j-=gap) {
                if (nums[j] > tmp) {
                    nums[j+gap] = nums[j];
                }else {
                    break;
                }
            }
            nums[j+gap] = tmp;
        }
    }
    // 选择排序
    public void selectSort(int[] nums) {
        // 每次选择 max/min 交换到 队尾/对头
//        for (int i = 0; i < nums.length; i++) {
//            int min = i;
//            for (int j = i+1; j < nums.length; j++) {
//                if (nums[j] < nums[min]) {
//                    min = j;
//                }
//            }
//            // 此时的min一定是当前最小值的下标
//            swap(nums,i,min);
//        }
        // 采用双指针对上述代码进行优化
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;
            for (int i = left+1; i < nums.length; i++) {
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }else if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            // 将最小值交换到left
            swap(nums,left,minIndex);
            // 当最大值下标恰好是left时，经过上述交换，此时已经在minIndex处
            if (maxIndex == left) {
                maxIndex = minIndex;
            }
            swap(nums,right,maxIndex);
            left++;
            right--;
        }
    }
    // 堆排序
    public void heapSort(int[] nums) {
        // 创建大根堆
        createBigHeap(nums);
        int end = nums.length - 1;
        while (end > 0) {
            swap(nums,0,end);
            siftDown(nums,0,end);
            end--;
        }
    }
    private void createBigHeap(int[] nums) {
        for (int parent = (nums.length-1-1)/2; parent >= 0; parent--) {
            siftDown(nums,parent,nums.length);
        }
    }
    private void siftDown(int[] nums,int parent,int end) {
        int child = 2*parent+1;
        while (child < end) {
            if (child + 1 < end && nums[child] < nums[child+1]) {
                // 保证孩子元素是较大元素
                child++;
            }
            if (nums[child] > parent) {
                swap(nums,parent,child);
                parent = child;
                child = 2 * child + 1;
            }else {
                break;
            }
        }
    }
    // 计数排序
    public void countSort(int[] nums) {
        // 1、找出max/min，确定计数数组的界限
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }else if (nums[i] < min) {
                min = nums[i];
            }
        }
        // 2、声明数组
        int[] count = new int[max - min + 1];
        for (int num: nums) {
            count[num - min]++;
        }
        // 3、将技术数组的元素遍历写回到nums中
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                nums[index++] = i + min;
                count[i]--;
            }
        }
    }
}
