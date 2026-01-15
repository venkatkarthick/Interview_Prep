package Revision.LLD;

public class D_FactoryPattern {

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

    //Factory
    static class ShapeFactory{
        public Shape getShape(String name){
            switch(name) {
                case "C" : return new Circle();
                case "S" : return new Square();
                default: return null;
            }
        }
    }

    public static void main(String[] args) {
        ShapeFactory shapeFactory=new ShapeFactory();
        Shape circle = shapeFactory.getShape("C");
        Shape square = shapeFactory.getShape("S");

        circle.calculate();
        square.calculate();
    }
}
