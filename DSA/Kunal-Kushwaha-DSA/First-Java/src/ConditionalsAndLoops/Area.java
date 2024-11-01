public class Area {
    public static void main(String[] args) {
        //Area Of Circle Java Program
        System.out.println("Area of circle : " + areaOfCircle(5));

        //Area Of Triangle
        System.out.println("Area of triangle : " + areaOfTriangle(4, 5));

        //Area Of Rectangle Program
        System.out.println("Area of Rectangle : " + areaOfRectangle(4, 5));

        //Area Of Equilateral Triangle
        System.out.println("Area Of Equilateral Triangle : " + areaOfEqTriangle(4));
    }

    static double areaOfEqTriangle(int side) {
        double constant = 0.433;
        return constant * side * side;
    }

    static double areaOfRectangle(int base, int height) {
        return base * height;
    }

    static double areaOfTriangle(int base, int height) {
        double HALF = 0.5;
        return HALF * base * height;
    }

    static double areaOfCircle(int radius) {
        double PI = 3.14;
        return PI * radius * radius;
    }
}
