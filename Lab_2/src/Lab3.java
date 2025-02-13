import java.util.ArrayList;
import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String[]> phonebook = new ArrayList<>();

        while (true) {
            System.out.println("\n[Phonebook]");
            System.out.println("1. Add contact");
            System.out.println("2. Remove contact");
            System.out.println("3. List contacts");
            System.out.println("4. Search contacts");
            System.out.println("5. Quit");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter the contact's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the contact's phone number: ");
                    String phone = scanner.nextLine();
                    phonebook.add(new String[]{name, phone});
                    System.out.println("Contact added.");
                    break;

                case 2:
                    System.out.print("Enter contact to remove: ");
                    String removeName = scanner.nextLine();
                    boolean removed = false;
                    for (int i = 0; i < phonebook.size(); i++) {
                        if (phonebook.get(i)[0].equals(removeName)) {
                            phonebook.remove(i);
                            removed = true;
                            System.out.println("Contact deleted.");
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("No contact with that name.");
                    }
                    break;

                case 3:
                    if (phonebook.isEmpty()) {
                        System.out.println("The phonebook is empty.");
                    } else {
                        System.out.println("Listing all contacts...");
                        for (String[] contact : phonebook) {
                            System.out.println("Name: " + contact[0] + " | Phone: " + contact[1]);
                        }
                        System.out.println("Done listing contacts.");
                    }
                    break;

                case 4:
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    boolean found = false;
                    System.out.println("Searching all contacts for keyword...");
                    for (String[] contact : phonebook) {
                        if (contact[0].contains(keyword)) {
                            System.out.println("Name: " + contact[0] + " | Phone: " + contact[1]);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No contacts contained the keyword.");
                    }
                    System.out.println("Done searching keyword.");
                    break;

                case 5:
                    System.out.println("Shutting off...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
            }
        }
    }
}
