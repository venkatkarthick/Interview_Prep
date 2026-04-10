# Typehead Suggestion

## ✅ 1. What is Typeahead Suggestion?

**Typeahead**, or **auto-suggest**, is a service that shows **live search recommendations** as a user types a query.

* Example: Typing `cap` → get `cap`, `capital`, `captain`, `caption`
* Helps **guide user intent**, improves query formulation
* Use cases: YouTube, Google, Amazon, Twitter, etc.

---

## 🎯 2. System Requirements

### ✅ Functional Requirements

* Show **top 10 matching suggestions** for a given input prefix
* Suggestions must be **sorted by popularity** (i.e., frequency of previous searches)

### ⏱️ Non-Functional Requirements

* **<200ms latency** for suggestions
* **Real-time performance**
* Should support **millions of terms** with **low memory footprint**

---

## 🌳 3. Core Data Structure: Trie (Prefix Tree)

A **Trie** is a tree where:

* Each node represents a **character**
* Path from root to leaf represents a **word**
* Efficient for **prefix lookups**

### Example:

To store: `cap, captain, capital, cat, caption`

Trie structure:

```
        c
       / 
      a
     / \
    p   t
   / \    \
  t   i    i
  a   a     o
  i   l      n
  n          (end)
  l
```

#### Benefits of Trie

* Search for all terms with a prefix in **O(P)** time (P = length of prefix)
* No full table scan needed

---

### 🔥 1. Frequency-Based Suggestions

#### Problem:

Prefix may map to **hundreds of completions**. We want **top 10 most popular**.

#### ✅ Solution:

* Store **search frequency** at **terminal nodes**
* Store **top 10 suggestions** at **each intermediate node** of the Trie

##### Example:

At node `cap`, store:

```json
[
  ("caption", 520),
  ("capital", 300),
  ("captain", 150),
  ("cap", 90)
]
```

#### ➕ Benefit:

* Real-time lookup: return suggestions from precomputed list at node
* Avoid traversal of large subtrees

#### ➖ Tradeoff:

* Increased memory usage: storing top 10 at every node
* We can **store pointers to terminal nodes** instead of full strings to reduce space

---

### 🔁 2. Trie Node Structure

Each node can have:

```java
class TrieNode {
    Map<Character, TrieNode> children;
    List<Suggestion> topSuggestions; // top 10 terminal nodes
    TrieNode parent;
    boolean isEndOfWord;
    int frequency; // only meaningful for terminal node
}
```

To reconstruct a suggestion string:

* Follow `parent` references from terminal node → root

---

### ⚙️ 3. Building the Trie (Bottom-Up Construction)

#### 🔧 How it works:

We **recursively build the Trie bottom-up**, starting from terminal nodes:

* Each **terminal node** (end of a word) stores:

    * The **word** (or a pointer)
    * Its **frequency count**
* Each **parent node**:

    * Recursively collects **top suggestions from all children**
    * Merges them to maintain its **top 10 list**

This approach avoids repeated computation and ensures all parent nodes maintain accurate top suggestions.

#### ✅ Advantages:

* Avoids top-down recomputation
* Easily parallelizable during offline batch rebuilds

---

### 🔄 4. Updating the Trie Efficiently

#### 💡 Real-time Updating is Expensive

* With \~5 **billion queries/day (\~60K/sec)**, updating the trie live for every query would:

    * Cause **resource contention**
    * Slow down **read latency**
    * Increase **locking overhead**

#### 🕒 Solution: **Periodic Offline Updates**

Instead of real-time updates:

* **Log user queries** (sampled or full)
* Periodically **process logs in batch**
* Use **MapReduce** to:

    * Aggregate counts
    * Generate diffs
    * Rebuild or incrementally update the trie

---

### 🗃️ 5. Logging & Frequency Collection

#### Two options:

* **Full Logging**: Every search query is logged (for smaller systems)
* **Sampled Logging**: Log every 1000th query (if goal is to ignore terms <1000 freq)

#### MapReduce Example:

* Map Phase: Emit `<term, 1>` from logs
* Reduce Phase: Sum frequencies for each term → `<term, totalFreq>`

---

### 🔁 6. Trie Update Deployment Strategies

We have two deployment options:

#### 🔁 Option 1: **Copy & Swap**

* Make a **copy of the in-memory trie**
* Update it offline (merge new terms, update frequencies)
* When ready, **atomically swap** the old trie with the new one

#### 🧑‍🤝‍🧑 Option 2: **Master-Slave Rotation**

* Each server has **master + slave trie**
* While master handles live traffic:

    * Slave is updated
* Once update completes:

    * Slave becomes new master
    * Old master becomes new slave

#### ✅ Pros:

* Zero-downtime
* No interference with reads
* Fast swaps (atomic pointer switch)

---

### 📈 7. Frequency Updates – Exponential Moving Average (EMA)

Rather than recalculating frequencies from scratch:

* Use **EMA** to give higher weight to recent searches

#### Formula:

```
EMA_today = α × F_today + (1 - α) × EMA_yesterday
```

* α = smoothing factor (e.g., 0.3)
* EMA adjusts smoothly over time
* Avoids outdated terms lingering in top 10

---

### 📤 8. How to Update Top-10 Suggestions

When a new term is inserted or its frequency increases:

* Go to the **terminal node**
* Traverse **up the path** to the root
* At each node:

    * Check if the term is already in top 10

        * ✅ Yes: update frequency
        * ❌ No: check if new frequency > current min

            * If yes, **insert it**, **evict the lowest**

This is done **bottom-up**, so only the prefix path is touched.

---

### ❌ 9. Removing a Term (e.g., Legal/Policy)

To **remove banned terms** (hate speech, piracy, etc.):

#### 🔒 Soft Block:

* Add a **filtering layer** before responding to user:

    * On trie servers or at API gateway
    * Blocklisted terms are not returned

#### 🧹 Hard Delete:

* During **next trie update** (offline batch job):

    * Remove term from trie structure
    * Clean up from all top-10 lists

---

### 📊 10. Ranking Criteria – Beyond Frequency

#### ➕ Additional Factors:

1. **Frequency** – Total number of searches
2. **Freshness** – Recency of search (via EMA or decay functions)
3. **Personalization** – User's past search behavior
4. **Geo-location** – Popularity in user’s region
5. **Language/Demographics** – e.g., Hindi terms for Indian users
6. **Device Type** – Mobile vs Desktop suggestions

---

### ✍️ 9. Final Trie Node Structure (Hydrated)

```python
class TrieNode:
    char: str
    children: Dict[char, TrieNode]
    topSuggestions: MinHeap[Suggestion]  # top-10 with frequency
    isTerminal: bool
    frequency: int  # valid only for terminal nodes
    parent: TrieNode
```

---

### 🔚 TL;DR: Trie Building & Maintenance Summary

| Aspect           | Approach                                               |
| ---------------- | ------------------------------------------------------ |
| Trie Build       | Bottom-up, recursive aggregation of top-10 suggestions |
| Realtime Update  | Avoided due to high QPS; use periodic batch update     |
| Log Processing   | Use MapReduce to collect frequencies                   |
| Deployment       | Copy & swap or Master-Slave rotation                   |
| Frequency Update | Use EMA to prioritize recent searches                  |
| Top Suggestions  | Updated during insert via parent-path traversal        |
| Term Removal     | Filter + offline removal                               |
| Ranking Factors  | Frequency, recency, personalization, geo, language     |

---


## 🔄 4. Permanent Storage of the Trie

To ensure **persistence** (i.e., recovery after a crash), we need to **serialize the Trie to disk** and **deserialize it** when a server restarts.

---

#### ✅ Java Serialization Format (Level-order Traversal)

We store the trie in **level-order**, each node containing:

* `char ch`
* `int childCount`
* followed by all children nodes in order

#### 🧠 Example

For a trie storing:

```
car
cat
cop
cod
```

Java-style serialized representation (prettified):

```
['c', 1],   // root 'c' with 1 child
['a', 2],   // 'a' with 2 children
['r', 0],   // leaf node (car)
['t', 0],   // leaf node (cat)
['o', 2],   // 'o' with 2 children
['p', 0],   // cop
['d', 0]    // cod
```

---

#### 🛠️ Java Code – Serialize Trie

```java
class TrieNode {
    char ch;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isTerminal;
    
    public TrieNode(char ch) {
        this.ch = ch;
    }
}

public class TrieSerializer {
    public void serialize(TrieNode root, DataOutputStream out) throws IOException {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            out.writeChar(node.ch);
            out.writeInt(node.children.size());
            
            for (TrieNode child : node.children.values()) {
                queue.add(child);
            }
        }
    }

    public TrieNode deserialize(DataInputStream in) throws IOException {
        TrieNode root = new TrieNode(in.readChar());
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            int childCount = in.readInt();

            for (int i = 0; i < childCount; i++) {
                char childChar = in.readChar();
                TrieNode childNode = new TrieNode(childChar);
                node.children.put(childChar, childNode);
                queue.add(childNode);
            }
        }
        return root;
    }
}
```

---

#### 🔄 Rebuilding Top Suggestions During Load

Since we’re not storing **top 10 terms at each node** in the file (due to the recursive child dependency), we **rebuild them bottom-up** on deserialization.

👉 After building the Trie from file, do a **post-order traversal**, and:

* Each node aggregates top 10 from its children
* Merge-sorts them based on frequency

---

## 📏 5. Scale Estimation

#### 🔢 Traffic Estimation

* Total Searches = **5B/day**
* QPS ≈ **60K/sec**
* **20% Unique** => 1B unique
* **Index top 10%** only = 100M terms

#### 💾 Storage Estimation

| Metric                                   | Value                   |
|------------------------------------------| ----------------------- |
| Avg query length                         | 3 words × 5 = 15 chars  |
| Bytes per query                          | 30 bytes (2 bytes/char) |
| Total terms                              | 100 million             |
| Total storage                            | 3 GB                    |
| Growth per day                           | 2%                      |
| Yearly max with growth [(365 x 2)% of 3] | \~25 GB                 |

---

## 🧩 6. Data Partitioning Strategies

We partition our data to:

* Improve latency
* Reduce load on single trie server
* Support horizontal scaling

---

### a. 📚 Range-Based Partitioning

**Strategy:** Partition terms based on their first letter or prefix (`A–Z`, `AA–AZ`, etc.)

#### ✅ Pros:

* Predictable lookup (e.g., ‘Cat’ → ‘C’ partition)
* Easy to route via load balancer

#### ❌ Cons:

* Skewed distribution (e.g., too many words for 'S', 'C')
* Poor handling of growth

#### 💡 Java Example:

```java
Map<Character, TrieServer> rangePartitions = new HashMap<>();

public TrieServer getServerForTerm(String term) {
    char first = Character.toUpperCase(term.charAt(0));
    return rangePartitions.getOrDefault(first, defaultServer);
}
```

---

### b. 🧠 Capacity-Aware Partitioning (Smart Range Splits)

**Strategy:** Dynamically assign ranges based on memory limits.

#### ✅ Pros:

* Balanced memory usage
* Avoids full static distribution

#### ❌ Cons:

* Multiple servers queried for prefix
* Requires intermediate **Aggregator** layer

#### 💡 Java Example:

```java
class TriePartitionRange {
    String start;
    String end;
    TrieServer server;

    public boolean contains(String term) {
        return term.compareTo(start) >= 0 && term.compareTo(end) <= 0;
    }
}

List<TriePartitionRange> triePartitions;

public List<TrieServer> getServersForPrefix(String prefix) {
    List<TrieServer> matched = new ArrayList<>();
    for (TriePartitionRange range : triePartitions) {
        if (prefix.compareTo(range.end) <= 0 && prefix.compareTo(range.start) >= 0) {
            matched.add(range.server);
        }
    }
    return matched;
}
```

#### 🧠 Aggregator Layer (for merging suggestions):

```java
class Aggregator {
    public List<String> getTopSuggestions(String prefix) {
        List<TrieServer> servers = getServersForPrefix(prefix);
        PriorityQueue<String> top = new PriorityQueue<>();

        for (TrieServer server : servers) {
            top.addAll(server.getSuggestions(prefix));
        }

        // Limit to top K
        while (top.size() > 10) top.poll();
        return new ArrayList<>(top);
    }
}
```

---

### c. 🔢 Hash-Based Partitioning

**Strategy:** `hash(term) % N` gives server number.

#### ✅ Pros:

* Uniform load distribution
* Minimizes hotspots

#### ❌ Cons:

* Can't do prefix search on one server
* Every lookup → all servers + aggregation

#### 💡 Java Example:

```java
int NUM_SERVERS = 10;
TrieServer[] servers = new TrieServer[NUM_SERVERS];

public int getServerIndex(String term) {
    return Math.abs(term.hashCode()) % NUM_SERVERS;
}

public void insertTerm(String term) {
    servers[getServerIndex(term)].insert(term);
}

// For suggestions, broadcast to all
public List<String> getSuggestions(String prefix) {
    PriorityQueue<String> result = new PriorityQueue<>();
    for (TrieServer server : servers) {
        result.addAll(server.getSuggestions(prefix));
    }
    // Keep top 10
    while (result.size() > 10) result.poll();
    return new ArrayList<>(result);
}
```

---

## 🔁 Strategy Summary

| Strategy             | Lookup Complexity | Load Balance | Hotspot Risk | Aggregation Required |
| -------------------- | ----------------- | ------------ | ------------ | -------------------- |
| Range-Based          | 1 server          | ❌ Skewed     | ✅ High       | ❌ No                 |
| Capacity-Aware Range | 1–few servers     | ✅ Moderate   | ⚠️ Some      | ✅ Yes (Aggregator)   |
| Hash-Based           | All servers       | ✅ Uniform    | ❌ Low        | ✅ Yes (Always)       |

---

## ⚖️ When to Use What?

| Use Case                            | Recommended Strategy        |
| ----------------------------------- | --------------------------- |
| Simpler deployment with few servers | Range-based                 |
| Variable prefix load                | Capacity-aware + aggregator |
| Global traffic + uniform access     | Hash-based                  |

---

## ✅ 7. 🔁 **Cache**

### 📌 Goal:

Reduce latency and load by caching **most frequent queries** and their **top suggestions**.

### ✅ Implementation:

* Use an in-memory cache like **Redis** or **Memcached** in front of trie servers.
* Store `Map<Prefix, List<Suggestions>>`
* Cache eviction strategy: **LFU (Least Frequently Used)** or **LRU**.

### 🧠 Advanced Optimization:

* Train a **lightweight ML model** to:

    * Predict hot terms
    * Promote trending queries
    * Cache based on click-through rate (CTR)

### 💡 Java-Like Pseudocode:

```java
Map<String, List<String>> cache = new LRUCache<>(10000);

public List<String> getSuggestions(String prefix) {
    if (cache.containsKey(prefix))
        return cache.get(prefix);
    List<String> suggestions = fetchFromTrie(prefix);
    cache.put(prefix, suggestions);
    return suggestions;
}
```

---

## ✅ 8. 🛰️ **Replication & Load Balancing**

### 🔄 Replication

* Each trie server has **1+ replicas** (e.g., master-slave)
* Ensures **high availability**, **failover support**

### ⚖️ Load Balancing

* One central **load balancer** handles routing:

    * Based on **prefix → server/partition**
    * Maintains partition map (`A-AABC` on server 1, etc.)

### 🔧 Key Tools:

* Load balancer: NGINX, Envoy, custom app layer
* Replication: ZooKeeper / etcd to track server roles

---

## ✅ 9. 🛡️ **Fault Tolerance**

### 🔥 What if a server goes down?

* **Replica becomes master** via election or static promotion
* Server restarts and **rebuilds trie from last snapshot** (see Section 4)
* Store snapshot every few minutes (e.g., serialized DFS dump of trie)

### 🔁 Techniques:

* **Heartbeat** monitoring (isAlive ping)
* **Leader election** (Raft, Paxos, or cloud-native like etcd/ZooKeeper)

---

## ✅ 10. 💻 **Typeahead Client Optimization**

### 💡 UI/UX Tweaks:

1. **Debounce typing** → Wait for `~50ms` idle before sending request
2. **Cancel in-flight requests** when user types more
3. **Wait until 2–3 chars** before triggering first request
4. **Pre-fetch** results based on partial terms (e.g., popular prefixes like "ca", "ho", "re")
5. **Local history** → Store previous results in browser storage
6. **Early connection** → Open connection on page load
7. **Push hot cache** from server to client (optional JS object blob)

### 💡 Code Hint:

```js
let timeout;
inputBox.addEventListener("input", (e) => {
  clearTimeout(timeout);
  timeout = setTimeout(() => {
    fetchSuggestions(e.target.value);
  }, 50);
});
```

---

## ✅ 11. 🧠 **Personalization**

### 🎯 What:

Tailor suggestions based on:

* **User history**
* **Location**
* **Device/language**
* **Time of day or session behavior**

### 🧩 Architecture:

* Maintain `Map<UserID, List<RecentSearches>>`
* Merge personalized suggestions with global ones
* Use client cache (e.g., localStorage or SQLite on mobile)

### 📈 Ranking Priority:

`personalized > trending > default trie suggestions`

---

## 🔚 Summary Table

| Feature             | Strategy                                                               |
| ------------------- | ---------------------------------------------------------------------- |
| **Cache**           | Redis/LRU cache for hot prefixes, optionally ML-driven prediction      |
| **Replication**     | Master-slave for failover, partitioned trie structure                  |
| **Load Balancer**   | Route based on prefix → server map                                     |
| **Fault Tolerance** | Snapshot + heartbeat + failover promotion                              |
| **Client**          | Debouncing, canceling requests, early connect, local storage, prefetch |
| **Personalization** | Rank user-specific results higher, blend with global suggestions       |

---
