import java.util.PriorityQueue;

/**
 * @BelongsProject: test-11.30
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023/11/30 8:40
 * @Description: 排序算法
 * @Author: code_hlb
 */
public class Demo {
    // 1、冒泡排序
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 标记是否被
            boolean flag = false;
            for (int j = 0; j < nums.length-1-i; j++) {
                if (nums[j+1] < nums[j]) {
                    swap(nums,j+1,j);
                    flag = true;
                }
            }
            // 如果刚才的循环没有交换的元素，说明已经有序，跳出循环
            if (!flag) {
                break;
            }
        }
    }

    // 2、直接插入排序
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > tmp) {
                    nums[j+1] = nums[j];
                }else {
                    break;
                }
            }
            nums[j+1] = tmp;
        }
    }

    // 3、希尔排序
    public static void shellSort(int[] nums) {
        // 采用跳跃式分组进行一组元素的比较
        int gap = nums.length;
        while (gap > 1) {
            // 每次分组数折半
            gap /= 2;
            shell(nums,gap);
        }
    }
    private static void shell(int[] nums,int gap) {
        for (int i = gap; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i - gap;
            // 保证每次比较的都是一组元素
            for (; j >= 0; j -= gap) {
                if (nums[j] > tmp) {
                    nums[j + gap] = nums[j];
                }else {
                    break;
                }
            }
            nums[j + gap] = tmp;
        }
    }

    //4、选择排序  每次选出最小元素往两边插入
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            swap(nums,i,minIndex);
        }
    }
    // 优化成每次找到最大和最小值
    public static void selectSort1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = left;
            for (int i = left + 1; i <= right; i++) {
                if (nums[minIndex] > nums[i]) {
                    minIndex = i;
                }
                if (nums[maxIndex] < nums[i]) {
                    maxIndex = i;
                }
            }
            swap(nums,left,minIndex);
            swap(nums,right,maxIndex);
            left++;
            right--;
        }
    }

    // 5、堆排序
    public static void heapSort(int[] nums) {
        createHeap(nums);
        int end = nums.length - 1;
        while (end > 0) {
            swap(nums,0,end);
            siftDown(nums,0,end--);
        }
    }
    // 建立大根堆
    private static void createHeap(int[] nums) {
        for (int parent = (nums.length-1-1)/2; parent >= 0; parent--) {
            siftDown(nums,parent,nums.length);
        }
    }
    private static void siftDown(int[] nums,int parent,int end) {
        // 左子树
        int child = 2 * parent + 1;
        while (child < end) {
            // 找到子结点中的较大结点
            if (child + 1 < end && nums[child + 1] > nums[child]) {
                child++;
            }
            // 当父结点比大结点小时，结点元素交换
            if(nums[parent] < nums[child]) {
                swap(nums,parent,child);
                parent = child;
                child = 2 * child + 1;
            }else {
                break;
            }
        }
    }

    // 6、快速排序
    public static void quickSort(int[] nums) {
        quick(nums,0,nums.length - 1);
    }
    private static void quick(int[] nums,int start,int end) {
        // 左边只有一个结点或者没有结点时
        if (start >= end) {
            return;
        }
        // 接收新的基准
        int pivot = partition(nums,start,end);
        quick(nums,start,pivot - 1);
        quick(nums,pivot + 1,end);
    }
    private static int partition(int[] nums,int start,int end) {
        // 1、挖坑法
        int tmp = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= tmp) {
                end--;
            }
            // 此时的end就是比关键字小的元素
            nums[start] = nums[end];
            while (start < end && nums[start] <= tmp) {
                start++;
            }
            // 此时的start就是比关键字大的元素
            nums[end] = nums[start];
        }
        // 填补最后一个坑
        nums[start] = tmp;
        return start;
    }

    // 7、归并排序
    public static void mergeSort(int[] nums) {
        merSortFunc(nums,0,nums.length - 1);
    }
    private static void merSortFunc(int[] nums,int left,int right) {
        // 归

        // 并
    }

    // 8、计数排序
    public static void countSort(int[] nums) {
        // 1、找到待排序数组的极值
        int minVal = nums[0];
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        // 2、创建一个容量为 "maxVal-minVal" 的数组
        int[] countArray = new int[maxVal - minVal + 1];
        for (int elem:nums) {
            // 开始计数
            countArray[elem - minVal]++;
        }
        // 3、遍历计数数组，将元素写入数组
        int k = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] != 0) {
                nums[k++] = i + minVal;
                countArray[i]--;
            }
        }
    }

    private static void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
