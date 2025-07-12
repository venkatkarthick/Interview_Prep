# Yelp

- Tell me this logic in more understandable and revvisable format for System design interviews with flashcards for quick revision. It is for Yelp design


## ✅ 1. Why Yelp / Proximity Server?

A **Proximity Server** helps users:

* Discover **nearby places** (restaurants, parks, cafes, events)
* Based on their **current location (lat/long)**

🔍 Example use-cases:

* “Find top-rated Italian restaurants near me”
* “Locate theaters within 10 miles”

A Yelp-like service powers such functionality efficiently by indexing geographic data and supporting rich queries.

---

## ✅ 2. Requirements & Goals

### 🛠️ Functional Requirements:

1. **Add / Delete / Update** places
2. **Search nearby places** based on `(lat, long)` and a **radius**
3. **Add feedback/review** for places

    * Includes: **Text, Images, Rating (stars)**

---

### ⚙️ Non-Functional Requirements:

1. **Low latency search** for real-time UX
2. System must handle **very high read load**

    * Write (add place) is less frequent than search
3. **Scalable** to millions of places and QPS

---

## 📊 3. Scale Estimations

Let’s assume realistic numbers to guide design:

| Metric              | Value                      |
| ------------------- | -------------------------- |
| 📍 Number of Places | 500 Million                |
| ⚡ Search Load (QPS) | 100,000 queries/sec        |
| 🔼 Annual Growth    | +20% (both data & traffic) |

> That means in 2 years, you may have 720M places and 144K QPS.

---

### 🚨 Design Implication:

* Must use **efficient indexing** (e.g., QuadTree, Grid, GeoHash)
* Prioritize **read optimization**
* Need **sharding**, **caching**, and **replication** for scale
* Focus on **fault tolerance** and **horizontal scalability**

---

---

Here's a **concise and interview-ready summary** of the **Database Schema and API Design** for a **Yelp-like Proximity Search System**, ideal for system design interviews.

---

## ✅ 4. Database Schema

### 🏪 **Places Table**

| Field       | Type    | Size  | Description                             |
| ----------- | ------- | ----- | --------------------------------------- |
| LocationID  | BIGINT  | 8 B   | Unique ID for each place (future-proof) |
| Name        | VARCHAR | 256 B | Name of the place                       |
| Latitude    | DOUBLE  | 8 B   | Latitude                                |
| Longitude   | DOUBLE  | 8 B   | Longitude                               |
| Description | TEXT    | 512 B | Description or tags                     |
| Category    | TINYINT | 1 B   | Type of place (e.g., 1=Restaurant)      |

**Total size per record**: \~793 bytes

---

### 📝 **Reviews Table**

| Field      | Type    | Size  | Description                              |
| ---------- | ------- | ----- | ---------------------------------------- |
| LocationID | BIGINT  | 8 B   | Foreign key from Places table            |
| ReviewID   | INT     | 4 B   | Unique per location (up to 2^32 reviews) |
| ReviewText | TEXT    | 512 B | The text content of the review           |
| Rating     | TINYINT | 1 B   | 0–10 rating                              |

---

### 🖼️ **Photos Table (optional structure)**

You may need:

* `PhotoID` (unique)
* `LocationID` or `ReviewID` (foreign key)
* `PhotoURL` or blob
* `UploadedByUserID`

---

## ✅ 5. System API Design

### 🔌 **API Style:** REST or SOAP

(REST is preferred for modern microservices)

---

### 🔍 **Search API Definition**

```http
GET /search
```

#### **Parameters:**

| Param                       | Type   | Required | Description                                    |
| --------------------------- | ------ | -------- | ---------------------------------------------- |
| `api_dev_key`               | string | ✅        | Developer API key (rate limiting, security)    |
| `search_terms`              | string | ✅        | Search keywords ("coffee", "pizza", etc.)      |
| `user_location`             | string | ✅        | Lat/Long as string (`"37.7749,-122.4194"`)     |
| `radius_filter`             | number | ❌        | Radius in meters (default value can apply)     |
| `maximum_results_to_return` | number | ❌        | Max number of results (e.g., 10, 20)           |
| `category_filter`           | string | ❌        | Limit to specific category (e.g., Restaurants) |
| `sort`                      | number | ❌        | 0=Best match, 1=Nearest, 2=Highest rated       |
| `page_token`                | string | ❌        | For pagination support                         |

---

### 📦 **Response Format (JSON)**

```json
{
  "results": [
    {
      "name": "Pizza Heaven",
      "address": "123 Main St, SF, CA",
      "category": "Restaurant",
      "rating": 8.7,
      "thumbnail_url": "https://cdn.yelp/pizza.jpg"
    },
    ...
  ],
  "next_page_token": "abcdef123456"
}
```

---

## 🗣️ What to Say in an Interview:

> “We separate places, reviews, and media into normalized tables to support scalability. Each place record is around 793 bytes and includes geolocation.
>
> The API supports flexible filters: keyword, radius, category, sort mode, and pagination, returning JSON responses. We also include API keys for usage tracking and throttling.”

---

## 🧠 Flashcards for Quick Revision

| ❓ Question                  | ✅ Answer                                             |
| --------------------------- | ---------------------------------------------------- |
| Place primary key           | `LocationID` (8 bytes)                               |
| Fields in Place             | ID, Name, Lat/Long, Description, Category            |
| Why separate reviews table? | Normalize many-to-one relation; supports scalability |
| API request type            | REST (GET `/search`)                                 |
| Sort modes supported        | 0 = Best Match, 1 = Nearest, 2 = Highest Rated       |
| Why API Key?                | Throttling, rate-limiting, abuse protection          |

---

---

## ✅ 6. Basic System Design & Algorithm (Yelp-Like Search)

---

### 🎯 **Goal**:

Design a system that can:

* Store **Places** and other metadata (reviews, ratings)
* Support **real-time search** for **nearby places**, ranked by **distance and popularity**

Since places are **mostly static** (e.g., restaurants don’t move), we optimize for **read-heavy access**, not frequent writes.

---

## 📦 Step 1: Naive SQL-Based Storage

### 🧱 Data Schema:

* Use **MySQL**
* Table `Places`: each row = 1 place
  Columns:

  ```
  LocationID | Name | Latitude | Longitude | Popularity | ...
  ```

### 🔍 Basic Query:

```sql
SELECT * 
FROM Places 
WHERE Latitude BETWEEN X-D AND X+D 
  AND Longitude BETWEEN Y-D AND Y+D;
```

#### ❌ Limitations:

* Latitude and Longitude are **indexed separately** → inefficient
* Intersection of large index ranges → **slow**
* Poor performance with **500M+ records**

---

## 🗺️ Step 2: Grid-Based Indexing (Spatial Bucketing)

### 🧠 Key Idea:

* **Divide the world map into square grids** (e.g., 10 miles x 10 miles)
* Assign each place to a **GridID** based on its lat-long
* Nearby places fall into the **same or neighboring grids**

### ✅ Benefits:

* Reduces scan area → query only relevant grids
* Improves locality of data

### 🔍 Optimized Query:

```sql
SELECT * 
FROM Places 
WHERE Latitude BETWEEN X-D AND X+D 
  AND Longitude BETWEEN Y-D AND Y+D 
  AND GridID IN (CurrentGrid, NeighborGrid1, ..., NeighborGrid8);
```

---

## ⚙️ Grid Storage & In-Memory Index

* Use a **HashMap** for fast access:

  ```java
  Map<GridID, List<Place>>
  ```
* Keep the index in memory for faster lookup

### 🧮 Memory Estimation:

* \~20 million grids for Earth at 10-mile resolution
* \~500M places

Memory needed for index:

```
(4 bytes * 20M grids) + (8 bytes * 500M LocationIDs) ≈ ~4 GB
```

This is feasible on modern servers.

---

Here’s a **clear, structured, and interview-ready summary** of **Dynamic Size Grids** using a **QuadTree**, perfect for Yelp-like system design.

---

## ✅ Step 3: Dynamic Size Grids – Using QuadTree

---

### 🚩 **Problem:**

* Places are **not uniformly distributed**
* Fixed-size grids lead to:

    * **Overloaded grids** in dense areas (e.g., NYC)
    * **Wasted space** in sparse regions (e.g., oceans)

---

## 🌳 **Solution: QuadTree**

### 🧠 Key Idea:

* **Recursively split a grid** into 4 smaller grids if it exceeds **500 places**
* This creates a **QuadTree**:
  Each node represents a **grid**, has **4 children**

---

### 📐 **QuadTree Structure**

* **Root Node** = Whole Earth
* **Internal Nodes** = Intermediate grids
* **Leaf Nodes** = Grids with ≤ 500 places

> Each **Leaf Node** stores:
>
> * List of Places (`LocationID`, `lat`, `long`)
> * Max size = 500 places

---

### 🧮 **Memory Estimation**

| Component             | Size Calculation                           | Result         |
| --------------------- | ------------------------------------------ | -------------- |
| Place Data            | 500M × 24 bytes (ID + lat/long)            | 12 GB          |
| Leaf Nodes (500M/500) | ≈ 1 million                                |                |
| Internal Nodes        | \~1/3 of leaf nodes × 4 pointers × 8 bytes | ≈ 10 MB        |
| **Total Memory**      | **12GB + 10MB**                            | **≈ 12.01 GB** |

✅ **Fits in memory of modern server**

---

## 🔍 **Search Workflow**

1. **Start at Root**
2. Traverse **downward** to find the grid for given location

    * At each level, pick the child that contains `(lat, long)`
3. Once at **Leaf Node**:

    * If enough places, return them
    * Else, **expand** to **neighboring grids** until:

        * Enough places found **OR**
        * Radius exhausted

---

### 🔄 **Finding Neighboring Grids**

Two methods:

#### 🧩 Method 1: Doubly Linked List of Leaf Nodes

* Connect all leaf nodes
* Navigate to neighbors easily

#### 🧩 Method 2: Parent Pointers

* Each node has a link to its **parent**
* Parents have links to **all 4 children**
* Use this hierarchy to find **siblings and neighbors**

---

## ➕ **Insertion Workflow**

1. **New Place added** by a user
2. Insert into:

    * **Database**
    * **QuadTree**
3. Insertion in QuadTree:

    * Traverse down to correct leaf node
    * If node exceeds 500:

        * **Split** into 4 child nodes
        * **Re-distribute** places among them

---

## ☁️ **If QuadTree is Distributed Across Servers**

* First, **determine which grid/server** a new place belongs to
* Then, **route the request** to that specific server
* Insert into that grid's local QuadTree partition

---
## 🗣️ What to Say in an Interview:

> “To support fast location-based search, we start by dividing the map into static square grids. Each grid is indexed, and we only scan relevant grids near the user’s location. This improves efficiency compared to raw lat/long indexing in SQL.
>
> We store this index in memory using a hash map: `GridID → List of Places`. To handle uneven distribution of places, we adapt grid size dynamically, similar to a QuadTree. Dense areas get smaller grids, reducing scan time.
> 
> "We solve the skewed distribution of places by using a **QuadTree**, which splits grids recursively when they exceed 500 places. This makes searches efficient in both dense and sparse areas.
> Each node represents a grid and has 4 children. Leaf nodes store actual places, and we connect them with a **doubly linked list** or **parent pointers** to support efficient neighbor lookup.
> The tree fits in memory (≈12 GB), and insertions involve finding the correct grid, splitting if needed, and rebalancing the tree. In a distributed setup, we route insertions to the correct server based on grid."

---

## 🧠 Flashcards for Quick Revision

| ❓ Question                                        | ✅ Answer                                                 |
| ------------------------------------------------- | -------------------------------------------------------- |
| What does basic SQL query do for location search? | Filters using `Latitude` and `Longitude` ranges          |
| What’s the problem with lat-long indexing?        | Separate indexes → inefficient intersection              |
| What’s the grid-based solution?                   | Divide map into fixed grids, assign GridID to each place |
| Why is it better?                                 | Reduces scan range → only nearby grids are queried       |
| What is stored in memory?                         | HashMap: `GridID → List<Place>`                          |
| What happens in dense areas?                      | Grids are split recursively (QuadTree-like)              |
| Challenge of dynamic grids?                       | Mapping location to grid and finding neighbors is harder |
| When do we split a grid?       | If it has > 500 places                       |
| What structure is used?        | **QuadTree** (4-child tree)                  |
| What’s stored in leaf nodes?   | List of places (`LocationID`, `lat`, `long`) |
| How are neighbors found?       | Doubly linked list or parent-child pointers  |
| How is memory efficiency?      | ≈12.01 GB for 500M places                    |
| How is new place inserted?     | Traverse tree → insert or split if needed    |
| What about distributed system? | Route to correct QuadTree server by location |


---

---

## 7. Data Partitioning
### ✅ **Why Partitioning is Needed in Yelp-like Systems**

As the number of **places (restaurants, shops, etc.) grows**, a single machine can't:

* Hold the **entire QuadTree index** in memory
* Handle the **read/write traffic**

So, we **partition** the data to scale horizontally — i.e., **distribute the load across multiple servers**.

---

### 📌 Two Common Partitioning Strategies

### **1. Sharding by Region (e.g., Zip Code or Geohash)**

Each region (zip/geohash) is assigned to a specific server.

#### ✅ Pros:

* Easy to route: Use region to directly go to the right server
* Simple design

#### ❌ Cons:

* **Hotspot Problem**: Popular regions (e.g., Times Square) get too many reads/writes — overloads that server
* **Skewed Data**: Some regions have far more places than others → imbalance in storage/load

#### 🔄 Fixes:

* Repartition data when imbalanced (complex + costly)
* Use **consistent hashing** to smooth distribution over time

---

### **2. Sharding by LocationID (Hash-Based Partitioning)**

Hash the `LocationID` and store the place on the server assigned to that hash range.

#### ✅ Pros:

* **Even distribution**: Hashing spreads data uniformly across servers
* Handles skew better than region-based sharding

#### ❌ Cons:

* **Nearby places may be on different servers** (not region-local)
* To find places near a user, you **query all servers**, then **merge results centrally**

#### ✔️ Design Decision:

* Each server builds its own **local QuadTree** for its set of places.
* Structure may vary per server — and that's **okay** — because:

    * You still query **all servers** for nearby places
    * Each returns **local matches**, and a central service **aggregates results**

---

## 🧠 Interview Insight: What to Say

"In our Yelp-like design, we scale our QuadTree by partitioning data. We explored two strategies:

1. **Region-based sharding**, where each region's data lives on one server. It's intuitive but can lead to hotspots and uneven data growth.
2. **Hash-based sharding using LocationID**, which spreads load evenly. But since it breaks locality, we query all servers for nearby places and merge results centrally.

Each server maintains its own QuadTree on local data. Even though trees differ, it doesn’t affect correctness because we cover all partitions when searching.

For scalability and resilience, we prefer **hash-based sharding** with **location-aware query aggregation**."

---

---

## 8. Replication and Fault Tolerance
### 🚨 Problem to Solve:

* **What if a server storing part of the QuadTree goes down?**
* **How do we ensure fault tolerance and high availability?**

---

### ✅ 1. **Replication for Fault Tolerance and Scaling Reads**

#### 🧱 Setup:

* Use **Master-Slave replication** for each QuadTree server:

    * **Master handles writes**
    * **Slaves handle reads**

#### 🌀 Delay:

* Writes to slaves are **eventually consistent** (may lag by a few milliseconds).
* Acceptable in most location-based use cases.

---

### 🔁 2. **Failover Scenarios**

#### 🟥 If **Primary (Master)** dies:

* **Secondary (Slave)** takes over as the **new Master**
* No loss of data or service downtime (if failover is fast)

#### 🟥 If **Both Primary & Secondary die**:

* We need to **rebuild the server from scratch**
* Challenge: **How do we know what data belonged to that server?**

---

### 🧠 3. **Efficient Recovery — QuadTree Index Server**

#### ❌ Naive approach:

* Re-scan **entire database**, hash each LocationID to check if it belonged to the dead server.
* This is **slow and inefficient**, especially during high traffic.

#### ✅ Optimized approach:

* Maintain a **reverse index** via a **QuadTree Index Server**:

    * Think of it as a **central registry** mapping:

      ```
      QuadTreeServerID → Set of LocationIDs + Coordinates
      ```

    * Implement as:

      ```java
      Map<ServerID, Set<PlaceInfo>>
      ```

* PlaceInfo = LocationID + (lat, long)

* Stored in **in-memory HashSet** for fast updates (add/remove)

---

### 🔁 4. **How Server Recovery Works**

* If a server dies, the new server:

    * **Contacts QuadTree Index Server**
    * Fetches the set of places it is supposed to store
    * Rebuilds its **local QuadTree** quickly

---

### 🔒 5. **Fault Tolerance of the Index Server**

* The **QuadTree Index Server** is also replicated
* If it crashes, it can be **rebuilt by scanning the main DB** (less frequently needed, slower but acceptable fallback)

---

## 📌 What to Say in an Interview:

> “We replicate each QuadTree server in a master-slave setup to support high read traffic and fault tolerance. For failover, if a master goes down, the slave takes over. If both go down, we rely on a **QuadTree Index Server**, which maintains a mapping from server IDs to all the places it holds. This lets us quickly rebuild a server without scanning the whole database. The Index Server itself is also replicated to avoid single points of failure. This design ensures high availability and quick recovery in a Yelp-like system."

---

---

Here’s a **revised and interview-friendly explanation** of **Cache and Load Balancing (LB)** for a Yelp-like system (System Design Interview context):

---

## 🚀 9. Caching — Handling Hot Places

### ✅ **Why Cache?**

* Some places (e.g., Starbucks in NYC) receive **very frequent traffic**
* Hitting the database for such "hot" items causes **unnecessary load and latency**

### 🧰 **Solution:**

* Use a **distributed cache layer**, like **Memcached** or **Redis**
* Cache stores **hot places’ data** (e.g., name, location, rating, etc.)

### 🧪 **How it works:**

* Application server first checks cache:

    * ✅ **Hit:** Returns data directly
    * ❌ **Miss:** Queries database and populates cache

### ⚙️ **Eviction Policy:**

* Use **LRU (Least Recently Used)**:

    * Removes oldest unused entries when cache is full
    * Keeps cache **fresh and efficient**

### 🔁 **Scalability:**

* Scale cache **horizontally** as usage grows
* Use **consistent hashing** if needed for distributed cache sharding

---

---

## 🏗️ 10. Load Balancing — Distributing Traffic

### 📍 **Why LB?**

* Prevent any **single server** from becoming a bottleneck
* Distribute incoming requests across available servers

---

### 📌 **Where to use Load Balancers:**

1. **Between Clients & App Servers**
2. **Between App Servers & Backend (DB / QuadTree / Cache servers)**

---

### 🔁 **Round Robin LB (Basic)**

* Distributes traffic **evenly**
* **Simple**, fast, and no special logic
* Detects **dead servers** and removes them from rotation

#### ❗Limitation:

* Ignores **real-time server load**
* May keep sending traffic to **overloaded** or **slow** servers

---

### 🧠 **Smarter Load Balancer (Advanced)**

* Monitors:

    * **CPU usage**
    * **Request queue length**
    * **Response time**
* Dynamically adjusts routing based on **server health**
* Can implement via tools like:

    * **Envoy**
    * **NGINX + custom health checks**
    * **AWS ALB (Application LB)** with **auto-scaling groups**

---

## 🗣️ What to Say in an Interview:

> “To support high throughput and low latency, we add a cache layer using Memcached for frequently accessed ‘hot’ places. This avoids hitting the DB repeatedly. We use LRU eviction for efficient memory usage.
>
> For load distribution, we introduce load balancers between clients and app servers, and between app servers and backend. A simple round-robin LB is easy to set up, but we can evolve to a smarter LB that tracks server load and adjusts traffic intelligently for better performance and resilience.”

---

---

Here's a **concise, interview-ready explanation** of how to implement **ranking** in a location-based service like **Yelp**, specifically when combining **proximity with popularity/relevance**.

---

## 🌟 11. Ranking in Location-Based Search

---

### 🎯 **Problem:**

How do we **rank** nearby places **not just by distance**, but also by:

* **Popularity**
* **Relevance**
* **Ratings**

---

## 🧠 Design Approach

### ✅ **Step 1: Store Popularity Data**

* Each place has a **popularity score** (e.g., average star rating out of 10, or number of check-ins)
* Stored in:

    * **Database** (for persistence)
    * **QuadTree nodes** (for fast access during search)

---

### ✅ **Step 2: Querying Top Places Within a Radius**

* When a user searches, each **QuadTree partition**:

    * Finds the **top 100 most popular places** within the given radius
* These are returned to an **aggregator server**

### 🔄 Aggregator:

* Merges results from all partitions
* Selects the **final top 100 places** globally

---

### ⚠️ **Updating Popularity Efficiently**

* Frequent updates to QuadTree nodes are **costly and disruptive**
* Popularity isn’t real-time sensitive (e.g., a restaurant’s average rating doesn’t change every minute)

### ✅ Solution:

* Use **batch updates**, **once or twice a day**
* Run updates during **low traffic windows**
* Reduce write load, preserve **system throughput**

---

### 🗣️ What to Say in an Interview:

> “To rank search results not just by proximity, we augment each place with a popularity score stored in both DB and QuadTree nodes. On query, each QuadTree partition returns the top 100 popular places within the radius. These are aggregated to get the final top 100.
>
> Since updating QuadTrees frequently is expensive, we batch popularity updates once or twice daily during low-traffic periods. This maintains ranking accuracy without hurting performance.”

---

## ✅ Quick Revision Flashcards:

| 🔍 Question                                  | ✅ Answer                                                   |
| -------------------------------------------- | ---------------------------------------------------------- |
| How is popularity stored?                    | In DB + QuadTree node                                      |
| How are top results within a radius fetched? | Each partition returns local top N; aggregator combines    |
| Why avoid frequent updates?                  | Updating QuadTrees is expensive and can affect performance |
| How to update popularity efficiently?        | Batch updates 1–2 times/day during off-peak hours          |

---