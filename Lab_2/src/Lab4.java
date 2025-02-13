import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkWallet wallet = new ParkWallet(100); // Initial tickets

        while (true) {
            System.out.println("\n[Theme Park Wallet Manager]");
            System.out.println("1. Add tickets");
            System.out.println("2. Set tickets");
            System.out.println("3. Buy prize" + (ParkWallet.isHoliday() ? " at holiday prices!" : ""));
            System.out.println("4. Set holiday");
            System.out.println("5. Quit");
            System.out.println("Your wallet has " + wallet.getTickets() + " tickets");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("\nAdd how many tickets? ");
                int amount = scanner.nextInt();
                wallet.addTickets(amount);

            } else if (choice == 2) {
                System.out.print("\nSet ticket balance to: ");
                int amount = scanner.nextInt();
                wallet.setTickets(amount);

            } else if (choice == 3) {
                int[] prices = {150, 350, 600};
                String[] prizes = {"T-shirt", "Sun hat", "Sneakers"};

                if (ParkWallet.isHoliday()) {
                    for (int i = 0; i < prices.length; i++) {
                        prices[i] /= 2; // 50% off during holidays
                    }
                }

                System.out.println("\n" + (ParkWallet.isHoliday() ? "Holiday Prices!" : ""));
                for (int i = 0; i < prizes.length; i++) {
                    System.out.println((i + 1) + ". " + prizes[i] + " (" + prices[i] + " tickets)");
                }
                System.out.print("Buy which prize? ");
                int prizeChoice = scanner.nextInt();

                if (prizeChoice >= 1 && prizeChoice <= 3) {
                    if (wallet.removeTickets(prices[prizeChoice - 1])) {
                        System.out.println("Bought a " + prizes[prizeChoice - 1] + " for " + prices[prizeChoice - 1] + " tickets.");
                    } else {
                        System.out.println("Not enough tickets to buy a " + prizes[prizeChoice - 1]);
                    }
                } else {
                    System.out.println("Invalid choice.");
                }

            } else if (choice == 4) {
                ParkWallet.toggleHoliday();
                System.out.println(ParkWallet.isHoliday() ? "It is now a holiday." : "It is no longer a holiday.");

            } else if (choice == 5) {
                System.out.println("\nShutting off...");
                break;

            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }
}

// ParkWallet Class inside the same file
class ParkWallet {
    private int tickets;
    private static boolean holiday = false;

    // Constructors
    public ParkWallet() {
        this.tickets = 0;
    }

    public ParkWallet(int tickets) {
        this.tickets = Math.max(tickets, 0); // Ensures no negative value
    }

    // Getters
    public int getTickets() {
        return tickets;
    }

    public static boolean isHoliday() {
        return holiday;
    }

    // Setters
    public void setTickets(int tickets) {
        if (tickets >= 0) {
            this.tickets = tickets;
        }
    }

    public static void toggleHoliday() {
        holiday = !holiday;
    }

    // Methods
    public void addTickets(int amount) {
        if (amount > 0) {
            this.tickets += amount;
        }
    }

    public boolean removeTickets(int amount) {
        if (amount > 0 && this.tickets >= amount) {
            this.tickets -= amount;
            return true;
        }
        return false;
    }
}
