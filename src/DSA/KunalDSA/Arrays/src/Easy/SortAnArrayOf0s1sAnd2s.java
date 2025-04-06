package Easy;

//    Given an array arr containing only 0s, 1s, and 2s. Sort the array in ascending order.
//
//    Examples:
//
//    Input: arr[]= [0, 2, 1, 2, 0]
//    Output: 0 0 1 2 2
//    Explanation: 0s 1s and 2s are segregated into ascending order.
//    Input: arr[] = [0, 1, 0]
//    Output: 0 0 1
//    Explanation: 0s 1s and 2s are segregated into ascending order.
//    Expected Time Complexity: O(n)
//    Expected Auxiliary Space: O(1)
//
//    Constraints:
//            1 <= arr.size() <= 106
//            0 <= arr[i] <= 2


import java.util.ArrayList;
import java.util.Arrays;

//Using Dutch National Flag Algorithm
public class SortAnArrayOf0s1sAnd2s {
    static class Solution {
        // Function to sort an array of 0s, 1s, and 2s
        public void sort012(ArrayList<Integer> arr) {
            // code here
            int low = 0, mid = 0, high = arr.size() - 1;
            while(mid<=high) {
                switch(arr.get(mid)) {
                    case 0 :
                        int temp = arr.get(low);
                        arr.set(low, arr.get(mid));
                        arr.set(mid, temp);
                        low++;mid++;
                        break;

                    case 1 : mid++; break;
                    case 2 : int temp1 = arr.get(high);
                        arr.set(high, arr.get(mid));
                        arr.set(mid, temp1);
                        high--; break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(0, 2, 1, 2, 0));
        s.sort012(nums);
        for(int i=0; i<nums.size(); i++) {
            System.out.printf("%d ", nums.get(i));
        }
        System.out.println("\n");
    }
}
