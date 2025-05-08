package JavaQuestions;

class Vehicle {
    String type="General Vehicle";
    void start() {
        System.out.println("Starting the vehicle");
    }
}
class Car extends Vehicle {
    String type="Car";
    @Override
    void start() {
        System.out.println("Starting the car");
    }
}
public class q16 {

    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        System.out.println("Type: " + vehicle.type);
        vehicle.start();

        //Type: General Vehicle
        //Starting the car

        //Fields are invoked by reference types
        //while methods are invoked by actual type
    }
}