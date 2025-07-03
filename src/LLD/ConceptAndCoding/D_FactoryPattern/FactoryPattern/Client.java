package LLD.ConceptAndCoding.D_FactoryPattern.FactoryPattern;

import LLD.ConceptAndCoding.D_FactoryPattern.FactoryPattern.Products.Shape;

public class Client {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape square= shapeFactory.getShape("square");
        square.draw();

    }
}
