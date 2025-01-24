import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        boolean sentinel = true;
        while (sentinel){
            System.out.print("1. Count from a number to another\n" +
                    "2. Determine largest number\n" +
                    "3. Type in word\n" +
                    "4. Quit\n" +
                    "Enter option: ");

            int resp = scan.nextInt();

            switch (resp){
                case 1:
                    System.out.print("Enter the start point: ");
                    int start = scan.nextInt();
                    System.out.print("Enter the end point: ");
                    int end = scan.nextInt();

                    System.out.println("Counting from " + start + " to " + end + "...");

                    if (start == end ){
                        System.out.println("Start and end are the same!");

                    } else if (start < end) {
                        for (int i = start; i <= end; i++){
                            System.out.println(i);
                        }
                    } else if (start > end){
                        for (int i = start; i >= end; i--){
                            System.out.println(i);
                        }
                    }
                    System.out.println("Done counting.");
                    break;

                case 2:
                    System.out.print("This option will display the largest number entered. Enter 0 when\n" +
                            "done.");

                    int largest = 0;
                    int num = 0;

                    do {
                        System.out.print("Enter a number (current largest is "+ largest +"):");
                        num = scan.nextInt();

                        if (num > largest){
                            largest = num;
                        }

                    } while (num != 0);

                    System.out.println("The largest number entered was " + largest);
                    break;

                case 3:
                    System.out.print("Type in the word 'Computer': ");
                    String input = scan.next();


                    if (input.equals("Computer")) {
                        System.out.println("Correct!");
                    } else {
                        while (!input.equals("Computer")) {
                            System.out.print("Incorrect. You must type 'Computer': ");
                            input = scan.next();
                        }
                        System.out.println("Correct!");
                        break;
                    }

                case 4:
                    System.out.println("Shutting off...");
                    sentinel = false;
            }
        }
    }
}