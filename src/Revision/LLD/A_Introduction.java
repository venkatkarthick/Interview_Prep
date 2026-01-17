package Revision.LLD;

import java.util.ArrayList;
import java.util.List;

public class A_Introduction {
    // LLD of 3 types - Creational, Structural and Behavioural
    //Creational - It contains object creation. [Singleton, Builder, Factory, Abstract Factory, Object Pool, Prototype]
    //Structural - It says, how different classes/objects are arranged together so that larger problem can be solved in most flexible way
    //             [Decorator, Proxy, Composite, Adapter, Bridge, Facade, Flyweight]
    //Behavioural - It focus on, how different objects communicate or interact with each other. - (Co-ordination, responsibility, interaction)
    //             [State, Strategy, Observer, Chain of Responsibility, Template, Iterator, Command, Visitor, Mediator, Memento, Null Object]

    //Is-a -> Inheritance. Vehicle class has 2 subclasses-2-wheeler, 4-wheeler. So, 2-Wheeler is a Vehicle.
    //Has-a -> Shows link between 2 objects. Ex: House has rooms, Library has books, School has students. This is generally called as Association.

    //Composition, Aggregation, Association
    //Association is at outer circle and other two are in inner circle in that order

    //Aggregation : Association with weak relationship. Existence of one object is not dependent on another. Ex : Library has books.
    //Books got initialised inside the constructor of Library. If library object goes out of scope so does the book object.
    static class Library{
        List<Book> books;
    }
    static class Book{
        String name;
        Book(String name) {this.name = name;}
    }

    //Composition : Association with strong relationship. Existence of one object is dependent on another. Ex: House has rooms
    static class House{
        List<Room> rooms;
        House() {
            rooms=new ArrayList<>(); //Room object is initialised and maintained along with House.
            rooms.add(new Room("Living Room"));
        }
    }
    static class Room{
        String name;
        Room(String name) {this.name = name;}
    }

    //SOLID
    //Single Responsibility Principle
    //Open/Closed Principle
    //Liskov-Substitution Principle
    //Interface Segragation Priciple
    //Dependency Inversion Principle

    //Single Responsibility Principle - A class should have only one reason to change.
    //Violation
    static class Invoice{
        int quantity;
        //3 responsibilities here
        public int calculateTotal() {return quantity;}
        public void saveToDB() {}
        public void printinvoice() {}
    }
    //If we need to modify the logic for saveToDB, we need to touch the invoice class which is already tested.
    //Solution
    static class InvoiceNew{
        int quantity;
        public int calculateTotal() {return quantity;}
    }
    static class InvoiceDao{
        InvoiceNew invoiceNew;
        InvoiceDao(InvoiceNew invoiceNew) {this.invoiceNew=invoiceNew;}
        public void saveToDB() {}
    }
    //So here we need to test only InvoiceDao class when there is a change in saveToDB without affecting other piece of code.
    //We can group methods logically in one class to avoid class explosion


    //Open/Closed Principle - It states that 'A class should be open for extension but closed for modification'
    //Adding modification to the existing code would pose additional risk and testing effort
    //Violation:
    static class InvoiceDao1{
        Invoice invoice;
        InvoiceDao1(Invoice invoice) {this.invoice=invoice;}
        public void saveToDB() {}
        public void saveToFile() {}
    }
    //Solution
    interface InvoiceDao2{ public void save();}
    static class DBInvoiceDao implements InvoiceDao2{ @Override public void save() {}}
    static class FileInvoiceDao implements InvoiceDao2{ @Override public void save() {}}

    //Liskov-Substitution Principle - It states that objects of a superclass should be replaceable with objects of its subclass without breaking the application
    //Violation
    interface Bike{
        public void turnOnEngine() throws Exception;
        public void turnOffEngine() throws Exception;
        public void calculate();
        public void applyBrakes();
    }
    static class Motorcycle implements Bike{
        int speed;
        boolean isEngineOn;
        @Override
        public void turnOnEngine() {isEngineOn=true;}
        @Override
        public void turnOffEngine() {System.out.println("Engine is off");isEngineOn=false;}
        @Override
        public void calculate() {speed+=10;}
        @Override
        public void applyBrakes() {speed-=10;}
    }
    static class Bicycle implements Bike{
        int speed;
        //Here engine related methods are not applicable thus it violates LSP
        @Override
        public void turnOnEngine() throws Exception {throw new Exception("Not applicable");}
        @Override
        public void turnOffEngine() throws Exception {throw new Exception("Not applicable");}
        @Override
        public void calculate() {speed+=10;}
        @Override
        public void applyBrakes() {speed-=10;}
    }
    static class Client{
        public static void main(String[] args) {
            Bike bike = new Motorcycle();
            try {
                bike.turnOffEngine();
                bike = new Bicycle(); //Replacing one implementation with another but breaks functionality
                bike.turnOffEngine();
            } catch (Exception e) {
                System.out.println("ERROR  : "+e.getMessage());
            }
        }
    }
    //Sub class should implement all methods of parent and should narrows down parent's functionality.
    //Solution:
    interface Engine{
        public void turnOnEngine() throws Exception;
        public void turnOffEngine() throws Exception;
    }
    static abstract class Bike1{
        //All bike related functions goes here
        public void calculate() {}
        public void applyBrakes() {}
    }
    static class Motorcycle1 extends Bike1 implements Engine{
        int speed;
        //Here engine related methods are not applicable thus it violates LSP
        @Override
        public void turnOnEngine() throws Exception {throw new Exception("Not applicable");}
        @Override
        public void turnOffEngine() throws Exception {throw new Exception("Not applicable");}
        @Override
        public void calculate() {speed+=10;}
        @Override
        public void applyBrakes() {speed-=10;}
    }
    static class Bicycle1 extends Bike1{
        int speed;
        @Override
        public void calculate() {speed+=10;}
        @Override
        public void applyBrakes() {speed-=10;}
    }

    //Interface Segragation Priciple - It states that Interfaces should be such that the client should not implement unnecessary functions they do not need.
    //Take the example of Bicycle class. We are unnecessarily forcing it to implement engine related methods. That is a violation.
    //Solution : Separate/Segregate interfaces in a such a way that it groups co-related methods and does specific related tasks.


    //Dependency Inversion Principle - It states that 'high level components should not depend on low-level components directly, instead they should depend on abstraction.
    interface Keyboard { public void getSpecifications();}
    static class WiredKeyboard implements Keyboard {
        public void getSpecifications(){}
        @Override public String toString() {return "WiredKeyboard";}
    }
    static class BlueToothKeyboard implements Keyboard {
        public void getSpecifications(){}
        @Override public String toString() {return "BlueToothKeyboard";}
    }
    //Violation:
    static class MacBook{
        public WiredKeyboard wiredKeyboard;
        MacBook() {wiredKeyboard=new WiredKeyboard();} //Here if we need to change the dependency from wired to bluetooth keyboard. We need to modify this class.
        @Override public String toString() {return wiredKeyboard.toString();}
    }
    //Solution: (Instead Modifiying, We can let the client decide what to use and 'has a' interface instead of concrete class)
    static class MacBook1{
        public Keyboard keyboard;
        MacBook1(Keyboard keyboard) {this.keyboard=keyboard;}
        @Override public String toString() {return keyboard.toString();}
    }
    static class Client1{
        public static void main(String[] args) {
            MacBook macBook=new MacBook();
            System.out.println(macBook);
            MacBook1 macBook1=new MacBook1(new WiredKeyboard());
            MacBook1 macBook2=new MacBook1(new BlueToothKeyboard());
            System.out.println(macBook1 + " " + macBook2);
        }
    }
}
