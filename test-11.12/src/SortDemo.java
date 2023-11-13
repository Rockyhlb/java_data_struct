
/**
 * @BelongsProject: test-11.12
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-13 14:58
 * @Description: 排序算法
 * @Author: code_hlb
 */
public class SortDemo {
    /**
     * 1、直接插入排序：类似于打扑克插入牌的方法，拿到一张牌后依次与前面的元素比较，大则插入，小则继续往前比
     *    时间复杂度：
     *      最好情况：数据完全有序的时候：O(n)
     *      最坏情况：数据完全逆序的时候：O(n^2)
     *    空间复杂度：O(1)
     *    稳定性(排序以后相同的元素顺序基于排序前不变)：稳定
     *      *** 一个本身就是稳定的排序，是可以实现为不稳定的排序的     ***
     *      *** 但是一个本身就不稳定的排序，是不可能实现为稳定的排序的 ***
     */
    public void insertSort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int tmp = arrays[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arrays[j] > tmp) {
                    arrays[j+1] = arrays[j];
                }else {
                    break;
                }
            }
            arrays[j + 1] = tmp;
        }
    }

    /**
     * 2、希尔排序  ：是对直接插入排序的优化，通过采用跳跃式分组，再将每组进行插入排序，可能会将更小的元素尽可能往前放
     *    时间复杂度: O(n^1.3 - n^1.5)
     *    空间复杂度：O(1)
     *    稳定性   ：不稳定
     */
    public void shellSort(int[] arrays) {
        int gap = arrays.length;
        while (gap > 1) {
            gap /= 2;
            shell(arrays,gap);
        }
    }

    public void shell(int[] arrays,int gap) {
        for (int i = gap; i < arrays.length; i++) {
            int tmp = arrays[i];
            // i++往前走后，j也跟着往前走1步
            int j = i - gap;
            // 此处如果是j--就会导致分组失败，j-=gap会保证每次都是一组元素比较
            for (; j >= 0; j -= gap) {
                if (arrays[j] > tmp) {
                    arrays[j + gap] = arrays[j];
                }else {
                    break;
                }
            }
            // 上一个循环结束以后，j = j - gap,因此此处交换元素不应该是arrays[j],应该是arrays[j + gap] = tmp;
            arrays[j + gap] = tmp;
        }
    }

    /**
     * 3、选择排序(相对不实用)： 每一次从待排序数据元素中选出最小(或最大)的一个元素，
     *                         存放在序列的起始位置，直到全部待排序的数据元素排完
     *    时间复杂度: 无论好坏情况,复杂度都是O(n)
     *    空间复杂度：O(1)
     *    稳定性   ：不稳定
     */
    public void selectSort(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arrays,minIndex,i);
        }
    }
    public static void swap(int[] arrays,int i,int j) {
        int tmp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = tmp;
    }
    // 选择排序的第二种写法，时间复杂度不变
    public void selectSort2(int[] arrays) {
        int left = 0;
        int right = arrays.length - 1;
        while (left < right) {
            // 记录最小和最大下标
            int minIndex = left;
            int maxIndex = left;
            for (int i = left + 1; i < right; i++) {
                if (arrays[i] < arrays[minIndex]) {
                    minIndex = i;
                }else if (arrays[i] > arrays[maxIndex]){
                    maxIndex = i;
                }
            }
            swap(arrays,left,minIndex);
            // 若最大值刚好在原来最小值的位置，此时已经被交换到了minIndex的位置，需要手动修正最大值的最新索引
            if (maxIndex == left) {
                maxIndex = minIndex;
            }
            swap(arrays,right,maxIndex);
            left++;
            right--;
        }
    }

    /**
     * 4、堆 排 序 ：通过创建大根堆，不断将堆顶元素与最后一个元素交换，再对其进行向下调整，最后结果就是从小到大的排序
     *    时间复杂度: O(N*logN)  -->   比插入，选择，希尔排序更快
     *               数据量很大时，堆排序 一定 比希尔排序快
     *    空间复杂度：O(1)
     *    稳定性   ：不稳定的
     */
    public void heapSort(int[] arrays) {
        createBigHeap(arrays);
        int end = arrays.length - 1;
        while (end > 0) {
            swap(arrays,0,end);
            siftDown(arrays,0,end);
            end--;
        }
    }
    // 建立一个大根堆
    private static void createBigHeap(int[] arrays) {
        for (int parent = (arrays.length-1-1)/2; parent >= 0; parent--) {
            siftDown(arrays,parent,arrays.length);
        }
    }
    // 向下调整
    private static void siftDown(int[] arrays,int parent,int end) {
        int child = 2 * parent + 1;

        while (child < end) {
            if (child + 1 < end && arrays[child] < arrays[child + 1]) {
                child++;
            }
            if (arrays[parent] < arrays[child]) {
                swap(arrays,parent,child);
                parent = child;
                child = 2 * child + 1;
            }else {
                break;
            }
        }
    }

    /**
     * 5、冒泡排序 ： 每一次将最大元素移动到最右端
     *    时间复杂度: O(N^2) 如果加了优化，最好情况O(N)
     *    空间复杂度：O(1)
     *    稳定性   ：不稳定
     */
    public void bubbleSort(int[] arrays) {
        for (int i = 0; i < arrays.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arrays.length-1-i; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    swap(arrays,j,j+1);
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }

    /**
     * 6、快速排序  : 本质是二叉树，
     *    时间复杂度
     *        最好情况：O(N*logN)   -->  (满二叉树或完全二叉树)
     *        最坏情况：O(N^2)      -->  (单分支树)
     *    空间复杂度
     *        最好情况：O(logN)     -->  (满二叉树或完全二叉树)
     *        最好情况：O(N)        -->  (单分支树)
     *    稳定性   : 不稳定
     */
    public void quickSort(int[] arrays) {
        quick(arrays,0,arrays.length - 1);
    }

    private static void quick(int[] arrays,int start,int end) {
        // 左边是一个节点或一个节点都没有时
        if (start >= end) {
            return;
        }
        int pivot = partition(arrays,start,end);
        quick(arrays,start,pivot - 1);
        quick(arrays,pivot + 1,end);
    }
    // 找基准
    private static int partition(int[] arrays,int left,int right) {
        int key = arrays[left];
        // 记录最初left的位置
        int i = left;
        while (left < right) {
            // 单独的一重循环，因此还要限制right的下标访问，最终right一定是比 key 小的值
            while (left < right && arrays[right] >= key) {
                right--;
            }
            // 最后left一定是比 key 大的值
            while (left < right && arrays[left] <= key) {
                left++;
            }
            // 交换left 和 right
            swap(arrays,left,right);
        }
        // 将left 和 right相遇的位置 与 原来的基准交换
        swap(arrays,left,i);
        // 此时的left就是新的基准
        return left;
    }
}
