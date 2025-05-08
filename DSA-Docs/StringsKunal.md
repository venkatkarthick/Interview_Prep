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
4. https://leetcode.com/problems/count-items-matching-a-rule/description/
> You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color, and name of the ith item. You are also given a rule represented by two strings, ruleKey and ruleValue.
The ith item is said to match the rule if one of the following is true:
ruleKey == "type" and ruleValue == typei.
ruleKey == "color" and ruleValue == colori.
ruleKey == "name" and ruleValue == namei.
```
Input: items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
Output: 1
Explanation: There is only one item matching the given rule, which is ["computer","silver","lenovo"].
```
```java []
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int rule = ruleKey.equals("type") ? 0 : (ruleKey.equals("color") ? 1 : 2);
        int count = 0;
        for(List<String> item : items) {
            if(item.get(rule).equals(ruleValue)) count++;
        }
        return count;
    }
}
```
5. https://leetcode.com/problems/sorting-the-sentence/description/
> A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of lowercase and uppercase English letters.
A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging the words in the sentence.
For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.
```
Input: s = "is2 sentence4 This1 a3"
Output: "This is a sentence"
Explanation: Sort the words in s to their original positions "This1 is2 a3 sentence4", then remove the numbers.
```
```java []
class Solution {
    public String sortSentence(String s) {
        String[] sArr=s.split(" ");
        int n=sArr.length;
        StringBuilder[] ans = new StringBuilder[n];
        for(int i=0; i<n; i++) {
            int m=sArr[i].length()-1, index=(int)(sArr[i].charAt(m)-'0')-1;
            ans[index]=new StringBuilder(sArr[i]);
            ans[index].setCharAt(m, ' ');
        }
        StringBuilder sentence=new StringBuilder();
        for(StringBuilder word : ans) {
            sentence.append(word);
        }
        return sentence.toString().strip();
        //return Arrays.stream(ans).map(StringBuilder::toString).collect(Collectors.joining()).strip();
    }
}
```