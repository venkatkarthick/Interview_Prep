# Null Object Design pattern

- Consider the below code

```java
private static void printVehicleDetails(Vehicle vehicle) {
    System.out.println("Seating Capacituy : " + vehicle.getSeatingCapacity());
    System.out.println("Fuel Tank Capacituy : " + vehicle.getTankCapacity());
}
```
- Here Vehicle object can hold some value or null and code may throw NullPointer Exception
- To fix this, we need to wrap the above code with null check
```java
private static void printVehicleDetails(Vehicle vehicle) {
    if(vehicle!=null) {
    System.out.println("Seating Capacituy : " + vehicle.getSeatingCapacity());
    System.out.println("Fuel Tank Capacituy : " + vehicle.getTankCapacity());
    }
}
```
- These null checks can be highly redundant in the entire codebase. How to avoid these null checks?
- We can use Null Object Design Pattern over here.
  - A null object replaces NULL return type
  - No need to put IF CHECK for checking NULL
  - Null object reflects do nothing or default behaviour

