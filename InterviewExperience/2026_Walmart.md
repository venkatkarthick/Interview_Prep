### **Jan Interview - Kunal**

1. What are the different probes in WCNP(Readiness probe, liveness probe)

**Java**

1. Difference between HashTable, HashMap and Concurrent Hashmaps.
2. Internals of HashMap. How concurrency is handled in concurrent hashmaps

**Springboot**

1. When two classes have same names. During Dependency Injection(Autowiring), how will you specify which class's object to autowire.
2. 

**kafka**

1. What is Rebalancing?
2. If there are 5 consumers and 4 partitions, what will be the 5th consumer doing? (Idle)

**DSA**
1. https://leetcode.com/problems/minimum-genetic-mutation/description/

(Failed)

### **17 March Interview - Nitin**

#### Round 1
**DSA**
1. Write a Movie rating Class with following functions
   1. like a video - videoId(Str) void
   2. dislike a video - videoId(Str) void
   3. most rated video at given time - noArg String
   
Answer given->

(Had a map and priority queue and implemented with O(logn), O(logn), O(1) complexity)
```java
import java.util.HashMap;import java.util.PriorityQueue;class MovieratingService {
    public class MovieRating{
        String videoId;
        String rating;
        MovieRating(String videoId, int rating) {
            this.rating=rating;
            this.videoId=videoId;
        }    
    }
    Map<String, MovieRating> MovieMap= new HashMap<>();
    Queue<MovieRating> priorityQueue = new PriorityQueue<>(
        (a,b) -> {return Integer.compare(b.rating, a.rating);}
    );
    public void like(String video) {
        if(!MovieMap.containsKey(video)) {
            MovieRating newMovieRating = new MovieRating(video, 1);
            MovieMap.put(video, newMovieRating);
            priorityQueue.add(newMovieRating);
        } else {
            MovieRating movieRating=MovieMap.get(video);
            priorityQueue.remove(movieRating);
            movieRating.rating++;
            priorityQueue.add(movieRating);
        }
    }    
    public void dislike(String video) {
        if(!MovieMap.containsKey(video)) {
            MovieRating newMovieRating = new MovieRating(video, -1);
            MovieMap.put(video, newMovieRating);
            priorityQueue.add(newMovieRating);
        } else {
            MovieRating movieRating=MovieMap.get(video);
            priorityQueue.remove(movieRating);
            movieRating.rating--;
            priorityQueue.add(movieRating);
        }
    }    
    public String mostRatedVideo() {
        return priorityQueue.peek().videoId; 
    }
}
```
   
- Discussed about possible test cases. Told about user only disliking the videoes so that in the answer to show the least disliked video.

**LLD**
1. Design a event booking system

- Asked to define requirement, API Contract and Database schema
- Failed to explain the HTTP error codes

**Kafka**
1. What is a partition

#### Round 2 (HM)
1. How to scale the below code of counting character in a string.
- Asked to write the count array logic
- We have a large string and we try to load it from DB.
- Parallel processing is agreed to be correct option but how to achieve it.
- countArray array cannot be shared by all the threads. How to optimise it if we are dividing the problem and aggregating the answer at the end.
- Can Hashmap be used instead of array?

```java
import java.util.Arrays;

public void countCharacters(String word) {
    int[] countArray = new int[26];
    for (Character c : word.toCharArray()) {
        countArray[c - 'a']++;
    }
    System.out.println(Arrays.toString(countArray));
}
```