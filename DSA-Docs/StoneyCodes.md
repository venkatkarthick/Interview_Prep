1. https://leetcode.com/problems/contains-duplicate/description/
> Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.  
```
Input: nums = [1,2,3,1]  
Output: true  
Explanation:  
The element 1 occurs at the indices 0 and 3.
```

```java []
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hash=new HashSet();
        int n=nums.length;
        for(int i=0; i<n; i++) {
            if(hash.contains(nums[i])) {
                return true;
            } else {
                hash.add(nums[i]);
            }
        }
        return false;
    }
}
```

2. https://leetcode.com/problems/missing-number/description/

> Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
```
Input: nums = [3,0,1]
Output: 2
Explanation:
n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
```
```java []
class Solution {
    public int missingNumber(int[] nums) {
        int len=nums.length;
        for(int i=0; i<len; i++) {
            while(nums[i]<len && i!=nums[i]) {
                //swap i and nums[i]
                int temp=nums[i];
                nums[i]=nums[nums[i]];
                nums[temp]=temp;
            }
        }
        for(int i=0; i<len; i++) {
            if(i!=nums[i]) return i;
        }
        return len;
    }
}
```
