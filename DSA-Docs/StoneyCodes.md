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

3. https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/

> Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

```
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
```
```java []
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len=nums.length;
        List<Integer> ans=new ArrayList();
        for(int i=0; i<len; i++) {
            while(i+1!=nums[i] && nums[i]!=nums[nums[i]-1]) {
                //swap i+1, nums[i]
                int temp=nums[i];
                nums[i]=nums[nums[i]-1];
                nums[temp-1]=temp;
            }
        }
        for(int i=0; i<len; i++) {
            if(i+1!=nums[i]) {
                ans.add(i+1);
            }
        }
        return ans; 
    }
}
```
4. http://leetcode.com/problems/two-sum/description/
> Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

```java []
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;
        for(int i=0; i<n; i++) {
            numMap.put(nums[i], i);
        }
        for(int i=0; i<n; i++){
            int secondNum = target - nums[i];
            if(numMap.containsKey(secondNum) && i != numMap.get(secondNum)) {
                int[] ans = {i, numMap.get(secondNum)};
                return ans;
            }
        }
        return null;
    }
}
```

5. https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/description/
> Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
```
Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation: 
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1). 
For nums[3]=2 there exist one smaller number than it (1). 
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
```
```java []
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        Map<Integer, Integer> countNum = new HashMap<Integer, Integer>();
        for(int i=0; i<n; i++) {
            if(!countNum.containsKey(sortedNums[i])) {
                countNum.put(sortedNums[i], i);
            }
        }
        int[] resultArr = new int[n];
        for(int i=0; i<n; i++) {
            resultArr[i] = countNum.get(nums[i]);
        }
        return resultArr;
    }
}
```
6. https://leetcode.com/problems/minimum-time-visiting-all-points/description/
> On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi]. Return the minimum time in seconds to visit all the points in the order given by points.
You can move according to these rules:
In 1 second, you can either:
move vertically by one unit,
move horizontally by one unit, or
move diagonally sqrt(2) units (in other words, move one unit vertically then one unit horizontally in 1 second).
You have to visit the points in the same order as they appear in the array.
You are allowed to pass through points that appear later in the order, but these do not count as visits.
```
Input: points = [[1,1],[3,4],[-1,0]]
Output: 7
Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]   
Time from [1,1] to [3,4] = 3 seconds 
Time from [3,4] to [-1,0] = 4 seconds
Total time = 7 seconds
```
```java []
class Solution {

    public int calSeconds(int[] p1, int[] p2) {
        return Math.max(Math.abs(p1[0]-p2[0]), Math.abs(p1[1]-p2[1]));
    }   

    public int minTimeToVisitAllPoints(int[][] points) {
        int ans=0;
        for(int i=1; i<points.length; i++) {
            ans+=calSeconds(points[i-1], points[i]);
        }
        return ans; 
    }
}
```

7. https://leetcode.com/problems/spiral-matrix/description/
> Given an m x n matrix, return all elements of the matrix in spiral order.
```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```
```java []
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rowStart = 0, rowEnd = matrix.length-1, colStart = 0, colEnd = matrix[0].length-1;
        List<Integer> spiralArray = new ArrayList<>();
        for(;rowStart <= rowEnd && colStart <= colEnd; rowStart++, rowEnd--, colStart++, colEnd--) {
            for(int j=colStart; j<=colEnd; j++) spiralArray.add(matrix[rowStart][j]);
            for(int i=rowStart+1; i<rowEnd; i++) spiralArray.add(matrix[i][colEnd]);
            if(rowStart+1 <= rowEnd) for(int j=colEnd; j>=colStart; j--) spiralArray.add(matrix[rowEnd][j]);
            if(colStart+1 <= colEnd) for(int i=rowEnd-1; i>rowStart; i--) spiralArray.add(matrix[i][colStart]);
        }
        return spiralArray;
    }
}
```
8. https://leetcode.com/problems/number-of-islands/
> Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```
```java []
class Solution {

    boolean[][] vis;

    public void bfs(Queue dq, char[][] grid) {
        int m=grid.length, n=grid[0].length;
        while(!dq.isEmpty()) {
            List<Integer> ele=(List<Integer>) dq.poll();
            int x=ele.get(0), y=ele.get(1);
            vis[x][y]=true;
            if(x+1<m && !vis[x+1][y] && grid[x+1][y]=='1') dq.add(List.of(x+1, y));
            if(x-1>=0 && !vis[x-1][y] && grid[x-1][y]=='1') dq.add(List.of(x-1, y));
            if(y+1<n && !vis[x][y+1] && grid[x][y+1]=='1') dq.add(List.of(x, y+1));
            if(y-1>=0 && !vis[x][y-1] && grid[x][y-1]=='1') dq.add(List.of(x, y-1));
        }
    }

    public void dfs(char[][] grid, int i, int j) {
        int m=grid.length, n=grid[0].length;
        if(i<0 || i>=m || j<0 || j>=n || vis[i][j] || grid[i][j]=='0') return ;
        vis[i][j]=true;
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }


    public int numIslands(char[][] grid) {
        int m=grid.length, n=grid[0].length, ans=0;
        vis=new boolean[m][n];
        //Queue<List<Integer>> dq=new LinkedList<>();
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]=='1' && !vis[i][j]) {
                    ans++;
                    //dq.add(List.of(i, j));
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
        //java.util.ImmutableCollections$List12
    }
}
```

9. https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
> You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
```
```java []
class Solution {
    public int maxProfit(int[] prices) {
        int i=0, j=1, ans=0; 
        for(;j<prices.length; j++) {
            if(prices[i]>prices[j]) {
                i=j;
            } else {
                ans= Math.max(ans, prices[j]-prices[i]);
            }
        }
        return ans;
    }
}
```
10. http://leetcode.com/problems/squares-of-a-sorted-array/description/
> Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
```
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
```
```java []
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n=nums.length,i=0,j=0;
        for(i=0; i<n; i++) {
            if(nums[i]>0) break;
        }
        j=i;
        i--;
        int[] sortedArr=new int[n];
        int k=0;
        for(; i>=0 && j<n; ) {
            if(Math.abs(nums[i])<nums[j]) {
                sortedArr[k++]=nums[i];
                i--;
            } else {
                sortedArr[k++]=nums[j];
                j++;
            }
        }
        for(; i>=0; i--) sortedArr[k++]=nums[i];
        for(; j<n; j++) sortedArr[k++]=nums[j];

        for(k=0; k<n; k++) {
            sortedArr[k]=sortedArr[k]*sortedArr[k];
        }
        return sortedArr;
    }
}
```
11. https://leetcode.com/problems/3sum/description/
> Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.
```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
```
```java []
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0; i<n-2 && nums[i]<=0; i++) {
            //If found duplicate number skip it to avoid redundant entries in final list
            if (i > 0 && nums[i] ==  nums[i - 1])
				continue;
            for(int j=i+1, k=n-1; j<k; ) {
                if(nums[i]+nums[j]+nums[k]==0) {
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    j++; k--;
                    //skip j's if they are redundant
                    while(j<k && nums[j]==nums[j-1]) j++;
                } else if(nums[i]+nums[j]+nums[k]<0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return ans;
    }
}
```
12. https://leetcode.com/problems/longest-mountain-in-array/description/
> You may recall that an array arr is a mountain array if and only if:
arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
```
Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
```
```java []
class Solution {
    public int longestMountain(int[] arr) {
        int n=arr.length;
        int ans=0, prevDip=0;
        boolean hasMount=false;
        for(int i=1; i<n-1; i++) {
            if(arr[i-1]==arr[i] && arr[i]==arr[i+1]) continue;
            if(arr[i-1]>=arr[i] && arr[i]<=arr[i+1]) {
                if(hasMount) {
                    ans=Math.max(ans, i-prevDip+1); 
                }
                prevDip=i;
                hasMount=false;
            } else if(arr[i-1]<arr[i] && arr[i]>arr[i+1]) {
                hasMount=true;
            }
        }
        if(hasMount) ans=Math.max(ans, n-prevDip); 
        return ans;
    }
}
```
13. https://leetcode.com/problems/contains-duplicate-ii/description/
> Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
```
Input: nums = [1,2,3,1], k = 3
Output: true
```
```java []
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> freqMap=new HashMap<>();
        int n=nums.length;
        for(int i=0; i<n && i<=k; i++) {
            if(!freqMap.containsKey(nums[i])) {
                freqMap.put(nums[i], 1);
            } else {
                return true;
            }
        }
        for(int j=0, i=k+1; i<n; i++, j++) {
            freqMap.put(nums[j], 0);
            if(!freqMap.containsKey(nums[i])) {
                freqMap.put(nums[i], 1);
            } else{
                if(freqMap.get(nums[i])==0) {
                    freqMap.put(nums[i], 1);
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
```
14. https://leetcode.com/problems/minimum-absolute-difference/description/
>Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr
```
Input: arr = [4,2,1,3]
Output: [[1,2],[2,3],[3,4]]
Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
```
```java []
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n=arr.length, absMin=Integer.MAX_VALUE;
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=1; i<n; i++) {
            absMin=Math.min(absMin, arr[i]-arr[i-1]);
        }
        //System.out.println(absMin+Arrays.toString(arr));
        for(int i=1; i<n; i++) {
            if(arr[i]-arr[i-1]==absMin) {
                ans.add(List.of(arr[i-1], arr[i]));
            }
        }
        return ans;
    }
}
//-14,-10,-4,3,8,19,23,27
```
15. https://leetcode.com/problems/minimum-size-subarray-sum/description/
> Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
```
```java []
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length, sum=0, len=Integer.MAX_VALUE;
        for (int i = 0, j=0; i < n; i++) {
            sum+=nums[i];
            if(sum>=target) {
                while(true) {
                    sum-=nums[j];
                    if(sum<target) {
                        len=Math.min(len, i-j+1);
                        j++;
                        break;
                    }
                    j++;
                }
            }
        }
        return len==Integer.MAX_VALUE?0:len;
    }
}

```
16. https://leetcode.com/problems/single-number/description/
> Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.
```
Input: nums = [2,2,1]
Output: 1
```
```java []
class Solution {
    public int singleNumber(int[] nums) {
        int xorVal=0, n=nums.length;
        for(int i=0; i<n; i++) {
            xorVal=xorVal^nums[i];
        }
        return xorVal;
    }
}
```
17. https://leetcode.com/problems/coin-change/description/
> You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

```
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
```
```java []
class Solution {

    int[][] memo;
    
    // public int minCoin(int ind, int sum, int[] coins) {
    //     if(sum==0) return 0;
    //     if(sum<0 || ind<0) return -1;
    //     if(memo[ind][sum]!=0) return memo[ind][sum];

    //     int op1=minCoin(ind, sum-coins[ind], coins);
    //     int op2=minCoin(ind-1, sum, coins);
    //     if(op1>-1 && op2>-1) return memo[ind][sum]=Math.min(op1+1, op2);
    //     if(op1>-1) return memo[ind][sum]=op1+1;
    //     else return memo[ind][sum]=op2;
    // } 

    public int coinChange(int[] coins, int amount) {
        //Recursion logic
        // memo=new int[coins.length][amount+1]; 
        // int res=minCoin(coins.length-1, amount, coins);
        // return res;

        //O(coins.length*amount) logic
        //consider ind in y axis(column) and sum in x axis(row). 
        //If y varies by 1, we can use the single array itself
        int[] dp=new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0]=0;
        for(int j=0; j<coins.length; j++) {
            for(int i=coins[j]; i<amount+1; i++) {
                dp[i]=Math.min(dp[i], 1+dp[i-coins[j]]);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }
}
```
18. https://leetcode.com/problems/climbing-stairs/description/
> You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
```
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```
```java []
class Solution {

    //int memo[];

    // public int climb(int steps) {
    //     if(steps==0) return 1;
    //     if(steps<0) return 0;
    //     if(memo[steps]!=0) return memo[steps];

    //     return memo[steps]=climb(steps-1)+climb(steps-2);
    // }

    public int climbStairs(int n) {
        //Recursion
        // memo=new int[n+1];
        // int res=climb(n);
        // System.out.println(Arrays.toString(memo));
        // return res;

        //dp solution
        if(n==0 || n==1) return n;
        int[] dp=new int[n+1];
        dp[1]=1;dp[2]=2;
        for(int i=3; i<n+1; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
```
19. http://leetcode.com/problems/maximum-subarray/description/
> Given an integer array nums, find the subarray with the largest sum, and return its sum

```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
```
```java []
class Solution {
    public int maxSubArray(int[] nums) {
        //kadane's algorithm
        int sum=0, max_sum=nums[0];
        for(int i=0; i<nums.length; i++) {
            sum+=nums[i];
            max_sum=Math.max(max_sum, sum);
            if(sum<0) sum=0;
        }
        return max_sum;
    }
}
```
20. http://leetcode.com/problems/counting-bits/description/
> Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
```
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
6 --> 110
7 --> 111
8 --> 1000
9 --> 1001
10 --> 1010
11 --> 1011
12 --> 1100
```
```java []
class Solution {
    public int[] countBits(int n) {
        //O(nlogn) solution
        // int[] ans=new int[n+1];
        // for(int i=1; i<=n; i++) {
        //     int count=0;
        //     int x=i;
        //     while(x!=0) {
        //         count+=(x&1);
        //         x=x>>1;
        //     }
        //     ans[i]=count;
        // }
        // return ans;

        if(n==0) return new int[]{0};
        int[] dp=new int[n+1];
        dp[1]=1;
        for(int i=1; i<=n/2; i++) {
            dp[i*2]=dp[i];
            if(i*2+1<=n)
                dp[i*2+1]=dp[i]+1;
        }
        return dp;
    }
}
```
21. https://leetcode.com/problems/range-sum-query-immutable/description/
> Given an integer array nums, handle multiple queries of the following type:
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:
NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
```
Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
```
```java []
class NumArray {

    int[] sumArray;

    public NumArray(int[] nums) {
        int n=nums.length;
        sumArray=new int[n];
        sumArray[0]=nums[0];
        for(int i=1; i<n; i++) {
            sumArray[i]=sumArray[i-1]+nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if(left==0) return sumArray[right];
        return sumArray[right]-sumArray[left-1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
```
22. https://leetcode.com/problems/letter-case-permutation/description/
> Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create. Return the output in any order.
```
Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
```
```java []
class Solution {

    List<String> ans;

    public void findPermutations(int ind, StringBuilder s) {
        if(ind>=s.length()) return ;
        if(s.charAt(ind)>='0' && s.charAt(ind)<='9') findPermutations(ind+1, s);
        else{
            StringBuilder mod=new StringBuilder(s);
            char ch=mod.charAt(ind);
            if(ch>='a' && ch<='z') {
                mod.setCharAt(ind, (char)(ch+'A'-'a'));
            } else {
                mod.setCharAt(ind, (char)(ch+'a'-'A'));
            }
            ans.add(mod.toString());
            findPermutations(ind+1, s);
            findPermutations(ind+1, mod);
        }
    }

    public List<String> letterCasePermutation(String s) {
        ans=new ArrayList<>();
        ans.add(s);
        findPermutations(0, new StringBuilder(s));
        return ans;
    }
}
```
23. https://leetcode.com/problems/subsets/description/
> Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
```
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```
```java []
class Solution {

    List<List<Integer>> ans=new ArrayList();

    public void findSubsets(int ind, int[] nums, List<Integer> subset) {
        if(ind==-1) {
            ans.add(subset);
            return ;
        }
        findSubsets(ind-1, nums, subset);
        List<Integer> newSubset=new ArrayList(subset);
        newSubset.add(nums[ind]);
        findSubsets(ind-1, nums, newSubset);
    }

    public List<List<Integer>> subsets(int[] nums) {
        findSubsets(nums.length-1, nums, new ArrayList<>());
        return ans;
    }
}
```
24. https://leetcode.com/problems/combinations/description/
> Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.

```
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
```
```java []
class Solution {

    List<List<Integer>> ans=new ArrayList();

    public void findSubSet(int num, int k, List<Integer> subset) {
        if(subset.size()==k) {
            ans.add(new ArrayList(subset));
            return ;
        }

        for(int i=num; i>=1; i--) {
            subset.add(i);
            findSubSet(i-1, k, subset);
            subset.remove(subset.size()-1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        findSubSet(n, k, new ArrayList());
        return ans;
    }
}
```
25. https://leetcode.com/problems/permutations/description/
> Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
```
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```
```java []
class Solution {

    List<List<Integer>> ans=new ArrayList();

    public void findPermutation(int ind, int[] nums, List<Integer> subset) {
        if(subset.size()==nums.length) {
            ans.add(new ArrayList(subset));
            return ;
        }

        for(int i=0; i<nums.length; i++) {
            if(nums[i]!=30) {
                subset.add(nums[i]);
                int temp=nums[i];
                nums[i]=30;
                findPermutation(i+1, nums, subset);
                nums[i]=temp;
                subset.remove(subset.size()-1);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        findPermutation(0, nums, new ArrayList());
        return ans;
    }
}
```
26. https://leetcode.com/problems/middle-of-the-linked-list/description/
> Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.
```
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
```
```java []
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast=head, slow=head;
        while(fast!=null && fast.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
}
```
27. https://leetcode.com/problems/linked-list-cycle/description/
> Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.
```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
```
```java []
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast=head, slow=head;
        while(fast!=null && fast.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow) return true;
        }
        return false;
    }
}
```
28. https://leetcode.com/problems/reverse-linked-list/description/
> Given the head of a singly linked list, reverse the list, and return the reversed list.
```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```
```java []
class Solution {

    public void printList(ListNode head) {
        for(; head!=null; head=head.next) System.out.print(head.val+" ");
        System.out.println();
    }

    public ListNode reverseList(ListNode head) {
        if(head==null) return head;
        ListNode root=head, prev=head;
        root=root.next;
        head.next=null;
        while(root!=null) {
            ListNode temp=root.next;
            root.next=prev;
            prev=root;
            root=temp;
        }
        return prev;
    }
}
```
29. https://leetcode.com/problems/remove-linked-list-elements/description/
> Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
```
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```
```java []
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        for(; head!=null && head.val==val; head=head.next);
        ListNode prev=head, root=head;
        for(; head!=null; head=head.next) {
            if(head.val==val) {
                prev.next=head.next;
                continue;
            }
            prev=head;
        }
        return root;
    }
}
```
30. https://leetcode.com/problems/reverse-linked-list-ii/description/
> Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
```
```java []
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode root=head, prev=head, leftBeforeNode=null, leftNode=head;
        int count=0;
        
        for(; root!=null; ) {
            count++;
            if(count==left-1) {
                leftNode=root.next;
                leftBeforeNode=root;
            }
            if(count==left) prev=root;
            if(count>left && count<=right) {
                if(count==right) {
                    if(leftNode!=head) {
                        leftBeforeNode.next=root;
                    } else {
                        head=root;
                    }
                    leftNode.next=root.next;
                }
                ListNode temp=root.next;
                root.next=prev;
                prev=root;
                root=temp;
                continue;
            }
            root=root.next;
        }
        return head;
    }
}
```
31. https://leetcode.com/problems/palindrome-linked-list/description/
> Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
```
Input: head = [1,2,2,1]
Output: true
```
```java []
class Solution {
    public void printLinkedList(ListNode head) {
        System.out.println("Slow");
        for( ;head!=null; head=head.next) System.out.print(head.val);
    }


    public boolean isPalindrome(ListNode head) {
        if(head.next==null) return true;
        //Reverse till middle and compare whether both slow and fast pointers hold same value
        ListNode slow=head, fast=head, prev=null;
        while(fast!=null && fast.next!=null) {
            fast=fast.next.next;
            //Add Reverse logic for slow ptr
            ListNode temp=slow.next;
            slow.next=prev;
            prev=slow;
            slow=temp;
        }
        ListNode left=prev, right=slow;
        if(fast!=null) right=slow.next; //For odd array
        while(left!=null && right!=null) {
            if(left.val!=right.val) return false;
            left=left.next;
            right=right.next; 
        }
        return left==null && right==null;
    }
}
```
32. https://leetcode.com/problems/merge-two-sorted-lists/description/
> You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.
```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```
```java []
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null) return list2;
        if(list2==null) return list1;
        ListNode head=list1.val<=list2.val?list1:list2, root=head;
        if(head==list1) list1=list1.next;
        else list2=list2.next;
        while(list1!=null && list2!=null) {
            if(list1.val<=list2.val) {
                root.next=list1;
                list1=list1.next;
            } else {
                root.next=list2;
                list2=list2.next;
            }
            root=root.next;
        }
        if(list1!=null) root.next=list1;
        if(list2!=null) root.next=list2;
        return head;
    }
}
```
33. https://leetcode.com/problems/min-stack/description/
> Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class:
MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

```
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
```
```java []
class MinStack {

    Node top;

    public MinStack() {
        top=null;
    }
    
    public void push(int val) {
        int minEle=val;
        if(top==null) {
            top=new Node(val, minEle);
            return ;
        }
        minEle=Math.min(val, top.min);
        Node newNode=new Node(val, minEle); 
        newNode.prev=top;
        top=newNode;
    }
    
    public void pop() {
        top=top.prev;
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        return top.min;
    }
}

class Node {
    int val,min;
    Node prev;
    Node(int val, int min) {
        this.val=val;
        this.min=min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```
34. https://leetcode.com/problems/valid-parentheses/description/
> Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
```
Input: s = "([])"
Output: true
```
```java []
class Solution {
    public boolean isValid(String s) {
        char[] ch=s.toCharArray();
        int n=s.length();
        int top=-1;
        char[] stack=new char[n];
        Map<Character, Character> parMap=Map.of(')','(',']','[','}','{');
        for(int i=0; i<n; i++) {
            if(List.of('(','[','{').contains(ch[i])) {
                stack[++top]=ch[i];
            } else {
                if(top==-1 || stack[top]!=parMap.get(ch[i])) return false;
                top--;
            }
        }
        return top==-1;
    }
}
```
35. https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
> You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
Evaluate the expression. Return an integer that represents the value of the expression.
Note that:
The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.
```
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
```
```java []
class Solution {
    public int evalRPN(String[] tokens) {
        //LinkedList implementation of stack
        Node top=null;
        for(String token:tokens) {
            if("+-*/".contains(token)) {
                int val2=top.val;
                top=top.prev;
                int val1=top.val;
                top=top.prev;
                int ans=0;
                switch(token) {
                    case "+" : ans=val1+val2; break;
                    case "-" : ans=val1-val2; break;
                    case "*" : ans=val1*val2; break;
                    case "/" : ans=val1/val2; break;
                }
                Node temp=new Node(ans);
                temp.prev=top;
                top=temp;
            } else {
                Integer val=Integer.valueOf(token);
                Node temp=new Node(val);
                temp.prev=top;
                top=temp;
            }
        }
        return top.val;
    }
}

class Node {
    int val;
    Node prev;
    Node(int val) {
        this.val=val;
    }
}
```
37. https://leetcode.com/problems/implement-stack-using-queues/description/
> Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
Implement the MyStack class:
void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:
You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
```
Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
```
```java []
class MyStack {

    Queue<Integer> q;

    public MyStack() {
        q=new LinkedList();
    }
    
    public void push(int x) {
        q.add(x);
    }
    
    public int pop() {
        int n=q.size();
        for(int i=0; i<n-1; i++) {
            q.add(q.poll());
        }
        return q.poll();
    }
    
    public int top() {
        int n=q.size();
        for(int i=0; i<n-1; i++) {
            q.add(q.poll());
        }
        int ans=q.peek();
        q.add(q.poll());
        return ans;
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```
38. https://leetcode.com/problems/time-needed-to-buy-tickets/description/
> There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
Return the time taken for the person initially at position k (0-indexed) to finish buying tickets.
```
Input: tickets = [2,3,2], k = 2

Output: 6

Explanation:

The queue starts as [2,3,2], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [3,2,1] at 1 second.
Continuing this process, the queue becomes [2,1,2] at 2 seconds.
Continuing this process, the queue becomes [1,2,1] at 3 seconds.
Continuing this process, the queue becomes [2,1] at 4 seconds. Note: the person at the front left the queue.
Continuing this process, the queue becomes [1,1] at 5 seconds.
Continuing this process, the queue becomes [1] at 6 seconds. The kth person has bought all their tickets, so return 6.
```
```java []
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int n=tickets.length, ans=0, threshold=tickets[k];
        for(int i=0; i<n; i++) {
            if(i>k) ans+=Math.min(tickets[i], threshold-1);
            else ans+=Math.min(tickets[i], threshold);
        }
        return ans;
    }
}
```
39. https://www.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1
> Given an integer k and a queue of integers, we need to reverse the order of the first k elements of the queue, leaving the other elements in the same relative order.
Only following standard operations are allowed on queue.
enqueue(x) : Add an item x to rear of queue
dequeue() : Remove an item from front of queue
size() : Returns number of elements in queue.
front() : Finds front item.
Note: The above operations represent the general processings. In-built functions of the respective languages can be used to solve the problem.
```
Input: q = [1, 2, 3, 4, 5], k = 3
Output: [3, 2, 1, 4, 5]
Explanation: After reversing the first 3 elements from the given queue the resultant queue will be 3 2 1 4 5
```
```java []
class Solution {
    public Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        if(k>q.size()) return q;
        Stack<Integer> st=new Stack();
        Queue<Integer> ans=new LinkedList();
        int co=0;
        for(;co<k;co++) st.push(q.poll());
        while(!st.empty()) ans.add(st.pop());
        while(!q.isEmpty()) ans.add(q.poll());
        return ans;
    }
}
```
40. https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
> Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
```
Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].
```
```java []
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q=new LinkedList();
        q.add(root);
        List<Double> ans=new ArrayList();
        int co=1;
        while(!q.isEmpty()) {
            int tempCo=0, dupCo=co;
            Double sum=0.0;
            for(;co!=0;co--) {
                TreeNode curr=q.poll();
                sum+=curr.val;
                if(curr.left!=null) {
                    q.add(curr.left);
                    tempCo++;
                }
                if(curr.right!=null) {
                    q.add(curr.right);
                    tempCo++;
                }
            }
            ans.add(sum/dupCo);
            co=tempCo;
        }
        return ans;
    }
}
```
41. https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
> Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.
```
Input: root = [3,9,20,null,null,15,7]
Output: 2
```
```java []
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> q=new LinkedList();
        q.add(root);
        int co=1, depth=1;
        while(!q.isEmpty()) {
            int tempCo=0;
            for(; co!=0; co--) {
                TreeNode curr=q.poll();
                if(curr.left==null && curr.right==null) {
                    return depth;
                }
                if(curr.left!=null) {
                    q.add(curr.left);
                    tempCo++;
                }
                if(curr.right!=null) {
                    q.add(curr.right);
                    tempCo++;
                }
            }
            depth++;
            co=tempCo;
        }   
        return 0;
    }
}
```
43. https://leetcode.com/problems/binary-tree-level-order-traversal/description/
> Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```
```java []
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList();
        Queue<TreeNode> q=new LinkedList();
        List<List<Integer>> ans=new ArrayList();
        q.add(root);
        int co=1;
        while(!q.isEmpty()) {
            int tempCo=0;
            List<Integer> temp=new ArrayList();
            for(; co!=0; co--) {
                TreeNode curr=q.poll();
                temp.add(curr.val);
                if(curr.left!=null) {
                    q.add(curr.left);
                    tempCo++;
                }
                if(curr.right!=null) {
                    q.add(curr.right);
                    tempCo++;
                }
            }
            ans.add(temp);
            co=tempCo;
        }
        return ans;
    }
}
```
44. https://leetcode.com/problems/same-tree/description/
> Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```
```java []
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null) return false;
        if(q==null) return false;

        if(p.val!=q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```
45. https://leetcode.com/problems/path-sum/description/
> Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.
```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
```
```java []
class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        if(root.left==null && root.right==null && targetSum-root.val==0) return true;

        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }
}
```
46. https://leetcode.com/problems/diameter-of-binary-tree/description/
> Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.
```
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
```
```java []
class Solution {
    int diameter=0;
    public int maxLevel(TreeNode root) {
        if(root==null) return 0;
        int left=maxLevel(root.left);
        int right=maxLevel(root.right);
        diameter=Math.max(diameter, left+right);

        return Math.max(left, right)+1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxLevel(root);
        return diameter;
    }
}
```
47. https://leetcode.com/problems/invert-binary-tree/description/
> Given the root of a binary tree, invert the tree, and return its root.
```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```
```java []
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        TreeNode temp=invertTree(root.left);
        root.left=invertTree(root.right);
        root.right=temp;
        return root;
    }
}
```
48. https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
> Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```
```java []
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root.val==p.val || root.val==q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left==null) return right;
        if(right==null) return left;
        if((left.val==p.val && right.val==q.val) || (left.val==q.val && right.val==p.val)) return root;
        return null;
    }
}
```
49. https://leetcode.com/problems/search-in-a-binary-search-tree/description/
> You are given the root of a binary search tree (BST) and an integer val.
Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
```
Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]
```
```java []
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null) return null;
        if(root.val==val) return root;
        if(val<root.val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }
}
```
50. https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
> You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
```
Input: root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]
```
```java []
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);
        if(root.val<val) root.right=insertIntoBST(root.right, val);
        else root.left=insertIntoBST(root.left, val);
        return root;
    }
}
```
51. https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
> Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
```
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
```
```java []
class Solution {
    public TreeNode insertBST(int st, int end, int[] nums) {
        if(st>end) return null;
        int mid=st+(end-st)/2;
        TreeNode node=new TreeNode(nums[mid]);
        node.left=insertBST(st, mid-1, nums);
        node.right=insertBST(mid+1, end, nums);
        return node;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        int n=nums.length;
        return insertBST(0, n-1, nums);
    }
}
```
52. https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
> Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
```
Input: root = [5,3,6,2,4,null,7], k = 9
Output: true
```
```java []
class Solution {
    public boolean search(TreeNode root, int val, TreeNode curr) {
        if(root==null) return false;
        if(root!=curr && root.val==val) return true;
        if(val<root.val) return search(root.left, val, curr);
        else return search(root.right, val, curr);
    }
    
    public boolean findSum(TreeNode root, TreeNode curr, int k) {
        if(curr==null) return false;
        int diff=k-curr.val;
        if(search(root, diff, curr)) return true;
        return findSum(root, curr.left, k)||findSum(root, curr.right, k);
    }
    
    public boolean findTarget(TreeNode root, int k) {
        return findSum(root, root, k);
    }
}
```
53. https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
> Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
```
```java []
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root.val>p.val && root.val>q.val) return lowestCommonAncestor(root.left, p, q);
        if(root.val<p.val && root.val<q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
```
54. https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
> Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
```
Input: root = [4,2,6,1,3]
Output: 1
```
```java []
class Solution {

    int prev=-1, ans=Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if(root==null) return 0;
        getMinimumDifference(root.left);
        if(prev!=-1) ans=Math.min(ans, root.val-prev);
        prev=root.val;
        getMinimumDifference(root.right);
        return ans;
    }
}
```
55. https://leetcode.com/problems/balance-a-binary-search-tree/description/
> Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
```
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
```
```java []
class Solution {

    List<Integer> sortedArr=new ArrayList();

    public TreeNode convertSortedArrToBST(int st, int end) {
        if(st>end) return null;
        int mid=st+(end-st)/2;
        TreeNode node=new TreeNode(sortedArr.get(mid));
        node.left=convertSortedArrToBST(st, mid-1);
        node.right=convertSortedArrToBST(mid+1, end);
        return node;
    }

    public void traverseTree(TreeNode root) {
        if(root==null) return ;

        traverseTree(root.left);
        sortedArr.add(root.val);
        traverseTree(root.right);
    }

    public TreeNode balanceBST(TreeNode root) {
        traverseTree(root);
        return convertSortedArrToBST(0, sortedArr.size()-1);
    }
}
```
56. https://leetcode.com/problems/delete-node-in-a-bst/description/
> Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
Basically, the deletion can be divided into two stages:
Search for a node to remove.
If the node is found, delete the node.
```
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
```
```java []
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return root;
        if(root.val==key) {
            if(root.left==null) return root.right;
            if(root.right==null) return root.left;
            TreeNode temp=root;
            for(temp=temp.right ;temp.left!=null; temp=temp.left);
            root.val=temp.val;
            root.right=deleteNode(root.right, root.val);
            return root;
        }
        if(root.val>key) {
            root.left=deleteNode(root.left, key);
        }
        else{
            root.right=deleteNode(root.right, key);
        }
        return root;
    }
}
```
57. https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
> Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
```
Input: root = [3,1,4,null,2], k = 1
Output: 1
```
```java []
class Solution {
    int count=0;
    public int kthSmallest(TreeNode root, int k) {
        if(root==null) return -1;
        int leftTree = kthSmallest(root.left, k);
        if(leftTree!=-1) return leftTree;
        count++;
        if(count==k) return root.val;
        int rightTree = kthSmallest(root.right, k);
        return rightTree!=-1?rightTree:-1;
    }
}
```
58. 
