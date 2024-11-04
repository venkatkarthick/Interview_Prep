//Take 2 numbers as input and print the largest number.
//Input currency in rupees and output in USD

import java.util.Scanner;

public class LargestNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 2 numbers : ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();

        if(num1 > num2) {
            System.out.println("Largest Num : " + num1);
        } else{
            System.out.println("Largest Num : " + num2);
        }

        System.out.println("Enter rupees : ");
        float rupees = input.nextFloat();
        System.out.println("USD : " + rupees/82);
    }
}
