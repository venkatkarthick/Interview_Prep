Easy
1. https://leetcode.com/problems/defanging-an-ip-address/description/
> Given a valid (IPv4) IP address, return a defanged version of that IP address.
A defanged IP address replaces every period "." with "[.]".
```
Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
```
```java []
class Solution {
    public String defangIPaddr(String address) {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<address.length(); i++) {
            if(address.charAt(i)=='.') {
                builder.append("[.]");
            } else {
                builder.append(address.charAt(i));
            }
        }
        return builder.toString();
        //return address.replace(".", "[.]");
    }
}
```
2. https://leetcode.com/problems/shuffle-string/description/
> You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
Return the shuffled string.
```
Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
```
```java []
class Solution {
    public String restoreString(String s, int[] indices) {
        int n=indices.length;
        char[] ans=new char[n];
        for(int i=0; i<n; i++) {
            ans[indices[i]]=s.charAt(i);
        }
        return new String(ans);
    }
}
```
3. https://leetcode.com/problems/goal-parser-interpretation/description/
> You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings are then concatenated in the original order.
Given the string command, return the Goal Parser's interpretation of command.
```
Input: command = "G()(al)"
Output: "Goal"
Explanation: The Goal Parser interprets the command as follows:
G -> G
() -> o
(al) -> al
The final concatenated result is "Goal".
```
```java []
class Solution {
    public String interpret(String command) {
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<command.length();) {
            if(command.charAt(i)=='(') {
                if(command.charAt(i+1)=='a') {
                    ans.append("al");
                    i+=4;
                } else {
                    ans.append("o");
                    i+=2;
                }
            } else {
                ans.append(command.charAt(i)); i++;
            }
        }
        return ans.toString();
    }
}
```
4. 