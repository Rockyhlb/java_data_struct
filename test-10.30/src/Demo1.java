import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/30 13:50
 * @desc :  LeetCode-Arrays-easy
 */
public class Demo1 {
    /**
     * 26. 删除有序数组中的重复项：
     *     给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     *     元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * 思路：因为是排序数组，因此相同的元素也是紧邻的，采用双指针的方法，q访问下一个元素，p访问当前个元素，
     *       当p和q相等时，q继续往后遍历，反之则将当前q的值赋给p+1，p往后移
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while(q < nums.length) {
            if(nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 27. 移除元素:
     *     给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *     不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *     元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        while(right < nums.length) {
            // 当右指针的元素不等于val时将元素往左移
            if(nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
            // 当元素相同时将指针向右移
            right++;
        }
        return left;
    }

    /**
     * 35. 搜索插入位置：
     *     给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *     请必须使用时间复杂度为 O(log n) 的算法。
     *     输入: nums = [1,3,5,6], target = 5
     *     输出: 2
     *     输入: nums = [1,3,5,6], target = 2
     *     输出: 1
     */
    // 1、通过左右指针进行遍历
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            if(nums[left] == target) {
                return left;
            }else if(nums[right] == target) {
                return right;
            }
            right--;
            left++;
        }
        //此时的left指针已经指向右半部分数据
        if(target >= nums[left]) {
            while(left < nums.length && nums[left] < target) {
                left++;
            }
            return left;
        }else {
            while(right >= 0 && nums[right] >= target) {
                right--;
            }
            return right+1;
        }
    }
    // 2、通过二分查找完成需求
    public int searchInsert1(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right)>>>1;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] > target) {
                if(mid-1 >= 0 && nums[mid - 1] < target) {
                    return mid;
                }
                right = mid-1;
            }else {
                if(mid+1 < nums.length && nums[mid + 1] > target) {
                    return mid+1;
                }
                left = mid + 1;
            }
        }
        return right+1;
    }

    /**
     * 11. 盛最多水的容器
     *     给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     *     找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *     返回容器可以储存的最大水量。
     *     说明：你不能倾斜容器。
     *     输入：[1,8,6,2,5,4,8,3,7]
     *     输出：49
     */
    public int maxArea(int[] height) {
        // 有bug，且时间复杂度大
        if(height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        if(height.length == 2) {
            if(height[left] > height[right]) {
                return height[right];
            }else {
                return height[left];
            }
        }
        while(left <= right>>>1) {
            while(right > left) {
                if(height[left] >= height[right]) {
                    int newMax = (right-left) * height[right];
                    if(newMax > max) {
                        max = newMax;
                    }
                }else if(height[left] < height[right]) {
                    int newMax = (right-left)*height[left];
                    if(newMax > max) {
                        max = newMax;
                    }
                }
                right--;
            }
            right = height.length - 1;
            left++;
        }
        return max;
    }

    public int maxArea1(int[] height) {
        //　时间复杂度低，精简
        if(height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while(left < right) {
            area = Math.max(area,(right-left) * Math.min(height[left],height[right]));
            // 因为容器的最大体积由短板决定，因此我们应该将短板尽可能变大
            // 在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 −1-1−1​ 变短：
            // 若向内 移动短板 ，水槽的短板 min(h[left],h[right]) 可能变大，因此下个水槽的面积 可能增大 。
            // 若向内 移动长板 ，水槽的短板 min(h[left],h[right]) 不变或变小，因此下个水槽的面积 一定变小 。
            if(height[left] > height[right]) {
                right--;  // 改变短板height[right]
            }else {
                left++;   // 改变短板height[left]
            }
        }
        return area;
    }

    /**
     * 283. 移动零
     *      给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *      请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        if(nums == null || nums.length == 0) {
            return;
        }
        while(right < nums.length) {
            if(nums[right] == 0) {
                right++;
            }else {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right++;
            }
        }
    }
}
