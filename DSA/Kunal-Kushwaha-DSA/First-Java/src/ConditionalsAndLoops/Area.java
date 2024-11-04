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

        //Perimeter Of Circle
        System.out.println("Perimeter of circle : " + perimeterOfCircle(4));

        //Perimeter Of Equilateral Triangle
        System.out.println("Perimeter Of Equilateral Triangle : " + perimeterOfEqTriangle(4));

        //Perimeter Of Parallelogram
        System.out.println("Perimeter Of Parallelogram : " + perimeterOfParallelogram(4, 5));

        //Volume Of Cone
        System.out.println("Volume Of Cone : " + volumeOfCone(3, 4));

        //Volume Of Prism
        System.out.println("Volume Of Prism : " + volumeOfPrism(15, 30));

        //Volume Of Cylinder
        System.out.println("Volume Of Cylinder : " + volumeOfCylinder(23, 34));

        //Volume Of Sphere
        System.out.println("Volume Of Sphere : " + volumeOfSphere(15));

        //Volume Of Pyramid
        System.out.println("Volume Of Pyramid : " + volumeOfPyramid(3, 6));

        //Curved Surface Area Of Cylinder
        System.out.println("Curved Surface Area Of Cylinder : " + csaOfCylinder(45, 6));

        //Total Surface Area Of Cube
        System.out.println("Total Surface Area Of Cube : " + tsaOfCylinder(10));
    }

    private static double tsaOfCylinder(int side) {
        return 6 * side * side;
    }

    private static double csaOfCylinder(int rad, int height) {
        return 2 * 3.14 * rad * height;
    }

    private static double volumeOfPyramid(int base, int height) {
        return ((double) 1/3) * base * height;
    }

    private static double volumeOfSphere(int rad) {
        return ((double) 4 /3) * 3.14 * rad * rad * rad;
    }

    private static double volumeOfCylinder(int rad, int height) {
        return 3.14 * rad * rad * height;
    }

    private static double volumeOfPrism(int base, int height) {
        return  base * height;
    }

    private static double volumeOfCone(int rad, int height) {
        return ((double) 1 /3) * 3.14 * rad * rad * height;
    }

    private static double perimeterOfParallelogram(int sideA, int sideB) {
        return 2 * (sideA + sideB);
    }

    private static double perimeterOfEqTriangle(int side) {
        return 3 * side;
    }

    private static double perimeterOfCircle(int rad) {
        double PI = 3.14;
        return 2 * PI * rad;
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
