import java.util.Scanner;

public class Lab11 {
    public static void main(String[] args) {
        BlueRayCollection collection = new BlueRayCollection();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (true) {
            System.out.println("\n[BlueRay Disk Collection]");
            System.out.println("1. Add to collection");
            System.out.println("2. See collection");
            System.out.println("3. Quit");
            System.out.print("Enter option: ");

            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a valid number.");
                continue;
            }

            if (option == 1) {
                // Option 1: Add to collection.
                System.out.print("\nEnter disk title: ");
                String title = scanner.nextLine();

                System.out.print("Enter director name: ");
                String director = scanner.nextLine();

                System.out.print("Enter year of release: ");
                String yearStr = scanner.nextLine();
                int year;
                try {
                    year = Integer.parseInt(yearStr);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Year of release must be a whole number!");
                    continue;
                }

                System.out.print("Enter price of disk: $");
                String priceStr = scanner.nextLine();
                double price;
                try {
                    price = Double.parseDouble(priceStr);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Price must be a number!");
                    continue;
                }

                collection.addDisk(title, director, year, price);
                System.out.println("BlueRay Disk added to collection.");

            } else if (option == 2) {
                // Option 2: See collection.
                String list = collection.showAll();
                if (list.isEmpty()) {
                    System.out.println("BlueRay collection is empty");
                } else {
                    System.out.println("Here's your current collection:");
                    System.out.println(list);
                }
            } else if (option == 3) {
                // Option 3: Quit.
                System.out.println("Shutting off...");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}