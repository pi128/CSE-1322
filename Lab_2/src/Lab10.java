import java.util.ArrayList;
import java.util.Scanner;

public class Lab10 {
    public static void main(String[] args) {
        ArrayList<Alarm> alarms = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n[Alarm System]");
            System.out.println("1. Create new alarm");
            System.out.println("2. View all alarms");
            System.out.println("3. Quit");
            System.out.print("Enter option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        System.out.print("\nEnter alarm name: ");
                        String name = scanner.nextLine();

                        int seconds;
                        try {
                            System.out.print("Enter alarm timer in seconds: ");
                            seconds = Integer.parseInt(scanner.nextLine());
                            if (seconds <= 0) {
                                System.out.println("Invalid timer: Timer must be a positive whole number.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid timer: Timer must be a whole number.");
                            continue;
                        }

                        Alarm alarm = new Alarm(name, seconds);
                        alarms.add(alarm);
                        System.out.println(alarm.toString());
                        alarm.start();
                        break;

                    case 2:
                        System.out.println("\nHere are all the alarms still running:");
                        for (Alarm alarmInstance : alarms) {
                            if (alarmInstance.isAlive()) {
                                System.out.println(alarmInstance.toString());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Stopping all alarms...");
                        for (Alarm alarmInstance : alarms) {
                            if (alarmInstance.isAlive()) {
                                alarmInstance.interrupt();
                            }
                        }
                        System.out.println("All alarms have been stopped.");
                        System.out.println("Shutting off...");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                option = 0;
            }
        } while (option != 3);

        scanner.close();
    }
}