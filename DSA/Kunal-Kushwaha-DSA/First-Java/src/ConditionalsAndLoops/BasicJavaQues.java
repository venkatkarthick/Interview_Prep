import java.util.Scanner;

public class BasicJavaQues {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Subtract the Product and Sum of Digits of an Integer
        System.out.println(subtractProductAndSum(4421));

        //Input a number and print all the factors of that number (use loops).
        System.out.print("Enter a number : ");
        int n = in.nextInt();
        printFactors(n);
        in.nextLine();

        //Take integer inputs till the user enters 0 and print the sum of all numbers (HINT: while loop)
        sumOfAllUserInput();

        //Take integer inputs till the user enters 0 and print the largest number from all.
        maxOfAllUserInput();

        //Addition Of Two Numbers
        System.out.println("Addition Result : " + addTwoNumbers(34, 56));
    }

    private static int addTwoNumbers(int a, int b) {
        return a + b;
    }

    private static void maxOfAllUserInput() {
        Scanner in = new Scanner(System.in);
        int n = -999999;
        int max = -999999;
        System.out.println("Enter numbers to find its max. Hit 0 to exit");
        while(n != 0) {
            n = in.nextInt();
            max = Math.max(max, n);
        }
        System.out.println("Max : " + max);
    }

    private static void sumOfAllUserInput() {
        Scanner in = new Scanner(System.in);
        int n = -1;
        int sum = 0;
        System.out.println("Enter numbers to add. Hit 0 to exit");
        while(n != 0) {
            n = in.nextInt();
            sum += n;
        }
        System.out.println("Sum : " + sum);
    }

    private static void printFactors(int n) {
        for(int i=1; i * i < n; i++) {
            if (n % i == 0) {
                System.out.print(i + " " + n/i + " ");
            }
        }
        System.out.println();
    }

    public static int subtractProductAndSum(int n) {
        int prod = 1, sum = 0;
        while(n>0) {
            int rem = n % 10;
            sum += rem;
            prod *= rem;
            n = n / 10;
        }
        return prod - sum;
    }
}
