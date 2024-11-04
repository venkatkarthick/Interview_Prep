//To calculate Fibonacci Series up to n numbers.

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter n value : ");
        int n = input.nextInt();

        if(n >= 1) System.out.println(0 + " ");
        if(n >= 2) System.out.println(1 + " ");
        int num1 = 0, num2 = 1;
        for(int i = 3; i <= n; i++) {
            int res = num1 + num2;
            System.out.println(res + " ");
            num1 = num2;
            num2 = res;
        }
    }
}
