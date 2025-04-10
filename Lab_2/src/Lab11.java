import java.util.Scanner;

public class Lab11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BlueRayCollection collection = new BlueRayCollection();
        int option;

        do {
            System.out.println("\n[BlueRay Disk Collection]");
            System.out.println("1. Add to collection");
            System.out.println("2. See collection");
            System.out.println("3. Quit");
            System.out.print("Enter option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        System.out.print("\nEnter disk title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter director name: ");
                        String director = scanner.nextLine();

                        int yearOfRelease;
                        try {
                            System.out.print("Enter year of release: ");
                            yearOfRelease = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Year of release must be a whole number!");
                            continue;
                        }

                        double cost;
                        try {
                            System.out.print("Enter price of disk: $");
                            cost = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Price must be a number!");
                            continue;
                        }

                        collection.addDisk(title, director, yearOfRelease, cost);
                        break;

                    case 2:
                        String collectionList = collection.showAll();
                        if (collectionList.isEmpty()) {
                            System.out.println("BlueRay collection is empty");
                        } else {
                            System.out.println("\nHere's your current collection:");
                            System.out.println(collectionList);
                        }
                        break;

                    case 3:
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