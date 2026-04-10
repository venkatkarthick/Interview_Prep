# ☕ Advanced Java — Full Course Notes
> **Source:** SimplyLearn Advanced Java Full Course (YouTube)  
> **Topics Covered:** Enums · Annotations · Serialization · Multithreading · Synchronization · Autoboxing · I/O Streams · JDBC · Generics · String Handling · java.lang & java.util · Networking · Images · Concurrency Utilities · Regex · NIO · JavaBeans · Spring Framework · Spring MVC · Spring REST · Spring Boot Project

---

## 📋 Table of Contents

1. [Enumerations (Enums)](#1-enumerations-enums)
2. [Annotations](#2-annotations)
3. [Serialization](#3-serialization)
4. [Multithreading](#4-multithreading)
5. [Synchronization](#5-synchronization)
6. [Autoboxing & Unboxing](#6-autoboxing--unboxing)
7. [I/O Streams](#7-io-streams)
8. [JDBC — Java Database Connectivity](#8-jdbc--java-database-connectivity)
9. [Generics](#9-generics)
10. [String Handling](#10-string-handling)
11. [java.lang & java.util Packages](#11-javalang--javautil-packages)
12. [Networking in Java](#12-networking-in-java)
13. [Regular Expressions (Regex)](#13-regular-expressions-regex)
14. [NIO — Non-Blocking I/O](#14-nio--non-blocking-io)
15. [Spring Framework](#15-spring-framework)
16. [Spring MVC](#16-spring-mvc)
17. [Spring REST APIs](#17-spring-rest-apis)
18. [Spring Boot Project (Student API)](#18-spring-boot-project-student-api)

---

## 1. Enumerations (Enums)

### Why Enums?
Use enums when you need to define **constants that will never change** — e.g., days of the week, months, seasons, time zones, directions.

- They are **type-safe** — not just plain integers or strings
- Values are **constant by default** — cannot be modified
- All enum constants are written in **CAPS** by convention

### Basic Enum Declaration

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

### Enum with Internal Values

Use when each constant needs an associated value (e.g., abbreviations or codes):

```java
public enum Color {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private String value;

    // Constructor
    Color(String value) {
        this.value = value;
    }

    // Getter
    public String getValue() {
        return value;
    }
}
```

### Using Enums

```java
public class Main {
    public static void main(String[] args) {
        Color c1 = Color.RED;

        // Print enum constant name (returns "RED" in caps)
        System.out.println("Enum name: " + c1.name());

        // Print internal value (returns "red" in lowercase)
        System.out.println("Enum value: " + c1.getValue());

        // Iterate over all enum constants
        for (Color c : Color.values()) {
            System.out.println(c.getValue());
        }
    }
}
```

**Output:**
```
Enum name: RED
Enum value: red
red
green
blue
```

> 💡 **Key Methods:**
> - `.name()` → returns the constant name in CAPS
> - `.getValue()` → returns the internal value (custom getter)
> - `.values()` → returns an array of all enum constants for iteration

---

## 2. Annotations

### What Are Annotations?
Annotations provide **metadata** to the Java compiler or runtime processor. They don't directly execute code but give instructions about *how* code should behave.

**Three main uses:**
1. Providing information to the **compiler**
2. **Compile-time** or deployment-time processing
3. **Runtime** processing instructions

### Common Built-in Annotations

| Annotation | Purpose |
|---|---|
| `@Override` | Tells the compiler the method overrides a parent class method |
| `@SuppressWarnings` | Suppresses compiler warning messages |
| `@Deprecated` | Marks a method/class as outdated |
| `@FunctionalInterface` | Marks an interface as a functional interface (single method) |
| `@Author` | Documents the author of a class or method |

### Creating Custom Annotations

**Step 1 — Marker Annotation (no logic):**

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   // When to process: SOURCE or RUNTIME
@Target(ElementType.TYPE)             // Where to apply: TYPE=class, METHOD, FIELD, etc.
public @interface MyMarkerAnnotation {
    // Empty — just a marker
}
```

**Step 2 — Annotation with Logic/Metadata:**

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)           // Only applies to methods
public @interface MyCustomAnnotation {
    String description() default "No description";
    int version() default 1;
}
```

**Step 3 — Using the custom annotation:**

```java
public class MyClass {

    @MyCustomAnnotation(description = "This method does something", version = 2)
    public void myMethod() {
        System.out.println("Method called!");
    }
}
```

> 💡 **Key Points:**
> - Annotations always start with `@`
> - Use `@interface` keyword to define a custom annotation
> - `@Retention` → controls when it is active (`SOURCE` = compile time, `RUNTIME` = runtime)
> - `@Target` → controls where it can be applied (`TYPE`, `METHOD`, `FIELD`, `CONSTRUCTOR`, etc.)

---

## 3. Serialization

### What Is Serialization?
The process of **converting a Java object into a byte stream** so it can be saved to a file, sent over a network, or stored in a database. **Deserialization** is the reverse — converting bytes back into an object.

### Making a Class Serializable

Implement the `Serializable` interface (marker interface — no methods required):

```java
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;  // Recommended for versioning
    private String name;
    private int age;
    private transient int x;  // 'transient' fields are NOT serialized

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
}
```

### Serializing an Object (Writing to File)

```java
import java.io.*;

public class SerializeDemo {
    public static void main(String[] args) throws IOException {
        Student student = new Student();
        student.setName("Alice");
        student.setAge(20);
        student.setX(10);  // transient value — will NOT be saved

        // Serialize
        FileOutputStream fileOut = new FileOutputStream("student.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(student);
        out.close();
        fileOut.close();

        System.out.println("Object serialized successfully.");
    }
}
```

### Deserializing an Object (Reading from File)

```java
import java.io.*;

public class DeserializeDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("student.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);

        Student student = (Student) in.readObject();
        in.close();
        fileIn.close();

        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("X (transient): " + student.getX()); // Will print 0, not 10!
    }
}
```

> ⚠️ **`transient` keyword:** Fields marked with `transient` are **excluded** from serialization. After deserialization, their value resets to the default (0 for int, null for objects).

---

## 4. Multithreading

### Concepts: Process vs Thread

| | Process | Thread |
|---|---|---|
| Definition | Standalone execution environment | Lightweight sub-unit of a process |
| Resources | Has its own memory/resources | Shares memory with other threads in the same process |
| Creation cost | Expensive | Cheap |
| Example | A running application | Each task inside the application |

> 💡 **Real-world analogy:** In a racing game — the speed display, leaderboard, fuel indicator each run as **separate threads** inside the same game process.

### Thread States
1. **Ready** — Thread is ready to run
2. **Running** — Thread is actively executing
3. **Waiting/Blocked** — Waiting for I/O or user input
4. **Sleep** — Paused temporarily
5. **Dead** — Execution completed

### Way 1 — Extending the Thread Class

```java
public class Thread1 extends Thread {
    @Override
    public void run() {
        // Your thread logic goes here
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 1 is running: " + i);
            try {
                Thread.sleep(100); // Pause for 100ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Way 2 — Implementing Runnable Interface

> ✅ **Preferred method** — allows you to also extend another class (Java doesn't support multiple inheritance)

```java
public class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 2 is running: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Starting Threads

```java
public class ThreadingDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // Way 1: Extend Thread
            Thread1 t1 = new Thread1();
            t1.start();  // Calls run() internally — NEVER call run() directly!

            // Way 2: Implement Runnable
            Thread t2 = new Thread(new Thread2());
            t2.start();
        }
    }
}
```

> ⚠️ **Important:** Always call `.start()`, NOT `.run()`. Calling `.run()` directly just executes it as a normal method — it won't run in a new thread. `.start()` changes the thread state from **Ready → Running**.

> ⚠️ **Thread execution order is NOT guaranteed.** Threads run whenever they find an idle CPU. Every run will produce different output order.

---

## 5. Synchronization

### The Problem
When two threads access and **modify the same object simultaneously**, the data can become corrupted or inconsistent.

> **Banking example:** If balance = ₹1000, Thread A deposits ₹500 and Thread B withdraws ₹500 simultaneously — the final balance could be unpredictable without synchronization.

### Solution: Intrinsic Locks (Monitor Locks)
When a thread takes a lock on an object/method, **no other thread can access it** until the lock is released.

### Method-Level Synchronization

```java
public class MathUtils {
    // Only one thread can execute this method at a time
    public synchronized void getMultiples(int number) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(number * i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Block-Level Synchronization

Use when you only want to lock **specific lines of code** within a method (more granular control):

```java
public void getMultiples(int number) {
    // Non-sensitive code — multiple threads can run this
    System.out.println("Starting calculation...");

    // Only lock the sensitive section
    synchronized (this) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(number * i);
        }
    }
}
```

> 💡 **When to use which:**
> - `synchronized` on method → Lock the **entire method**
> - `synchronized(this)` block → Lock only the **critical section** — better for performance
> - The "sensitive code" is code where two threads modifying it simultaneously would cause bugs — identifying it comes with experience.

---

## 6. Autoboxing & Unboxing

### What Is It?
Java has both **primitive types** (int, char, double) and **wrapper/object types** (Integer, Character, Double).

- **Autoboxing** → Auto-converting primitive → wrapper object
- **Unboxing** → Auto-converting wrapper object → primitive

### Why Do We Need This?
Collections like `ArrayList`, `HashMap` only work with **objects**, not primitives. Autoboxing allows you to store primitives in collections seamlessly.

```java
public class AutoboxingDemo {
    public static void main(String[] args) {
        // Autoboxing: int primitive → Integer object
        int primitiveInt = 5;
        Integer integerObj = primitiveInt;  // Auto-boxed automatically
        System.out.println("Integer Object: " + integerObj);

        // Unboxing: Integer object → int primitive
        Integer i = new Integer(10);
        int i1 = i;  // Auto-unboxed automatically
        System.out.println("Primitive int: " + i1);

        // Character autoboxing
        char ch = 'A';
        Character charObj = ch;  // Autoboxed
        char unboxed = charObj;  // Unboxed
        System.out.println("Char: " + unboxed);

        // Using in Collections (requires objects, not primitives)
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        list.add(42);  // Autoboxing happens automatically here
    }
}
```

| Primitive | Wrapper Class |
|---|---|
| `int` | `Integer` |
| `char` | `Character` |
| `double` | `Double` |
| `float` | `Float` |
| `long` | `Long` |
| `boolean` | `Boolean` |
| `byte` | `Byte` |

---

## 7. I/O Streams

### Concept
- **Input Stream** → Reading data from a source
- **Output Stream** → Writing data to a destination
- Data travels as a **stream of bytes (0s and 1s)**

### Two Types of Streams

| | Byte Stream | Character Stream |
|---|---|---|
| Classes | `FileInputStream`, `FileOutputStream` | `FileReader`, `FileWriter` |
| Unit | Byte by byte | Character by character |
| Best for | Binary files, images, databases | Text/character files |

### Byte Stream Example — Copy a File

```java
import java.io.*;

public class ByteStreamExample {
    public static void main(String[] args) {
        FileInputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = new FileInputStream("source.txt");
            outStream = new FileOutputStream("dest.txt");

            int content;
            // Read byte by byte until end-of-file (-1)
            while ((content = inStream.read()) != -1) {
                outStream.write((byte) content);  // Cast int to byte
            }
            System.out.println("File copied successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Always close streams in the finally block!
            try {
                if (inStream != null) inStream.close();
                if (outStream != null) outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Character Stream Example — Copy a Text File

```java
import java.io.*;

public class CharacterStreamExample {
    public static void main(String[] args) {
        FileReader reader = null;
        FileWriter writer = null;

        try {
            reader = new FileReader("source.txt");
            writer = new FileWriter("dest.txt");

            int content;
            // Read character by character
            while ((content = reader.read()) != -1) {
                writer.write((char) content);  // Cast int to char
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

> ⚠️ **Always close streams in the `finally` block!** File streams are costly connections — leaving them open wastes memory and can cause bugs.

---

## 8. JDBC — Java Database Connectivity

### What Is JDBC?
JDBC is a **uniform interface/API** that lets Java programs connect to **any database** (PostgreSQL, MySQL, Oracle, SQL Server, etc.) using the same code. Each database vendor provides a **JDBC driver** (JAR file) that implements the JDBC interface.

### Setup Steps
1. Install your database (e.g., PostgreSQL)
2. Download the **JDBC driver JAR** for your DB from the official site
3. Add the JAR to your project classpath

### JDBC Connection URL Format
```
jdbc:<vendor>://<host>:<port>/<database_name>
```
Example for PostgreSQL:
```
jdbc:postgresql://localhost:5432/testdb
```

### Complete JDBC Example — Insert a Record

```java
import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        // Connection details
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "postgres";

        Connection con = null;

        // Data to insert
        int rollNumber = 101;
        String name = "Alice";
        int age = 21;

        try {
            // 1. Get a connection to the database
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database!");

            // 2. Build the SQL statement
            String sql = "INSERT INTO student (roll_number, name, age) VALUES ("
                        + rollNumber + ", '" + name + "', " + age + ")";

            // 3. Create a Statement object
            Statement stmt = con.createStatement();

            // 4. Execute the SQL
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("Rows inserted: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Always close the connection
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### JDBC Key Classes

| Class/Interface | Purpose |
|---|---|
| `DriverManager` | Manages DB drivers, creates connections |
| `Connection` | Represents a live connection to the DB |
| `Statement` | Executes SQL queries |
| `PreparedStatement` | Safer parameterized SQL (prevents SQL injection) |
| `ResultSet` | Holds the result of a SELECT query |

### Reading Data (SELECT Query)

```java
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM student");

while (rs.next()) {
    System.out.println("Roll: " + rs.getInt("roll_number"));
    System.out.println("Name: " + rs.getString("name"));
    System.out.println("Age: " + rs.getInt("age"));
}
```

> 💡 **Best Practice:** Use `PreparedStatement` over `Statement` to avoid **SQL injection attacks** and improve performance.

---

## 9. Generics

### What Are Generics?
Generics allow you to write **type-safe, reusable code** that works with any data type. They eliminate the need for casting and catch type errors at compile time.

```java
// Without Generics (old way) — requires casting, error-prone
List list = new ArrayList();
list.add("Hello");
String s = (String) list.get(0); // Must cast manually

// With Generics — type-safe, no casting needed
List<String> list = new ArrayList<>();
list.add("Hello");
String s = list.get(0); // No casting needed!
```

### Generic Class

```java
public class Box<T> {  // T = Type placeholder
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

// Usage
Box<String> stringBox = new Box<>();
stringBox.setContent("Hello");
System.out.println(stringBox.getContent()); // Hello

Box<Integer> intBox = new Box<>();
intBox.setContent(42);
System.out.println(intBox.getContent()); // 42
```

### Generic Method

```java
public class GenericMethod {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Alice", "Bob"};
        Integer[] numbers = {1, 2, 3};

        printArray(names);   // Works with String
        printArray(numbers); // Works with Integer
    }
}
```

---

## 10. String Handling

### Key String Methods

```java
public class StringHandlingDemo {
    public static void main(String[] args) {
        String str = "Hello, World!";

        System.out.println(str.length());          // 13
        System.out.println(str.toUpperCase());     // HELLO, WORLD!
        System.out.println(str.toLowerCase());     // hello, world!
        System.out.println(str.charAt(0));         // H
        System.out.println(str.indexOf("World")); // 7
        System.out.println(str.substring(7, 12)); // World
        System.out.println(str.replace("World", "Java")); // Hello, Java!
        System.out.println(str.trim());            // Removes leading/trailing spaces
        System.out.println(str.contains("World")); // true
        System.out.println(str.startsWith("Hello")); // true
        System.out.println(str.endsWith("!"));      // true
        System.out.println(str.split(", ")[0]);     // Hello

        // String comparison
        String s1 = "Java";
        String s2 = "Java";
        System.out.println(s1.equals(s2));          // true (use equals, not ==)
        System.out.println(s1.equalsIgnoreCase("java")); // true
    }
}
```

### StringBuilder — Mutable Strings

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(", World!");  // Modify without creating new objects
sb.insert(5, " Java");
sb.delete(5, 10);
sb.reverse();
System.out.println(sb.toString());
```

> 💡 Use `StringBuilder` (or `StringBuffer` for thread-safe) when doing many string modifications in a loop — `String` is immutable and creates a new object on every change, which is memory-inefficient.

---

## 11. java.lang & java.util Packages

### java.lang Package
- **Imported automatically** — no explicit import needed
- Contains the most fundamental Java classes

**Key contents:**
- **Data type wrapper classes:** `Integer`, `Double`, `Character`, `Boolean`, `Long`, `Float`, `Byte`
- **Core classes:** `String`, `Math`, `Object`, `System`, `Thread`, `Enum`
- **Interfaces:** `Runnable`, `Comparable`, `Iterable`, `Cloneable`
- **Exceptions:** `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ClassCastException`
- **Annotations:** `@Override`, `@FunctionalInterface`

```java
// These work WITHOUT any import statement:
int x = Integer.parseInt("42");
double d = Math.sqrt(16);
String s = "Hello";
Thread t = new Thread(...);
```

### java.util Package
- **Must be explicitly imported**
- Contains the Collections Framework and utilities

**Key contents:**
- **Collection interfaces:** `List`, `Set`, `Map`, `Queue`
- **Collection classes:** `ArrayList`, `LinkedList`, `HashMap`, `HashSet`, `TreeMap`, `Stack`
- **Utility classes:** `Scanner`, `Arrays`, `Collections`, `Date`, `Random`
- **Exceptions:** `ConcurrentModificationException`, `NoSuchElementException`

```java
import java.util.*;  // or import specific class

ArrayList<String> list = new ArrayList<>();
HashMap<String, Integer> map = new HashMap<>();
Scanner sc = new Scanner(System.in);
```

> 💡 **Rule of thumb:**
> - `java.lang` → basic types, threads, exceptions — **auto-imported**
> - `java.util` → collections, utilities — **must be imported**

---

## 12. Networking in Java

### java.net Package
Java provides the `java.net` package for all networking operations.

**Key classes:**

| Class | Purpose |
|---|---|
| `InetAddress` / `Inet4Address` | Represents an IPv4 address |
| `Inet6Address` | Represents an IPv6 address |
| `Socket` | Client-side TCP socket connection |
| `ServerSocket` | Server-side TCP socket |
| `URI` | Represents a Universal Resource Identifier (identifier only) |
| `URL` | Represents a Universal Resource Locator (can access the resource) |
| `URLConnection` | Opens a connection to a URL resource |

```java
import java.net.*;

// Get the IP address of a hostname
InetAddress address = InetAddress.getByName("www.google.com");
System.out.println("IP: " + address.getHostAddress());

// Simple URL connection
URL url = new URL("https://www.example.com");
URLConnection conn = url.openConnection();
```

> 💡 **URI vs URL:** `URI` is just an identifier (like a name). `URL` is a locator that can actually connect to and retrieve the resource.

---

## 13. Regular Expressions (Regex)

### What Is Regex?
Regular expressions are **patterns** used to search, match, or validate strings.

### Key Classes

```java
import java.util.regex.*;
```

| Class | Purpose |
|---|---|
| `Pattern` | Compiles the regex pattern |
| `Matcher` | Applies the pattern to a string |

### Common Regex Symbols

| Symbol | Meaning |
|---|---|
| `[a-z]` | Any lowercase letter |
| `[A-Z]` | Any uppercase letter |
| `[0-9]` | Any digit |
| `.*` | Any character, 0 or more times |
| `.+` | Any character, 1 or more times |
| `^` | Start of string |
| `$` | End of string |

### Regex Example

```java
import java.util.regex.*;

public class RegexDemo {
    public static void main(String[] args) {
        // Pattern: start with anything, then letters, then numbers, then anything
        String patternStr = ".*[a-zA-Z]+[0-9]+.*";

        Pattern pattern = Pattern.compile(patternStr);

        String testString = "123abc456";
        Matcher matcher = pattern.matcher(testString);

        if (matcher.find()) {
            System.out.println("Match found!");
        } else {
            System.out.println("Match not found.");
        }
    }
}
```

> ⚠️ Regex can get very complex. Test patterns carefully — wrong patterns can cause false positives/negatives or poor performance.

---

## 14. NIO — Non-Blocking I/O

### Why NIO?
The classic `java.io` package **blocks the thread** while reading/writing a file. The `java.nio` package provides:
- **Non-blocking I/O** — threads don't get stuck waiting
- **Multi-threading support** for I/O operations
- Better performance for large files or network I/O

### Core NIO Concepts

| Concept | Description |
|---|---|
| **Channel** | Like a pipe — the pathway for data (replaces Stream) |
| **Buffer** | A container that holds the data being read/written |
| **Selector** | Monitors multiple channels for readiness (enables non-blocking) |

> 💡 **How it works:**
> - **Reading:** data flows from **Channel → Buffer**
> - **Writing:** data flows from **Buffer → Channel**

### NIO Example — Read and Write a File

```java
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class NIOExample {
    public static void main(String[] args) throws IOException {
        // ── READING with NIO ──
        FileInputStream fis = new FileInputStream("source.txt");
        FileChannel readChannel = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        readChannel.read(buffer);

        buffer.flip(); // Reset buffer position to beginning for reading
        System.out.println("File content read into buffer.");
        readChannel.close();

        // ── WRITING with NIO ──
        FileOutputStream fos = new FileOutputStream("dest.txt");
        FileChannel writeChannel = fos.getChannel();

        writeChannel.write(buffer);  // Write buffer contents to file
        writeChannel.close();

        System.out.println("File written successfully.");
    }
}
```

> 💡 **`buffer.flip()`** resets the buffer's position pointer back to the start, making it ready for the next read/write sequence.

---

## 15. Spring Framework

### What Is Spring?
Spring is a **Java application framework** that simplifies enterprise Java development. Its two core principles are:

### 1. Dependency Injection (DI)
Instead of a class creating its own dependencies with `new`, Spring **injects** them automatically.

```java
// Without DI — tightly coupled, hard to test
public class StudentController {
    StudentRepository repo = new StudentRepository(); // manually created
}

// With DI — Spring injects it automatically
public class StudentController {
    @Autowired
    StudentRepository repo; // Spring creates and injects this
}
```

### 2. Inversion of Control (IoC)
The **framework** controls the lifecycle of objects, not the developer. You give up control of object creation to Spring's IoC container.

### Spring Ecosystem

| Module | Purpose |
|---|---|
| Spring Core | DI / IoC container |
| Spring MVC | Web applications (Model-View-Controller) |
| Spring Boot | Rapid development of REST APIs and microservices |
| Spring Data JPA | Database operations |
| Spring Security | Authentication & authorization |

---

## 16. Spring MVC

### MVC Architecture

```
Client (Browser)
    │
    ▼
Dispatcher Servlet  ←──────────────────────────
    │                                           │
    ▼                                           │
Handler Mapping                                 │
    │                                           │
    ▼                                           │
Controller  ──────► Model (Business Logic)      │
    │                                           │
    ▼                                           │
View Resolver                                   │
    │                                           │
    ▼                                           │
View (HTML/Thymeleaf Template) ─────────────────┘
```

- **Dispatcher Servlet** → Front controller that receives all HTTP requests
- **Controller** → Handles the request, calls business logic, returns view name
- **Model** → Holds the data/business logic to pass to the view
- **View Resolver** → Finds the right HTML template
- **View** → Renders the response (Thymeleaf, JSP, etc.)

### Spring MVC Project Setup (pom.xml dependencies)

```xml
<dependencies>
    <!-- Thymeleaf — view technology (HTML templates) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Spring Web — enables web app features -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- DevTools — hot reload without server restart -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Creating a Controller

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // Marks this class as a Spring MVC Controller
public class GreetingController {

    @GetMapping("/greeting")  // Maps GET requests to /greeting URL
    public String greeting(
        @RequestParam(name = "name", required = false, defaultValue = "World") String name,
        Model model) {

        model.addAttribute("name", name);  // Add data to the model
        return "greeting";  // Return view name → resolves to templates/greeting.html
    }
}
```

### Thymeleaf Template (greeting.html)

```html
<!DOCTYPE html>
<html>
<head><title>Greeting</title></head>
<body>
    <h1>Hello, <span th:text="${name}">World</span>!</h1>
    <!-- ${name} renders the model attribute value dynamically -->
</body>
</html>
```

### Running the App

```bash
mvn spring-boot:run
```

Access: `http://localhost:8080/greeting` → displays "Hello, World!"  
Access: `http://localhost:8080/greeting?name=Alice` → displays "Hello, Alice!"

---

## 17. Spring REST APIs

### Spring MVC vs Spring REST

| Spring MVC | Spring REST (Spring Boot) |
|---|---|
| Returns a **view** (HTML page) | Returns **data** (JSON/XML) |
| `@Controller` | `@RestController` |
| Uses view technology (Thymeleaf/JSP) | No view layer |
| For web applications | For REST APIs / microservices |

### REST Controller Annotations

| Annotation | HTTP Method | Use |
|---|---|---|
| `@GetMapping` | GET | Retrieve data |
| `@PostMapping` | POST | Create data |
| `@PutMapping` | PUT | Update data |
| `@DeleteMapping` | DELETE | Delete data |
| `@RequestBody` | — | Read request body (JSON → Java object) |
| `@PathVariable` | — | Read URL path parameter |
| `@RequestParam` | — | Read query string parameter |

### REST Controller Example

```java
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController  // Marks as REST controller — returns data, not view
public class StudentController {

    @Autowired
    StudentRepository studentRepository;  // Injected by Spring

    // POST: Create a student
    // URL: POST http://localhost:8080/student/create
    @PostMapping("/student/create")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);  // Returns saved object as JSON
    }

    // GET: Retrieve a student by ID
    // URL: GET http://localhost:8080/student/1
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).get();
    }
}
```

---

## 18. Spring Boot Project (Student API)

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.example/
│   │       ├── MainApplication.java      ← Entry point
│   │       ├── Student.java              ← Entity class
│   │       ├── StudentRepository.java    ← Repository (DB layer)
│   │       └── StudentController.java    ← REST Controller
│   └── resources/
│       └── application.properties        ← Config (DB URL, port, etc.)
└── test/
```

### 1. Entry Point

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Mandatory annotation on the entry point class
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```

### 2. Entity Class (Student.java)

```java
import javax.persistence.*;

@Entity  // Maps this class to a DB table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate ID
    private Long id;

    private String name;
    private int age;
    private String address;

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
```

### 3. Repository (StudentRepository.java)

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Zero code needed! JpaRepository provides:
    // save(), findById(), findAll(), count(), delete(), deleteById()...
}
```

> 💡 **Spring Magic:** By extending `JpaRepository`, you automatically get all CRUD operations — no implementation needed!

### 4. REST Controller (StudentController.java)

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // Create student
    @PostMapping("/student/create")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Get student by ID
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }
}
```

### 5. application.properties (H2 In-Memory DB)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### 6. Run the Application

```bash
mvn spring-boot:run
```

App starts on: `http://localhost:8080`

### 7. Testing with REST Client (e.g., Postman or Advanced REST Client)

**Create a student (POST):**
```
POST http://localhost:8080/student/create
Content-Type: application/json

{
  "name": "Alice",
  "age": 21,
  "address": "Chennai"
}
```
Response: `200 OK` + the saved student object with auto-generated ID

**Fetch a student (GET):**
```
GET http://localhost:8080/student/1
```
Response: `200 OK` + the student JSON  
If ID doesn't exist: `500 Internal Server Error`

---

## 📌 Quick Reference Cheat Sheet

| Topic | Key Takeaway |
|---|---|
| **Enum** | Use for constants that never change. Use `.name()` and `.values()` |
| **Annotation** | Metadata for compiler/runtime. Use `@interface` to define custom annotations |
| **Serialization** | Implement `Serializable`. Use `transient` to exclude fields |
| **Multithreading** | Extend `Thread` OR implement `Runnable`. Always call `.start()` not `.run()` |
| **Synchronization** | Use `synchronized` to prevent race conditions. Method-level or block-level |
| **Autoboxing** | Automatic int↔Integer conversion. Needed for collections |
| **I/O Streams** | Input = Read. Output = Write. Byte streams for binary, Char streams for text |
| **JDBC** | `DriverManager.getConnection()` → `Statement` → `execute()`. Always close connection |
| **Generics** | Type-safe reusable code. Use `<T>` placeholder |
| **java.lang** | Auto-imported. Contains types, Thread, String, Math, exceptions |
| **java.util** | Must import. Contains Collections, Scanner, Arrays |
| **Regex** | `Pattern.compile()` + `matcher.find()`. Use `.*`, `[a-z]+`, `[0-9]+` patterns |
| **NIO** | Channel = pipe, Buffer = container. `buffer.flip()` to reset position |
| **Spring DI** | `@Autowired` — Spring injects dependencies automatically |
| **Spring MVC** | `@Controller` + `@GetMapping` + `Model` + Thymeleaf view |
| **Spring REST** | `@RestController` + `@PostMapping/@GetMapping` + `@RequestBody/@PathVariable` |
| **Spring Boot** | `@SpringBootApplication` + `JpaRepository` = zero-boilerplate CRUD |

---

*Notes compiled from SimplyLearn Advanced Java Full Course*
