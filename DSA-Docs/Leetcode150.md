1. https://leetcode.com/problems/implement-trie-prefix-tree/?envType=study-plan-v2&envId=top-interview-150

>A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.<br>
Implement the Trie class:<br>
Trie() Initializes the trie object.<br>
void insert(String word) Inserts the string word into the trie.<br>
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.<br>
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

```
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
```
```java
class TrieNode {
    TrieNode[] nextPtrs= new TrieNode[26];
    boolean hasFullWord=false;
}

class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode temp=root;
        for(int i=0; i<word.length(); i++) {
            int index=word.charAt(i)-'a';
            if(temp.nextPtrs[index]==null) {
                temp.nextPtrs[index]=new TrieNode();
            }
            temp=temp.nextPtrs[index];
        }
        temp.hasFullWord=true;
    }
    
    public boolean search(String word) {
        TrieNode temp=root;
        for(int i=0; i<word.length(); i++) {
            int index=word.charAt(i)-'a';
            if(temp==null || temp.nextPtrs[index]==null) {
                return false;
            }
            temp=temp.nextPtrs[index];
        }
        return temp.hasFullWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode temp=root;
        for(int i=0; i<prefix.length(); i++) {
            int index=prefix.charAt(i)-'a';
            if(temp==null||temp.nextPtrs[index]==null) {
                return false;
            }
            temp=temp.nextPtrs[index];
        }
        return true;
    }
}
```