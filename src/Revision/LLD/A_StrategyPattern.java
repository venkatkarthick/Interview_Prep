package Revision.LLD;

public class A_StrategyPattern {

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
        NormalCar() {
            super(new NormalDrive());
        }
    }
    static class SportsCar extends Vehicle{
        SportsCar() {
            super(new SpecialDrive());
        }
    }

    public static void main(String[] args) {
        Vehicle vehicle=new NormalCar();
        vehicle.drive();

        Vehicle sportsCar = new SportsCar();
        sportsCar.drive();
    }
}
