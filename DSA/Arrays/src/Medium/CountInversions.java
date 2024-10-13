package Medium;


import Easy.BinaryArraySorting;

import java.util.Map;
import java.util.TreeMap;

public class CountInversions {

     static class Solution {
        // arr[]: Input Array
        // N : Size of the Array arr[]
        // Function to count inversions in the array.
        static long inversionCount(long arr[]) {
            Integer n = arr.length;
            Integer[] convertedArr = new Integer[n];

            //Insert into map
            TreeMap<Long, Integer> tmap = new TreeMap<Long, Integer>();
            for(Integer i=1; i<n+1; i++) {
                tmap.put(arr[i-1], i);
            }
            Integer j=0;
            for(Map.Entry<Long, Integer> entry : tmap.entrySet()) {
                convertedArr[j] = entry.getValue();
                j++;
            }

            for(Integer c : convertedArr) {
                System.out.printf("%d ", c);
            }
            System.out.println("\n");

            //Binary Indexed tree implementation
            Integer[] bit = new Integer[n+1];


            return 1;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        long[] nums = new long[]{2, 4, 1, 3, 5};
        System.out.println(s.inversionCount(nums));
    }

}

// 
// 0 1 2 3 4 5