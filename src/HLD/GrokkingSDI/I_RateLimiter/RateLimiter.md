# Rate Limiter

Here’s a **quick-revision, interview-friendly breakdown** of **Facebook Messenger’s Rate Limiting Logic**—perfect for **system design interviews**:

---

## ✅ API Rate Limiter for Facebook Messenger

---

### 🧠 1. **What is Rate Limiting?**

> Rate Limiting **controls how many requests** a user/device/app can make **in a defined time window**.

📌 Examples:

* 1 message per second
* 100 messages per hour
* 3 failed logins per day

---

### 🎯 2. **Why Rate Limit Messenger?**

* **Prevent abuse**: Avoid spamming (bots sending mass messages).
* **Protect resources**: Backend/database is protected from overload.
* **Fairness**: Prevent a few users from hogging the system.
* **Security**: Prevent brute-force attacks (e.g., login, password reset).
* **Revenue model**: Different limits for free vs premium users.

---

#### 🧩**What to Rate Limit in Messenger?**

| Feature                        | Limit Type               |
| ------------------------------ | ------------------------ |
| Sending messages               | 1 msg/sec or X msgs/min  |
| Login attempts                 | Max 3 failed/day         |
| New group creations            | Max 10/hour              |
| Attachments/Media uploads      | Bandwidth or count based |
| API access (bots/integrations) | Tiered quotas            |

---

Here’s a **crisp, revisable summary** of how to **implement API Rate Limiting in Facebook Messenger**, ideal for **system design interviews**:

---


### ✅ 4. **How is Rate Limiting Done?**

Rate Limiting:

* Controls **how fast** users can access APIs.
* Prevents system overload or abuse.

📌 If the limit is exceeded:

> ❌ Server responds with **HTTP 429 – Too Many Requests**

---

### 📊 5. **Types of Throttling (Rate Control Strategies)**

| Type                   | Description                                         | Example                         |
| ---------------------- | --------------------------------------------------- | ------------------------------- |
| **Hard Throttling**    | Strict cap. No request is allowed beyond the limit. | Max 100 messages/min – *Strict* |
| **Soft Throttling**    | Allows small % over limit before blocking.          | Limit = 100, allow up to 110    |
| **Elastic Throttling** | Exceed limit if system has idle capacity.           | Use unused capacity dynamically |

---

### 📈 6. **Rate Limiting Algorithms**

| Algorithm                  | Description                                                 | Use Case                         |
| -------------------------- | ----------------------------------------------------------- | -------------------------------- |
| **Fixed Window**           | Count resets every fixed time unit (e.g. every 60 sec).     | Simple limits, easy to implement |
| **Sliding Window Log**     | Tracks timestamps of requests; accurate but memory heavy.   | High accuracy needed             |
| **Sliding Window Counter** | Approximates logs using buckets for moving window effect.   | Balance between accuracy & cost  |
| **Token Bucket**           | Add tokens per second; allow if tokens exist. Allows burst. | Ideal for Messenger chat         |
| **Leaky Bucket**           | Controls outflow rate strictly, smoothens traffic spikes.   | Upload rate limiting             |

---

### 📉 Fixed vs Sliding Window (With Example)

```
Time (sec) → 0         1         2
              m1 m2    m3 m4 m5

Assume limit = 2 requests/sec
```

* ✅ **Fixed Window**: Resets every 1 sec

    * m1, m2 → OK
    * m3, m4 → OK
    * m5 → ❌ *Blocked*

* ✅ **Rolling Window** (per millisecond)

    * m3 (300ms), m4 (400ms), m5 (600ms)
    * If all fall in 1s sliding window → m5 gets throttled

---

### 🧠 Design Tip for Interviews

> Choose the algorithm **based on accuracy vs performance tradeoff**:

| If you need...          | Use this...        |
| ----------------------- | ------------------ |
| Simplicity              | Fixed Window       |
| Burst handling          | Token Bucket       |
| High accuracy           | Sliding Window Log |
| Consistent outflow rate | Leaky Bucket       |

---


Here's a **well-structured and hydrated version** of the **Rate Limiting System Design** with a focus on **clarity and revision-friendliness** for **system design interviews**. Java examples can be added at the end if needed.

---

## ✅ API Rate Limiting – System Design (e.g., for Facebook Messenger)

---

### 📌 8. Basic Design Goal

We want to **limit the number of API requests per user** within a given time frame.

---

### 🧱 Core Data Structure

We maintain a **per-user entry** in a **hash table**:

```plaintext
Key:   UserID
Value: { Count, StartTime }
```

Example:

```plaintext
"Kristie" → { count: 3, startTime: 1499818564 }
```

---

### 📋 Logic (Fixed Window Algorithm – 3 req/min/user)

#### Step-by-step request handling:

1. **New UserID** (not in the map):

    * Add entry: `{ count = 1, startTime = currentEpochMinute }`
    * ✅ Allow the request

2. **Existing UserID**:

    * If `currentTime - startTime >= 1 minute`:

        * Reset: `{ count = 1, startTime = now }`
        * ✅ Allow the request
    * Else (within same window):

        * If `count < 3` → increment `count` → ✅ Allow
        * Else `count >= 3` → ❌ Reject request (rate limit hit)

---

### ⚠️ Drawbacks of Fixed Window Algorithm

#### 1. ❗ Double-Dipping Edge Case

> **Problem**: User can send requests at the **end of one window** and at the **start of the next**, effectively doubling their rate.

Example:

* Kristie sends **3 messages at 00:59:58**
* Sends **3 more at 01:00:01**
* ➤ 6 messages in \~3 seconds (instead of 3 per minute)

**🛠️ Solution**: Use **Sliding Window** for smoother rate control.

---

#### 2. ⚠️ Race Conditions (Atomicity)

> In a **distributed system**, two requests could read the same count before updating.

**Example**:

* Kristie sends 2 requests at once.
* Both see `count = 2`, think they’re safe, and increment.
* Result: Count becomes 4! (limit was 3)

---

### 🧩 Solving Atomicity Issues

#### If using **Redis**:

* Use **Redis `SETNX` or `WATCH`/`MULTI/EXEC`** or a **distributed lock** around the read-update logic.

#### If using **in-memory map**:

* Use **synchronized blocks** or **per-user locks**.

---

### 💾 Memory Estimation (HashMap-based In-Memory Version)

| Field          | Size         |
| -------------- | ------------ |
| UserID         | 8 bytes      |
| Count          | 2 bytes      |
| Timestamp      | 2 bytes      |
| **Total/User** | **12 bytes** |

* HashMap overhead per entry: \~20 bytes
  → `12 + 20 = 32 bytes/user`

With **1 million users**:
→ `32MB total memory`

Add **4-byte lock field/user** (optional):
→ `36MB` total

✅ Fits in memory, but...

---

### 🚦 Scaling Rate Limiter System

| Concern           | Solution                                    |
| ----------------- | ------------------------------------------- |
| High QPS          | Use **distributed cache** (Redis/Memcached) |
| Single node limit | Horizontally scale rate limiter nodes       |
| Data sync         | Redis handles atomic ops via Lua scripts    |
| Memory limit      | Expire old UserIDs using TTL or LRU         |

---

### ✅ When to use which backend?

| Option          | Use When...                          |
| --------------- | ------------------------------------ |
| In-Memory Map   | Simple setup, limited users          |
| Redis (Central) | Reliable, atomic, distributed setup  |
| Memcached       | Faster, less durable (no atomic ops) |

---

### ☑️ Interview Tip

If asked *"How do you rate limit 100M users for a messaging service?"*:

**Answer**:

> I'd partition the keys (e.g., using consistent hashing), use Redis for distributed atomicity, and store `{count, startTime}` per user with TTL. I’d choose sliding window counters or token bucket to smooth out spikes, and use per-user locks or Redis Lua scripts to avoid race conditions.

---

