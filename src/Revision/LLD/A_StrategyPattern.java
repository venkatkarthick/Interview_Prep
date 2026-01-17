package Revision.LLD;

public class A_StrategyPattern {
    //It is a behavioural design pattern that defines multiple algorithms, encapsulates their logic in dedicated classes and enables changing an algo's behaviour at runtime.
    //It's useful when multiple ways to perform a task and want to choose the approach dynamically,
    //Problems Addressed: Code duplication in multiple sub-classes. Tight coupling: If in future, we need to change the drive functionality of Normal Car, we need to update the class.

    interface DriveStrategy{
        public void drive();
    }
    static class NormalDrive implements DriveStrategy{
        @Override
        public void drive() {
            System.out.println("It is Normal Drive!");
        }
    }
    static class SpecialDrive implements DriveStrategy{
        @Override
        public void drive() {
            System.out.println("It is Special Drive!");
        }
    }

    static class Vehicle {
        DriveStrategy obj;
        Vehicle(DriveStrategy obj) {
            this.obj=obj;
        }
        public void drive() {
            obj.drive();
        }
    }
    static class NormalCar extends Vehicle{
        NormalCar(DriveStrategy driveStrategy) {
            super(driveStrategy);
        }
    }
    static class SportsCar extends Vehicle{
        SportsCar(DriveStrategy driveStrategy) {
            super(driveStrategy);
        }
    }
    static class OffRoadVehicle extends Vehicle{
        OffRoadVehicle(DriveStrategy driveStrategy) {
            super(driveStrategy);
        }
    }

    public static void main(String[] args) {
        Vehicle vehicle=new NormalCar(new NormalDrive());
        vehicle.drive();

        Vehicle sportsCar = new SportsCar(new SpecialDrive()); //Tight coupling is removed here with dependency injection
        sportsCar.drive();
    }
}
