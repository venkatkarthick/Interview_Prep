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
 