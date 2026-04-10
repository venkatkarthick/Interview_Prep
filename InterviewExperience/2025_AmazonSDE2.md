### **Amazon**

Behavioural
1. Tell me an instance where you have gone beyond your role
    * Told about kafka implementation

DSA
1. Find kth largest element in an array
2. Given a tree, Convert into BST without altering its structure
    * Traverse the tree and store elements in a list and sort it
    * Now again traverse the tree in preorder and fill out the elements. (Then we will get BST with the same tree)

### **Yubi - Senior software engineer**

DSA
1. Implement Queue using stacks
Streams
1. stateful and stateless in streams
2. Remove duplicates in an array using streams(distinct)
3. Count each characters in a list of strings using streams

Springboot
1. Some Annotations in springboot
2. What annotations are contained in a @Springboot annotation

Java
1. Can main method be overloaded

Kubernetes
1. How Autoscaling works

### **Walmart DSA**


1. What is difference b/w array and linkedlist
2. Hashmap collisions techniques
3. How hashmaps In java STL works internally - Not answered

**Debugging**
1. Map<String, String> mp=new Map();
   Mp.put(null, 1) -> will it accept, yes
2.
```java
Class Key{
    int id;
    Key(int id) {
        this.id=id;
    }
    @Override
    public int hashcode() {
        return 1;
    }
}
Class Main{
    Main() {
        Map<Key, Integer> mp=new Map();
        mp.put(new key(1), 2);
        mp.put(new key(2), 2);
        Sout(mp.size());
    }
}
```

Op => 2. .equals method needs to be overriden to make it to size 1 (Wrongly answered)

3.
```java
String a=new String("abc");
 String b=new String("abc");
 Map<String, Integer> mp=new Map();
mp.put(a, 1);
mp.put(b,1);
Sout(mp.size());
 ```

Op=> 1. .equals method check each characters

**DSA**

https://www.geeksforgeeks.org/dsa/minimum-number-platforms-required-railwaybus-station/ - Greedy
