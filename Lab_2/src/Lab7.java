import java.util.Locale;
import java.util.Scanner;

public class Lab7 {
    public static int recursiveMultiply(int num1 , int num2){
        if (num2 == 0){
            return 0;
        }

        return num1 + recursiveMultiply(num1, num2-1);
    }
    public static int recursiveDivision(int num1, int num2){
        if (num2 == 0){
            return -1;
            //why not just 0??
        }
        if (num1 < num2){
            return 0;
        }
        return ( 1 + recursiveDivision(num1 - num2, num2));
    }
    public static int recursiveRemainder(int num1, int num2){
        if (num2 == 0){
            return 0;
        }
        if (num1 < num2){
            return num1;
        }
        return (recursiveRemainder(num1 - num2, num2));

    }
    public static String recursiveEcho(String resp2, int num1){
        if (num1 == 0){
            return "";

        }
        return resp2 + " " + recursiveEcho(resp2, num1 - 1);
    }
    public static boolean recursiveReverse(String resp1, String resp2){
        resp1 = resp1.toLowerCase(Locale.ROOT);
        resp2 = resp2.toLowerCase(Locale.ROOT);

        if (resp1.isEmpty() && resp2.isEmpty()) {
            return true;
        }

        // Base Case 2: If lengths don't match, they can't be reverses
        if (resp1.length() != resp2.length()) {
            return false;
        }

        // Base Case 3: If the first char of original doesn't match the last char of reversed, return false
        if (resp1.charAt(0) != resp2.charAt(resp2.length() - 1)) {
            return false;
        }

        // Recursive Case: Check the rest of the string by removing first and last characters
        return recursiveReverse(resp1.substring(1), resp2.substring(0, resp2.length() - 1));
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        Boolean sent = true;
        String resp2;
        String resp1;
        int resp;
        int num1;
        int num2;

        while (sent){
            System.out.print("1. Multiply 2 numbers\n" +
                    "2. Divide 2 numbers\n" +
                    "3. Mod 2 numbers\n" +
                    "4. Echo sentence\n" +
                    "5. Determine if reverse\n" +
                    "6. Quit\n" +
                    "Enter option: ");
            resp = scan.nextInt();
            switch (resp){
                case 1:
                    System.out.print("Enter the first number: ");
                    num1 = scan.nextInt();
                    System.out.print("Enter the second number: ");
                    num2 = scan.nextInt();
                    System.out.println("Your product is " + recursiveMultiply(num1 , num2));
                break;
                case 2:
                    System.out.print("Enter the first number: ");
                    num1 = scan.nextInt();
                    System.out.print("Enter the second number: ");
                    num2 = scan.nextInt();
                    System.out.println("Your product is " + recursiveDivision(num1 , num2));
                break;
                case 3:
                    System.out.print("Enter the first number: ");
                    num1 = scan.nextInt();
                    System.out.print("Enter the second number: ");
                    num2 = scan.nextInt();
                    System.out.println("Your product is " + recursiveRemainder(num1 , num2));
                break;
                case 4:
                    System.out.print("Enter your sentence: ");
                    resp2 = scan.next();
                    System.out.print("Repeat how many times? ");
                    num1 = scan.nextInt();
                    System.out.println("Your sentence repeated " + num1 + " times is ");
                    System.out.println(recursiveEcho(resp2,num1));
                break;
                case 5:
                    System.out.print("Enter a sentence: ");
                    resp1 = scan.next();
                    System.out.print("Enter another sentence: ");
                    resp2 = scan.next();
                    if (recursiveReverse(resp1 , resp2)){
                        System.out.println("The sentences are the opposite of each other.");
                    } else {
                        System.out.println("The sentences are NOT the opposite of each other.");
                    }
                break;
                case 6:
                    System.out.println("Shutting off...");
                    sent = false;

            }
        }
    }
}
