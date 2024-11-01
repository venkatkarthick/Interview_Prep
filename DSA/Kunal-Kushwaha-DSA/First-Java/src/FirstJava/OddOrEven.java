//Write a program to print whether a number is even or odd, also take input from the user.
//Take name as input and print a greeting message for that particular name.

import java.util.Scanner;

public class OddOrEven {
    public static void main(String[] args) {
        System.out.print("Enter a number : ");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        if(num % 2 == 0) {
            System.out.println("Number is even");
        } else {
            System.out.print("Number is odd");
        }

        // Consume the newline character left by nextInt()
        input.nextLine();

        System.out.print("\nEnter your name : ");
        String name = input.nextLine();
        System.out.println("Hello " + name + "! Have a nice day!");
    }
}
