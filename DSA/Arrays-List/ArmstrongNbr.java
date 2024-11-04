//To find Armstrong Number between two given number

import java.util.Scanner;

public class ArmstrongNbr {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number : ");
        int num = input.nextInt();
        int tempNum =  num;
        int sum = 0;
        while(num > 0) {
            int rem = num % 10;
            sum += Math.pow(rem, 3);
            num = num / 10;
        }
        if(sum == tempNum) {
            System.out.println("Armstrong number");
        } else {
            System.out.println("Not an armstrong number");
        }
    }
}
