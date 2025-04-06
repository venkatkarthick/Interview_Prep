package Hard;//    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//    The overall run time complexity should be O(log (m+n)).
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,3], nums2 = [2]
//    Output: 2.00000
//    Explanation: merged array = [1,2,3] and median is 2.
//    Example 2:
//
//    Input: nums1 = [1,2], nums2 = [3,4]
//    Output: 2.50000
//    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
//    Constraints:
//
//    nums1.length == m
//    nums2.length == n
//    0 <= m <= 1000
//            0 <= n <= 1000
//            1 <= m + n <= 2000
//            -106 <= nums1[i], nums2[i] <= 106

public class MedianOfTwoSortedArray {

    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int i=0, j=0, index = 0;
            int[] sortedArray = new int[nums1.length + nums2.length];
            for(; i<nums1.length && j<nums2.length; index++ ) {
                if(nums1[i] < nums2[j]) {
                    sortedArray[index] = nums1[i];
                    i++;
                }
                else {
                    sortedArray[index] = nums2[j];
                    j++;
                }
            }

            for(; i<nums1.length; i++, index++) {
                sortedArray[index] = nums1[i];
            }

            for(; j<nums2.length; j++, index++) {
                sortedArray[index] = nums2[j];
            }

            double median = 0;
            if(index % 2 != 0) {
                median = sortedArray[index/2];
            } else {
                median = (sortedArray[index/2]+sortedArray[index/2 -1]) / 2D;
            }
            return median;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        double index = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(index + "\n");
    }
}
