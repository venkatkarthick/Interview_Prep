# Book My Show

- Objects
    - User
    - Movie
    - Theatre
    - City
    - Screen(halls)
    - Shows
    - Seat
    - Booking
    - Payment

### Concurrency Management
- User1 and user2 cannot select the same seat
- If user1 selects a seat, it will be locked for some time. If user1 doesn't complete payment, the lock will be released

- Locking mechanisms are of 2 tyoes.
  - Pessimistic
    - Lock the object when user reads the object. 
    - EX: Resource R1 is locked when user1 reads. When user2 tries to read r1, request got failed. After R1 reads and updates R1, lock on R1 got released.
  - Optimistic
    - It allows multiple users to read the resource. It also maintains the versions of the resource. While update, system checks the version and if version matches, system acquires a lock and update the resource. Else system updates the latest version and after resolving conflicts it updates the resource
    - Ex : User1 -> Read R1(V1) -> Now U1 has V1 of R1 -> Before update R1, checks version of R1 -> Still V1 -> Acquires lock and updates R1 -> R1's version upgraded to V2
    - User2 -> Read R1(V1) ->  Now U2 has V1 of R1 -> Before update R1, checks version of R1 -> Here U1 updated R1 to V2 -> Now U2 has V1 and V2 of R1. U2 resolves conflict and has V2 of R1 -> Acquires lock and updates R1 -> R1's version upgraded to V3
- Which lock we can use here?
  - Pessimistic lock may lock all the seats on read due to high traffic and affests user experience/
  - We can use optimistic lock
    - User1(U1) reads Seat S1(V1) and updates its user version to V1.
    - User1(U2) reads Seat S1(V1) and updates its user version to V1.
    - Now User1 enters into update block, which is a **synchronized block** and holds a lock. 
    - U1 checks its user version matches with S1's version. (V1(S1 of U1) == V1(original S1)). 
    - It matches, so U1 updates S1's version to V2 and completes payment and releases lock.
    - Now U2 enters into update block.
    - U2 checks its user version matches with S1's version. (V1(S1 of U1) != V2(original S1)).
    - It doesn't match, so U2 returns with an error message "Try after some time".
  - For timer logic, we can have **redis** and stores the lock timer with some expiration time. 
  - If lock doesn't get released and timer got expired, we release the lock