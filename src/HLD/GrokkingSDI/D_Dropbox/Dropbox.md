# Dropbox

## 🎯 Why Design Dropbox (Cloud Storage System)?

### ✅ Real-World Motivation

* Users want **anywhere, anytime access** to files across devices.
* Cloud storage like Dropbox enables:

    * **Availability** (via internet)
    * **Durability** (no data loss)
    * **Scalability** (storage on-demand)

> 📌 Think: Sync between devices, offline editing, version history, and shared access.

---

## 📋 1. Functional & Non-Functional Requirements

### 🔧 Functional

1. Upload/download files from any device
2. Share files/folders with other users
3. Automatic sync across devices
4. Handle **large files** (up to 1GB)
5. Support **offline editing** → sync when online
6. **Versioning/Snapshotting** of files

### 🧱 Non-Functional

* Strong **ACID properties**
* **High reliability** (data loss not acceptable)
* **Low latency sync** and update detection
* **Efficient bandwidth usage**

---

## 🧠 2. Design Considerations

| Category             | Details                                            |
| -------------------- | -------------------------------------------------- |
| **Concurrency**      | High read & write volume                           |
| **Sync granularity** | Use **chunks** (e.g. 4MB) instead of full files    |
| **Fault-tolerance**  | Retry only failed chunks                           |
| **Efficiency**       | Transfer only modified chunks (diff-based updates) |
| **Deduplication**    | Avoid uploading same content multiple times        |
| **Local cache**      | Store file metadata on the client for speed        |
| **Offline support**  | Maintain change log → sync when online             |

> 📌 Interview Tip: **Chunking + Metadata + Diff Uploads** = Key to scalable Dropbox.

---

## 📏 3. Capacity Estimation (Estimation Questions Are Common!)

| Metric                   | Assumption               |
| ------------------------ | ------------------------ |
| Total Users              | 500M                     |
| Daily Active Users (DAU) | 100M                     |
| Avg Devices/User         | 3                        |
| Files/User               | 200                      |
| Total Files              | 500M × 200 = 100B        |
| Avg File Size            | 100KB                    |
| Total Storage            | 100B × 100KB = **10 PB** |
| Active Connections       | 1M/minute (\~17K/sec)    |

> 📌 Highlight how you'd handle **scale and sync performance under load.**

---

## 🧠 What to Emphasize in an Interview

### 📦 Storage

* Store files in **chunks**
* Maintain **versioned metadata**
* Use **hashing** (SHA-256 or similar) for deduplication

### 🔄 Sync

* Client watches file system → logs changes
* Changes sent as **diffs or chunk updates**
* Use **timestamp/versioning** to resolve conflicts

### 🛠️ Fault Tolerance

* Replicate data across **data centers**
* Retry failed chunks
* Support **rollback/version restore**

### ⚡ Performance

* **CDN for downloads**
* Background upload/download manager on clients
* Reduce write amplification by **only syncing changed data**

---

## 🗣️ Sample Interview Response

> “Dropbox is a cloud file storage system enabling cross-device sync and collaboration.
> Files are chunked for efficient storage and updates, with deduplication to save space.
> We maintain metadata separately, use versioning for snapshot recovery, and enable offline sync by queueing changes.
> The system needs to support strong consistency and be resilient to client-side failures, while scaling to handle millions of users and petabytes of data.”

---

## 🧠 Flashcard Format (Quick Revision)

| ❓ Question                          | ✅ Answer                     |
| ----------------------------------- | ---------------------------- |
| Avg file size in Dropbox?           | 100KB                        |
| How is sync optimized?              | Chunking + Diff updates      |
| Offline editing support?            | Yes, with local change queue |
| Total file volume estimated?        | 100 billion                  |
| Storage scale?                      | 10 Petabytes                 |
| Key properties required?            | ACID                         |
| What helps avoid duplicate uploads? | Content-based hashing        |

---

## **5. High-Level Architecture**

**Main Idea:**
Each client monitors a **local sync folder** (workspace). Any changes—additions, deletions, or modifications—are propagated to:

* **Cloud storage** (for file chunks)
* **Metadata servers** (for file/folder state)
* **Other client devices** (via Sync Service)

### 📌 Core Components:

1. **Client**
2. **Metadata DB**
3. **Synchronization Service**
4. **Message Queue**
5. **Cloud Storage (Block/File Store)**

---

## **6. Component-Level Design (for Interview)**

### 🔹 A. Client

**Responsibilities:**

* Monitor local workspace (watch for file system events)
* Upload/download chunks to/from cloud storage
* Sync metadata with server (file name, size, version, etc.)
* Listen for updates (long polling / WebSocket)
* Resolve conflicts (e.g., concurrent edits or offline changes)

**Subcomponents:**

| Subcomponent            | Role                                           |
| ----------------------- | ---------------------------------------------- |
| **Metadata DB (local)** | Cache of file paths, chunk list, versions      |
| **Chunker**             | Splits/merges files (e.g., into 4MB chunks)    |
| **Watcher**             | Observes filesystem events                     |
| **Indexer**             | Updates local metadata & notifies Sync Service |

> 🔁 Sync only changed chunks using hash comparison (e.g., SHA-256).

---

### 🔹 B. Metadata Database

**Stores metadata for:**

* Files (name, type, version, owner)
* Chunks (IDs, order, hash)
* Users and devices
* Workspaces (linked folders)

**Tech Choices:**

* **Relational DB (MySQL)** → native ACID support
* **Or NoSQL (e.g., DynamoDB)** → requires custom ACID handling in app logic

> 💡 Choose RDBMS for easier transactional guarantees unless extremely high write throughput is needed.

---

### 🔹 C. Synchronization Service

**Responsibilities:**

* Receives file changes from clients
* Applies updates to Metadata DB
* Notifies subscribed devices of updates
* Resolves consistency issues (e.g., versioning conflicts)
* Uses **differencing algorithm** to reduce data sync overhead

> 📬 Can use **message queues** to broadcast updates and offload processing.

---

### 🔹 D. Message Queue

**Types:**

* **Request Queue:** All client update requests
* **Response Queues:** One per subscribed client/device

**Benefits:**

* Decouples clients from Sync Service
* Scalable async processing
* Supports both **pull and push** notification models

---

### 🔹 E. Cloud / Block Storage

**Stores:**

* Actual file chunks
* Indexed by hash (for deduplication)
* Clients interact directly for upload/download (bypass Sync Service)

**Advantages:**

* Scalable, cheap storage (e.g., AWS S3)
* Separate from metadata layer
* Enables deduplication at chunk level → reduce storage footprint

---

## ⚙️ Sync Flow (Interview Walkthrough)

1. **User modifies a file** locally.
2. **Watcher** detects change → notifies **Indexer**
3. **Indexer** uses **Chunker** to split file → compute hashes → upload only changed chunks
4. Metadata updated locally + pushed to Sync Service via queue
5. **Sync Service** validates update, writes to Metadata DB
6. Sync Service pushes update to **Response Queues** for other devices
7. Other clients receive the update, fetch new chunks, and apply changes

---

## ✅ Interview Tips

| Question              | Points to Hit                             |
| --------------------- | ----------------------------------------- |
| How to optimize sync? | Chunking + Hashing + Delta updates        |
| Data consistency?     | ACID → RDBMS or programmatic with NoSQL   |
| Offline edits?        | Local metadata DB + queued updates        |
| Conflict resolution?  | Timestamps or version vector              |
| Scale messaging?      | Global queue + per-client response queues |
| Reduce bandwidth?     | Only upload diffs (changed chunks)        |
| Mobile sync?          | On-demand only to save battery/data       |

---