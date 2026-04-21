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
2. How to make sure producers are producing in same partition so that consmers get the messages in order. (By having unique key for each partition in the header in Producer. So that the message goes in to the unique partition for unique key) 

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


### **3 April Interview**

#### Round 1

1. DSA - Leetcode 875 - https://leetcode.com/problems/koko-eating-bananas/description/
2. LLD -

Design a pub-sub system in java. 

**Requirements**

The Pub-Sub system should allow pblishers to publish messages to specific topics.<br/>
Subscribers should be able to subscribe to topics of interest and receive messages published to those topics. <br/>
The system should support multiple publishers and subscribers.<br/>
Messages shold be delivered to all subscribers of a topic in real-time.
3. kafka
 
- What are scenarios in which rebalancing of consumers will happen?
  - Answered as when start of the application, consumers will get attached. When a consumer leaves the group and when a new consumer joins the group
  - Questioned as What do you think when a consumer is taking longer time to process (Got the hint for previous question)
  - Answered as In that case, Consumer will fail to commit the latest offset to the co-ordinator as it is taking longer time to process (when code is cofigured to have lazy commit). The co-ordinator assumes that the consumer is dead and the rebalancing of consumers happens and the partition will be allocated to another consumer.
  - Questioned as What happened to the already processed messages in the old/time-taking consumer?
  - Answered as Either we need to parallely process the messages or we need to increase the commit time in configuration to avoid thia scenario. Already processed messages, as they are not committed, they will again be processed by different consumer leading to duplicate processing of messages.
- 

Verdict - Rejected (I felt like I didn't give LLD upto the mark even though I have DSA and answered kafka follow-ups)

### **10 April Interview - Dipayan**

#### Round 1
1. Given a linked list, output interlinked linkedlist

Ex : L1 : a->b->c
     L2 : x->y->z
     O/P : a->x->b->y->c->z

2. Given a tree, Find the maximum root to leaf sum

Ex :

               5
             /   \
            11    54
           /  \
          20  15
             /  \
            1    3

Ans: 59 (5->54)

#### Round 2

1. What is the component which you are working on in your current team 
2. How did you convince your team on differencing opinion and resolve conflict

1. Design URL Shortener (Wmlink). Assume 2.2M users use it. And there are 5M daily hits.
2. How does Wmlink got resolved without any domain. Will load balancer take care of this action
3. For faster retrieval, Will you use cache or indices in DB.
4. I got stumbled in Back of the envelope estimation. Interviewers asked me to think of the scale and come up with the number estimates which I failed. (Need to learn)

1. Asked me to write code for encoder and decoder part. Given a secret, encode the string. I have written the simple playfair cipher in java.

1. Got very disappointed with the performance in System design round. I even said the interviewers that it was my first SD round though I am preparing for such rounds. I am remembering some concepts but what I am lacking is the flow and co-relate things and deliver.

### **16 April Interview - Sujit**

#### Round 1

**SQL**

1. Given Employee Table with Manager ID and Senior Manager ID. Print the Employee and Senior Manager ID
        <br/> **Table**<br/>
      EmployeeId, Name, ManagerId, DepartmentId, Salary
      1, 'Ram', 2, 1, 10000
      3, 'Raghu', null, 2, 200000
      2, 'Arun', 3, 1, 150000
   

Questions:
1. What is the situation you have faced production issue. 
2. A : Explained about the app slowness issue faced because of Heap overflow
3. Q : What is the tool used  to identify the heap overflow . (A: Forgot the tool name but used it to identify the object which is causing heap dump and fixed it) -> Vulnerable here (Gather some points and get stronger)
4. Q : What is the legacy components used. (A: App is in OnPrem Servers. We have Mainframe dependencies. We are hvaing an effort to eliminate it)
5. Q : While migrating to Springboot/WCNP, How did handle SQL queries (Did you have raw queries or used any other ways) [A: Legacy used hibernate, we incorporated same during migration as well]
6. 
kafka
1. How did you handle kafka in your application
WCNP 
1. Have you done autoscaling of pods? (Learn abt autoscaling. Blabbered something like tried to autoscale but faced latency issues. So turned off the feature)
2. Q: When you autoscale, How to handle two pods don't get similar messages in kafka
3. A: By having same consumerGroups in all consumers. Same Messages won't get consumed in all consumers in same consumer group
Springboot
1. What do you mean by Inversion Of control (Blabbered smthng like responsibility of bean creation is given back to the Class instead of Client)

