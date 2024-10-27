package Medium;

//    Given an array arr[] and an integer k where k is smaller than the size of the array, the task is to find the kth smallest element in the given array.
//
//    Follow up: Don't solve it using the inbuilt sort function.
//
//    Examples :
//
//    Input: arr[] = [7, 10, 4, 3, 20, 15], k = 3
//    Output:  7
//    Explanation: 3rd smallest element in the given array is 7.
//    Input: arr[] = [2, 3, 1, 20, 15], k = 4
//    Output: 15
//    Explanation: 4th smallest element in the given array is 15.
//    Expected Time Complexity: O(n+(max_element) )
//    Expected Auxiliary Space: O(max_element)
//    Constraints:
//            1 <= arr.size <= 106
//            1<= arr[i] <= 106
//            1 <= k <= n

import Easy.TwoSum;

import java.util.PriorityQueue;

public class KthSmallestElement {

    static class Solution {

        public void heapify(int[] arr, int curr, int size) {
            int l = 2*curr+1;
            int r = 2*curr+2;
            int smallest = curr;
            if(l<size && arr[l] < arr[smallest]) {
                smallest = l;
            }
            if(r<size && arr[r] < arr[smallest]) {
                smallest = r;
            }
            if(smallest != curr) {
                int temp = arr[smallest];
                arr[smallest] = arr[curr];
                arr[curr] = temp;

                heapify(arr, smallest, size);
            }
        }

        public int kthSmallest(int[] arr, int k) {

            // 1st approach - heap implementation
            //build-heap
            //half of the array will be leaf nodes

            // int n = arr.length;
            // for(int i=(n/2)-1; i>=0; i--) {
            //     heapify(arr, i, n);
            // }

            // for(int i=0; i<k-1; i++) {
            //     int temp = arr[0];
            //     arr[0] = arr[n-i-1];
            //     arr[n-i-1] = temp;
            //     heapify(arr, 0, n-i-1);
            // }
            // return arr[0];


            //2nd approach - priority queue implementation
            int n = arr.length;
            PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b.compareTo(a));
            for (int j : arr) {
                maxQueue.add(j);
                if (maxQueue.size() > k) {
                    maxQueue.remove();
                }
            }
            return maxQueue.peek();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{7, 10, 4, 3, 20, 15};
        System.out.printf("%d ", s.kthSmallest(nums, 3));
    }

}
