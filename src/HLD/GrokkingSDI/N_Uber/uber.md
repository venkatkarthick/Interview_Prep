# Uber

## ✅ **1. What is Uber (Scope for MVP)**

Uber is a ride-sharing platform where:

* **Drivers** use personal vehicles to offer taxi rides.
* **Customers** book rides using the mobile app.
* App facilitates **real-time location tracking**, **ride requests**, and **driver-customer communication**.

---

## ✅ **2. Core Requirements**

#### 👥 **Users**

* **Drivers**

    * Send frequent location updates (every 3 seconds).
    * Set availability for rides.
    * Accept or reject ride requests.

* **Customers**

    * View nearby available drivers.
    * Request a ride.
    * Get matched with a nearby driver.
    * Track driver's live location during ride.

#### 🔁 **Ride Lifecycle**

1. Driver updates location + availability.
2. Customer opens app, sees nearby drivers.
3. Customer requests ride.
4. Nearby drivers are notified in real time.
5. A driver accepts; ride is confirmed.
6. Both track each other’s live location.
7. Driver ends ride upon reaching destination → becomes available again.

#### ✅ **Non functional requirements and Key Challenges**

* **Real-time location updates** at scale.
* **Low-latency driver discovery** (within a few seconds).
* **Efficient geo-indexing** for nearest driver search.
* **Concurrency** for handling simultaneous requests.
* **Data consistency** and **fault tolerance** for ride state.

---

## ✅ **3. Capacity Estimation**

| Metric                      | Value                     |
| --------------------------- | ------------------------- |
| Total customers             | 300M                      |
| Total drivers               | 1M                        |
| Daily active customers      | 1M                        |
| Daily active drivers        | 500K                      |
| Daily rides                 | 1M                        |
| Driver location update rate | Every 3 seconds           |
| Real-time matching latency  | **\~sub-second expected** |

---

## 4. System Design and Real-Time Location Updates

---

#### 🌍 **Problem**

* **Yelp’s QuadTree** worked for static data (like places).
* **Uber requires** frequent **real-time updates** from moving drivers every few seconds.

---

### 🔁 Challenge #1: Frequent Driver Updates

#### ❌ QuadTree Limitations

* Updating QuadTree on every location change is **expensive**.
* If a driver moves out of a grid:

    * Remove from old grid
    * Add to new grid
    * Repartition if the new grid is overloaded

---

#### ✅ Solution: Use a **DriverLocation HashTable (HT)**

| Key                | Value                                        |
|--------------------|----------------------------------------------|
| DriverID (_3 bytes_) | {oldLat(_8 bytes_) , oldLong, newLat, newLong} |

* Temporarily store most recent driver updates
* Update QuadTree every **10–15 seconds**
* Store \~1M drivers → Needs only **35MB RAM** _[(3+4*8)*1M]_

---

#### 🚗 Driver Location Update Flow

1. Driver sends location every **3 seconds**
2. Update goes to **Driver Location Server**
3. Server:

    * Updates `DriverLocationHT`
    * Pushes data to customers (if using push model)
    * Triggers update to QuadTree server (periodically)

---

### 📡 Broadcasting Driver Location to Customers

#### Option 1: **Push Model** (Complex)

* Use **Pub/Sub Notification Service**
* Subscribe customers to drivers
* On driver movement, push update to all subscribed users

🔢 Bandwidth Estimation:

* 500K drivers × 5 subscribers = 2.5M messages/sec
* 2.5M * 19 bytes=>  \~47.5MB/s to broadcast all driver positions

🧠 Memory for subscriptions: ≈ 21MB [(500K * 3) + (500K * 5 * 8 )]

---

#### Option 2: **Pull Model** (Simpler ✅)

* Clients **poll server** every 5 seconds for nearby drivers
* Server responds with driver list from `DriverLocationHT` or QuadTree

📱 Preferred in real-world mobile apps for simplicity and bandwidth savings.

---

### 🔁 Grid Partitioning Optimization

* Don’t split/merge grids **immediately**
* Allow a **±10% cushion** before triggering partition

    * Reduces load on partitioning logic during high activity

---

### 🎯 Ride Request Workflow (Key Interview Flow)

1. **Customer requests a ride**
2. **Aggregator Server**:

    * Queries QuadTree for nearby drivers
    * Sorts by **ratings** or ETA
3. **Top 3 drivers notified simultaneously**

    * First to accept = assigned ride
    * Others get cancellation
4. **If none respond**, try next 3 drivers
5. Once accepted:

    * Notify customer
    * Track live trip updates

---

### 🧠 Flashcards for Quick Revision

| ❓ Question                               | ✅ Answer                                          |
| ---------------------------------------- | ------------------------------------------------- |
| Why not update QuadTree every 3 seconds? | Too expensive, slows system                       |
| What is DriverLocationHT?                | HashTable holding latest location for each driver |
| Memory needed for 1M drivers?            | \~35MB                                            |
| Push or Pull: Which is simpler?          | Pull model (client polls every 5s)                |
| Notification model used in Push?         | Pub/Sub (Notification Service)                    |
| What is done during grid overflow?       | Allow ±10% cushion before splitting               |
| How does ride request work?              | Aggregator → Query → Notify drivers → Assign ride |

---

### 🗣️ What to Say in an Interview:

> “To handle frequent driver updates, we use a DriverLocation HashTable for fast in-memory updates and only periodically refresh the QuadTree.
> For driver-customer updates, we prefer a pull model where clients poll every few seconds.
> Ride assignment is done by querying nearby drivers via an aggregator and notifying the top few in parallel. This design balances efficiency and scalability in real-time environments.”
---

## ✅ 5. Fault Tolerance and Replication

### 💥 Failure Scenarios:

* **Driver Location Server dies**
* **Notification Server dies**

### ✅ Solution:

| Fault                          | Solution                                   |
| ------------------------------ | ------------------------------------------ |
| Server crash                   | Use **replication** (primary + secondary)  |
| Both primary + secondary crash | Recover from **persistent storage (SSDs)** |
| Data loss risk                 | Keep **periodic snapshots** of memory data |

🧠 **Key Point:**
Both `DriverLocationHT` and Subscription info should be **backed by fast persistent storage**, e.g., SSDs, so we can **recover quickly**.

---

## ✅ 6. Ranking Drivers (Not just proximity)

### 🏅 New Requirement:

Return **best drivers**, not just nearest.

### 🧠 Solution:

* Store **driver ratings** (e.g., average stars out of 10)
* Each QuadTree partition stores driver ratings
* When querying:

    * Ask each relevant partition for **top K drivers by rating**
    * Aggregator server merges results and returns global top K

📈 Example:

> User queries for drivers within 5km
> → Partitions return top 10 drivers each
> → Aggregator merges → returns best 10 overall

---

## ✅ 7. Advanced Issues

---

### 🐢 1. **Clients on Slow / Unstable Networks**

**Problem:** Delays in updates or ride requests

✅ **Solution:**

* Use **exponential backoff retry** mechanisms
* Show **“Last seen at”** timestamp for driver updates
* Cache last known driver location on client to prevent blank maps

---

### 📴 2. **Client Disconnects During a Ride**

**Problem:** Lost connection while ride is active

✅ **Solution:**

* Maintain ride state on server (not client)
* Server keeps tracking location → continues billing
* Once client reconnects → sync current state from server

---

### 🔄 3. **Push vs Pull for Location Updates**

| Mode               | Pros                         | Cons                               |
| ------------------ | ---------------------------- | ---------------------------------- |
| **Push (Pub/Sub)** | Real-time updates, efficient | Complex to manage subscriptions    |
| **Pull (Polling)** | Simpler, stateless clients   | More load on servers, slight delay |

**Uber-Style Choice:**

* **Pull model** every 5 seconds is preferred due to simplicity, especially for **mobile networks with frequent disconnections**

---

### 🗣️ What to Say in an Interview:

> “We replicate critical servers like DriverLocation and Notification servers for fault tolerance, and persist data on SSDs.
> To return top-rated drivers, we augment our QuadTree to track ratings and let each partition return top-K candidates for aggregation.
> For client instability, we rely on retries, cached data, and stateless polling to simplify the mobile experience.”

---

### 🧠 Flashcards

| ❓ Question                                | ✅ Answer                                            |
| ----------------------------------------- | --------------------------------------------------- |
| How is fault tolerance achieved?          | Replicas + SSD-backed storage                       |
| Can QuadTree help with ranking?           | Yes, by storing ratings in each partition           |
| Preferred model for live driver updates?  | Pull (client polls every few seconds)               |
| How to handle client disconnect mid-ride? | Server tracks state and billing continues           |
| Strategy for slow networks?               | Retry, show stale data, keep ride state server-side |



