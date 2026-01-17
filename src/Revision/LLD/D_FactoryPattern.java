package Revision.LLD;

public class D_FactoryPattern {

    //Problem Addressed: If object creation logic is changed like from Circle() to Circle(4). We need to change all the references where circle object is created.
    //Solution: We can create a factory class dedicated for object creation. So that changing in one class will serve the purpose.
    //It is a creational design pattern which is used when we want to encapsulate object creation and related creation logic at one place.

    //Products
    interface Shape{
        public void calculate();
    }
    static class Circle implements Shape{
        @Override
        public void calculate() {
            System.out.println("Circle");
        }
    }
    static class Square implements Shape{
        @Override
        public void calculate() {
            System.out.println("Square");
        }
    }
    enum ShapeObjects{
        CIRCLE, SQUARE
    }

    //Simple Factory Pattern
    static class ShapeFactory{
        public Shape getShape(ShapeObjects name){
            switch(name) {
                case CIRCLE : return new Circle();
                case SQUARE : return new Square();
                default: return null;
            }
        }
    }

    static class Client{
        public static void main(String[] args) {
            ShapeFactory shapeFactory=new ShapeFactory();
            Shape circle = shapeFactory.getShape(ShapeObjects.CIRCLE);
            Shape square = shapeFactory.getShape(ShapeObjects.SQUARE);

            circle.calculate();
            square.calculate();
        }
    }

    //Disadvantage: - It violates open-close principle as we need to change the ShapeFactory class when we need to add another object declaration.
    //              - Factory class can become bloated if object creation logic is complex and difficult to manage. It also violates single responsibility principle as it does two things - Selection and creation logic.
    //Solution - If we have complex logic, We can separate out the creation logic to separat factory making separate factory for each product which implements factory interface and having only selection logic inside factory method

    //Factory Method pattern
    interface ShapeFactoryNew{
        public Shape createShape();
    }
    static class CircleFactory implements ShapeFactoryNew{
        @Override
        public Shape createShape() {return new Circle();}
    }
    static class SquareFactory implements ShapeFactoryNew{
        @Override
        public Shape createShape() {return new Square();}
    }
    static class ShapeFactorySelector{
        //Only selection implementation here. Addressed Single Responsibility violation. But still violates open/Close principle
        public Shape getShape(ShapeObjects shapeObjects) {
            switch (shapeObjects) {
                case CIRCLE : return new CircleFactory().createShape();
                case SQUARE: return new SquareFactory().createShape();
                default: return null;
            }
         }
    }
    static class Client1{
        public static void main(String[] args) {
            ShapeFactorySelector shapeFactory=new ShapeFactorySelector();
            Shape circle = shapeFactory.getShape(ShapeObjects.CIRCLE);
            Shape square = shapeFactory.getShape(ShapeObjects.SQUARE);

            square.calculate();
            circle.calculate();
        }
    }
}
