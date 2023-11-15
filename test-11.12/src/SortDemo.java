import java.util.Stack;

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

    private void shell(int[] arrays,int gap) {
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
    private static void swap(int[] arrays,int i,int j) {
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
     *    稳定性   ：不稳定
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
     *    稳定性   ：稳定
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
     * 6、快速排序  : 本质是二叉树的递归，因此非极端情况下不建议使用快排
     *    时间复杂度
     *        最好情况：O(N*logN)   -->  (满二叉树或完全二叉树)
     *        最坏情况：O(N^2)      -->  (单分支树)
     *    空间复杂度
     *        最好情况：O(logN)     -->  (满二叉树或完全二叉树)
     *        最好情况：O(N)        -->  (单分支树)
     *    稳定性   : 不稳定
     */
    public void quickSort(int[] arrays) {
        quick1(arrays,0,arrays.length - 1);
    }
    // 递归排序基准的前半部分和后半部分
    private static void quick(int[] arrays,int start,int end) {
        // 左边是一个节点或一个节点都没有时
        if (start >= end) {
            return;
        }
        int pivot = partition(arrays,start,end);
        quick(arrays,start,pivot - 1);
        quick(arrays,pivot + 1,end);
    }

    // 快排有两种优化方法，可以通过减少递归次数来减少栈帧的建立，从而减少内存的开销
    // 分别是 三数取中 和 插入排序方法
    private static void quick1(int[] arrays,int start,int end) {
        // 左边是一个节点 或 一个节点都没有时
        if (start >= end) {
            return;
        }
        // 1、三数取中(通过降低树的高度来降低空间复杂度，但是并不能保证时间复杂度的降低)
        int mid = midOfThree(arrays,start,end);
        // 交换完以后，start位置的元素一定是中间大的元素
        swap(arrays,start,mid);

        // 2、因为二叉树中最后几排的节点数量占比约为总节点的2/3,因此我们可以采用插入排序，减少递归次数
        // 递归到小的区间时，可以使用插入排序
        if (end - start + 1 <= 10) {
            insertSortRange(arrays,start,end);
            // 此时已经有序，打断递归
            return;
        }
        int pivot = partition(arrays,start,end);
        quick1(arrays,start,pivot - 1);
        quick1(arrays,pivot + 1,end);
    }
    private static int midOfThree(int[] arrays,int left,int right) {
        int mid = (left + right) / 2;
        if (arrays[left] > arrays[right]){
            if (arrays[mid] > arrays[left]) {
                return left;
            }else if (arrays[mid] < arrays[right]){
                return right;
            }else {
                return mid;
            }
        }else {
            if (arrays[mid] > arrays[right]) {
                return right;
            }else if (arrays[mid] < arrays[left]){
                return left;
            }else {
                return mid;
            }
        }
    }

    private static void insertSortRange(int[] arrays,int begin,int end) {
        for (int i = begin + 1; i <= end; i++) {
            int tmp = arrays[i];
            int j = i - 1;
            for (; j >= begin; j--) {
                if (arrays[j] > tmp) {
                    arrays[j + 1] = arrays[j];
                } else {
                    break;
                }
            }
            arrays[j + 1] = tmp;
        }
    }

    // 快排有三种递归的实现方法，分别是挖坑法，霍尔法，前后指针法  最经常用的是挖坑法，其次是霍尔，前后指针了解即可
    // 1、挖坑法，返回新的基准（最经常使用的）
    private static int partition(int[] arrays,int left,int right){
        int key = arrays[left];
        // 记录最初left的位置
        while (left < right) {
            // 为什么要从 right 开始遍历，而不是 left呢？
            // 答：因为如果从left开始遍历，可能会导致最终的新基准大于当前基准，不利于排序
            // 单独的一重循环，因此还要限制right的下标访问，最终right一定是比 key 小的值
            while (left < right && arrays[right] >= key) {
                right--;
            }
            arrays[left] = arrays[right];
            // 最后left一定是比 key 大的值
            // 这里的arrays[left] <= key 可以修改为 arrays[left] < key么？
            // 答：不能，如果末尾数据和基准数据一致时，right就无法向前移动，从而导致死循环的产生
            while (left < right && arrays[left] <= key) {
                left++;
            }
            arrays[right] = arrays[left];
        }
        arrays[left] = key;
        return left;
    }

    // 2、霍尔法
    private static int partition1(int[] arrays,int left,int right) {
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

    // 3、前后指针法
    private static int partition2(int[] arrays,int left,int right) {
        int prev = left;
        int cur = left + 1;
        while (cur <= right) {
            if (arrays[cur] < arrays[left] && arrays[++prev] != arrays[cur]) {
                swap(arrays,cur,prev);
            }
            cur++;
        }
        swap(arrays,prev,left);
        return prev;
    }

    // 快排用的更多的方式还是非递归,内存开销小，不容易发生栈溢出
    public void quickSort1Nor(int[] arrays) {
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int right = arrays.length - 1;

        int pivot = partition(arrays,left,right);
        // 左边有两个元素
        if (pivot - 1 > left) {
            // 下次取出作为left
            stack.push(left);
            // 下次取出作为right
            stack.push(pivot - 1);
        }
        if (pivot + 1 < right) {
            stack.push(pivot + 1);
            stack.push(right);
        }

        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();

            pivot = partition(arrays,left,right);
            // 左边有两个元素
            if (pivot - 1 > left) {
                stack.push(left);
                stack.push(pivot - 1);
            }
            if (pivot + 1 < right) {
                stack.push(pivot + 1);
                stack.push(right);
            }
        }
    }

    /**
     * 7、归并排序  : 是采用分治法的典型例子，将已有序的子序列合并，得到完全有序的序列
     *               缺点在于需要O(N)的空间复杂度，因此更多用于解决在磁盘中的排序问题，属于最常用的外部排序
     *    时间复杂度: O(N*logN)
     *    空间复杂度: O(N)
     *    稳定性   : 稳定
     */
    public void mergeSort(int[] arrays) {
        mergeSortFunction(arrays,0,arrays.length - 1);
    }
    private static void mergeSortFunction(int[] arrays,int left,int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        // 分解部分  从mid向左右两端分解
        mergeSortFunction(arrays,left,mid);
        mergeSortFunction(arrays,mid + 1,right);
        // 合并部分
        merge(arrays,left,right,mid);
    }
    private static void merge(int[] arrays, int left, int right, int mid) {
        // 演变成两个有序数组的合并
         int s1 = left;
         // int e1 = mid;
         int s2 = mid + 1;
         // int e2 = right;
        int[] tmpArr = new int[right - left + 1];
        int k = 0;
        // 两个区间都有数据时
        while(s1 <= mid && s2 <= right) {
            if (arrays[s2] <= arrays[s1]) {
                tmpArr[k++] = arrays[s2++];
            }else {
                tmpArr[k++] = arrays[s1++];
            }
        }
        while(s1 <= mid) {
            tmpArr[k++] = arrays[s1++];
        }
        while (s2 <= right) {
            tmpArr[k++] = arrays[s2++];
        }
        // 将tmpArr中的有序数据插入到原数组中
        for (int i = 0; i < tmpArr.length; i++) {
            arrays[i + left] = tmpArr[i];
        }
    }
    // 归并排序的非递归实现
    public void mergeSortNor(int[] arrays) {
        int gap = 1;
        while (gap < arrays.length) {
            for (int i = 0; i < arrays.length; i += 2*gap) {
                int left = i;
                int mid = left + gap - 1;
                int right = mid + gap;
                if (mid >= arrays.length) {
                    mid = arrays.length-1;
                }
                if (right >= arrays.length) {
                    right = arrays.length-1;
                }
                merge(arrays,left,right,mid);
            }
            gap *= 2;
        }
    }

    /**
     * 8、计数排序: 建立一个计数数组，长度为待排排序数组区间加一，再遍历数组,将对应索引处加一
     *             在数据范围集中时，效率很高，但是适用范围及场景有限
     *    时间复杂度：O[Max(N,范围)]
     *    空间复杂度: O(范围)
     *    稳定性   : 稳定
     */
    public void countSort(int[] arrays) {
        // 1、找到数组的最大值 和 最小值
        int maxNum = arrays[0];
        int minNum = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i] > maxNum) {
                maxNum = arrays[i];
            }else if (arrays[i] < minNum){
                minNum = arrays[i];
            }
        }
        // 2、建立计数数组
        int[] countArr = new int[maxNum - minNum + 1];
        for (int array : arrays) {
            // 开始计数
            countArr[array - minNum]++;
        }
        // 3、遍历计数数组，把当前元素写回arrays
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                arrays[index++] = i + minNum;
                countArr[i]--;
            }
        }
    }
}
