import java.util.Scanner;
import java.util.InputMismatchException;

public class Lab8 {

    public static int differenceInSeconds(String start, String end) throws InvalidTimeException {
        int startSec = convertToSeconds(start);
        int endSec = convertToSeconds(end);
        return endSec - startSec;
    }

    private static int convertToSeconds(String timestamp) throws InvalidTimeException {
        String[] parts = timestamp.split(":");
        if (parts.length != 3) {
            throw new InvalidTimeException("Timestamp must be in format HH:MM:SS");
        }

        int hours, minutes, seconds;

        try {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidTimeException("You must enter integers for the hours, minutes, and seconds");
        }

        if (hours < 0) {
            throw new InvalidTimeException("Hours must be greater than or equal to 0");
        }
        if (hours >= 24) {
            throw new InvalidTimeException("Hours must be less than 24");
        }
        if (minutes < 0) {
            throw new InvalidTimeException("Minutes must be greater than or equal to 0");
        }
        if (minutes >= 60) {
            throw new InvalidTimeException("Minutes must be less than 60");
        }
        if (seconds < 0) {
            throw new InvalidTimeException("Seconds must be greater than or equal to 0");
        }
        if (seconds >= 60) {
            throw new InvalidTimeException("Seconds must be less than 60");
        }

        return (hours * 3600) + (minutes * 60) + seconds;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n[Time Calculator]");
            System.out.println("1. Calculate difference in seconds");
            System.out.println("2. Exit");
            System.out.print("Enter your option: ");
            String option = scanner.nextLine();

            if (option.equals("2")) {
                System.out.println("\nShutting off...");
                break;
            } else if (option.equals("1")) {
                try {
                    System.out.print("\nEnter the start timestamp: ");
                    String start = scanner.nextLine();
                    System.out.print("Enter the end timestamp: ");
                    String end = scanner.nextLine();

                    int diff = differenceInSeconds(start, end);
                    System.out.println("The difference between " + start + " and " + end + " is " + diff + " seconds");
                } catch (InvalidTimeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("You must enter integers for the hours, minutes, and seconds");
                }
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}