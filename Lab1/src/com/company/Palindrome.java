package com.company;

public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];

            if (isPalindrome(s)){
                System.out.println(s + " - palindrome");
            }
            else{
                System.out.println(s + " - not palindrome");
            }
        }
    }

    public static String reverseString(String str){
        String result = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }

        return result;
    }

    public static boolean isPalindrome(String str){
        String reverse = reverseString(str);
        return str.equals(reverse);
    }
}
