//Write a program to input principal, time, and rate (P, T, R) from the user and find Simple Interest.

import java.util.Scanner;

public class SimpleInterest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter principal : ");
        int principal = input.nextInt();
        System.out.println("Enter time : ");
        int time = input.nextInt();
        System.out.println("Enter rate : ");
        int rate = input.nextInt();

        int si = (principal * time * rate) / 100;
        System.out.println("Simple Interest : " + si);
    }
}
