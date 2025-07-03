package LLD.ConceptAndCoding.D_FactoryPattern.FactoryPattern;

import LLD.ConceptAndCoding.D_FactoryPattern.FactoryPattern.Products.Circle;
import LLD.ConceptAndCoding.D_FactoryPattern.FactoryPattern.Products.Shape;
import LLD.ConceptAndCoding.D_FactoryPattern.FactoryPattern.Products.Square;

public class ShapeFactory {

    public Shape getShape(String input) {
        return switch (input) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            default -> null;
        };
    }
}
