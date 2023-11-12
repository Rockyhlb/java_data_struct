/**
 * @author: code_hlb
 * @date :  2023/11/12 13:51
 * @desc :  排序算法
 */
public class SortDemo {
    /**
     * 1、直接插入排序：类似于打扑克插入牌的方法，拿到一张牌后依次与前面的元素比较，大则插入，小则继续往前比
     *   时间复杂度：
     *     最好情况：数据完全有序的时候：O(n)
     *     最坏情况：数据完全逆序的时候：O(n^2)
     *   空间复杂度：O(1)
     *   稳定性(排序以后相同的元素顺序基于排序前不变)：稳定
     *      *** 一个本身就是稳定的排序，是可以实现为不稳定的排序的 ***
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
     * 2、希尔排序： 是对直接插入排序的优化，通过采用跳跃式分组，再将每组进行插入排序，可能会将更小的元素尽可能往前放
     *   时间复杂度: O(n^1.3 - n^1.5)
     *   空间复杂度：O(1)
     *   稳定性   ：不稳定
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
     * 3、直接选择排序： 每一次从待排序数据元素中选出最小(或最大)的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完
     *   时间复杂度: 无论好坏情况,复杂度都是O(n)
     *   空间复杂度：O(1)
     *   稳定性   ：不稳定
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

    public void swap(int[] arrays,int i,int j) {
        int tmp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = tmp;
    }
}
