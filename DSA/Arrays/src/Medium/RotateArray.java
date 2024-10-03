package Medium;//    Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
//
//
//
//            Example 1:
//
//    Input: nums = [1,2,3,4,5,6,7], k = 3
//    Output: [5,6,7,1,2,3,4]
//    Explanation:
//    rotate 1 steps to the right: [7,1,2,3,4,5,6]
//    rotate 2 steps to the right: [6,7,1,2,3,4,5]
//    rotate 3 steps to the right: [5,6,7,1,2,3,4]
//    Example 2:
//
//    Input: nums = [-1,-100,3,99], k = 2
//    Output: [3,99,-1,-100]
//    Explanation:
//    rotate 1 steps to the right: [99,-1,-100,3]
//    rotate 2 steps to the right: [3,99,-1,-100]
//
//
//    Constraints:
//
//            1 <= nums.length <= 105
//            -231 <= nums[i] <= 231 - 1
//            0 <= k <= 105
//
//
//    Follow up:
//
//    Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
//    Could you do it in-place with O(1) extra space?

public class RotateArray {

    static class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            if(n == 1 || k == 0) return ;
            reverse(nums, 0, n-1);
            reverse(nums, 0, k-1);
            reverse(nums, k, n-1);
        }

        public void reverse(int[] nums, int spos, int epos) {
            for(; spos<epos; spos++, epos--) {
                int temp = nums[spos];
                nums[spos] = nums[epos];
                nums[epos] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-1};
        s.rotate(nums, 2);
        for (int num : nums) {
            System.out.println(num);
        }

    }
}
