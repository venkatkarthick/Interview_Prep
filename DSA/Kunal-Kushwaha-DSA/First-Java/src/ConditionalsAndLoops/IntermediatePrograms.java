package ConditionalsAndLoops;

import java.util.Arrays;

public class IntermediatePrograms {

    public static void main(String[] args) {
        //Find Ncr & Npr
        System.out.println("Ncr & Npr : " + Arrays.toString(findNcrNpr(5, 2)));

        //Reverse A String In Java
        System.out.println("Reverse A String In Java : " + reverseString("Venkat Karthick"));

        //Find if a number is palindrome or not
        System.out.println("Find if a number is palindrome or not - 123321 : " + isPalindrome(123321));

        //Future Investment Value
        System.out.println("Future Investment Value : " + compundInterestValue(1000F, 0.05F, 12F, 3F));

        //HCF Of Two Numbers Program
        System.out.println("HCF Of Two Numbers Program : " + hcfOfTwoNumbers(48, 18));

        //LCM Of Two Numbers
        System.out.println("LCM Of Two Numbers : " + lcmOfTwoNumbers(48, 18));

        //Java Program Vowel Or Consonant
        System.out.println("Java Program Vowel Or Consonant - i : " + vowelOrConsonant("e"));

        //Perfect Number
        System.out.println("Perfect Number - 28 : " + perfectNumber(28));

        //Check Leap Year Or Not
        System.out.println("Check Leap Year Or Not : " + isLeapYear(1900));
    }

    private static boolean isLeapYear(int year) {
//        If the year is divisible by 4, it is a leap year, unless:
//        If the year is divisible by 100, it is not a leap year, unless:
//        If the year is divisible by 400, it is a leap year.
        return year % 4 == 0 && year % 100 == 0 && year % 400 == 0;
    }

    private static boolean perfectNumber(int n) {
        //Sum of divisors = number for perfect number
        int sum = 1;
        for (int i = 2; i * i < n; i++) {
            if(n % i == 0) {
                sum += (i + n / i);
            }
        }
        return sum == n;
    }

    private static String vowelOrConsonant(String word) {
        return Arrays.asList('a', 'e', 'i', 'o', 'u').contains(word.charAt(0)) ? "Vowel" : "Consonant";
    }

    private static int lcmOfTwoNumbers(int a, int b) {
        return a * b / hcfOfTwoNumbers(a, b);
    }

    private static int hcfOfTwoNumbers(int a, int b) {
        //following Euclidean's algo to find hcf
        int maxNum = Math.max(a, b);
        int minNum = Math.min(a, b);
        int rem = maxNum % minNum;
        while(rem > 0) {
            maxNum = minNum;
            minNum = rem;
            rem = maxNum % minNum;
        }
        return minNum;
    }

    private static double compundInterestValue(float principal, float rate, float compoundingInterval, float years) {
        return principal * (Math.pow((1 + rate / compoundingInterval), compoundingInterval * years));
    }

    private static boolean isPalindrome(int n) {
        int reversedNumber = 0, tempNum = n;
        while(tempNum > 0) {
            int rem = tempNum % 10;
            reversedNumber = reversedNumber * 10 + rem;
            tempNum = tempNum / 10;
        }
        return reversedNumber == n;
    }

    private static String reverseString(String name) {
        StringBuilder reversedName = new StringBuilder();
        for (int i = name.length()-1; i >= 0; i--) {
            reversedName.append(name.charAt(i));
        }
        return String.valueOf(reversedName);
    }

    static int[] findNcrNpr(int n, int r) {
        int[] results = new int[2];
        results[0] = factorial(n) / (factorial(r) * factorial(n-r));
        results[1] = factorial(n) / factorial(n-r);
        return results;
    }

    static int factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n-1);
    }
}
