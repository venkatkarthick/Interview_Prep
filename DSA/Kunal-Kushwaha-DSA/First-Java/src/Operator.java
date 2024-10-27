//Take in two numbers and an operator (+, -, *, /) and calculate the value. (Use if conditions)

import java.util.Scanner;

public class Operator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter two numbers : ");
        int num1 = input.nextInt();
        input.nextLine();
        int num2 = input.nextInt();
        input.nextLine();
        System.out.println("Enter any of the operator - (+ , -, *, /) : ");
        String operator = input.next();

        int result = 0;
        switch (operator) {
            case "+" : result = num1 + num2; break;
            case "-" : result = num1 - num2; break;
            case "*" : result = num1 * num2; break;
            case "/" : result = num1 / num2; break;
            default:
                System.out.println("Enter a valid operator");
        }
        System.out.println("Result : " + result);
    }
}
