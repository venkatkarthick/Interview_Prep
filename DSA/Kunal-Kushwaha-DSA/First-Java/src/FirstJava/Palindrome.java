//To find out whether the given String is Palindrome or not.

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a word : ");
        String word = input.next();
        int st = 0, en = word.length() - 1;
        boolean flag = false;
        while(st<en) {
            if(word.charAt(st) != word.charAt(en)) {
                System.out.println("Not a palindrome");
                flag = true;
                break;
            }
            st++;en--;
        }
        if(!flag) System.out.println("Palindrome");
    }
}
