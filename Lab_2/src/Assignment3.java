import java.util.ArrayList;
import java.util.Scanner;

public class Assignment3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> items = new ArrayList<>();

        while (true) {
            System.out.println("\n[VaporStore Item System]");
            System.out.println("1. Add Video Game");
            System.out.println("2. Add Development Kit");
            System.out.println("3. Add Tradable");
            System.out.println("4. Add Collectable");
            System.out.println("5. List all items");
            System.out.println("6. Update price");
            System.out.println("7. Quit");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVideoGame(scanner, items);
                    break;
                case 2:
                    addDevelopmentKit(scanner, items);
                    break;
                case 3:
                    addTradable(scanner, items);
                    break;
                case 4:
                    addCollectable(scanner, items);
                    break;
                case 5:
                    listItems(items);
                    break;
                case 6:
                    updatePrice(scanner, items);
                    break;
                case 7:
                    System.out.println("Shutting off...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addVideoGame(Scanner scanner, ArrayList<Item> items) {
        System.out.print("Enter name of item: ");
        String name = scanner.nextLine();
        System.out.print("Enter price of item: $");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter name of publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Does this game have multiplayer support? (yes/no): ");
        boolean multiplayerSupport = scanner.nextLine().equalsIgnoreCase("yes");

        items.add(new VideoGame(name, price, publisher, multiplayerSupport));
        System.out.println("Item added.");
    }

    private static void addDevelopmentKit(Scanner scanner, ArrayList<Item> items) {
        System.out.print("Enter name of item: ");
        String name = scanner.nextLine();
        System.out.print("Enter price of item: $");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter name of publisher: ");
        String publisher = scanner.nextLine();
        System.out.println("Enter the name of each supported platform, one per line. Enter an empty line when done.");

        ArrayList<String> platforms = new ArrayList<>();
        while (true) {
            String platform = scanner.nextLine();
            if (platform.isEmpty()) break;
            platforms.add(platform);
        }

        items.add(new DevelopmentKit(name, price, publisher, platforms));
        System.out.println("Item added.");
    }

    private static void addTradable(Scanner scanner, ArrayList<Item> items) {
        System.out.print("Enter name of item: ");
        String name = scanner.nextLine();
        System.out.print("Enter price of item: $");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter item's description: ");
        String description = scanner.nextLine();
        System.out.print("How many days before this item can be resold? ");
        int saleDelay = scanner.nextInt();

        items.add(new Tradable(name, price, description, saleDelay));
        System.out.println("Item added.");
    }

    private static void addCollectable(Scanner scanner, ArrayList<Item> items) {
        System.out.print("Enter name of item: ");
        String name = scanner.nextLine();
        System.out.print("Enter price of item: $");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter item's description: ");
        String description = scanner.nextLine();
        System.out.print("Where can this collectable be used? ");
        String type = scanner.nextLine();

        items.add(new Collectable(name, price, description, type));
        System.out.println("Item added.");
    }

    private static void listItems(ArrayList<Item> items) {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }
        System.out.println("\nHere's a list of all items currently available:");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void updatePrice(Scanner scanner, ArrayList<Item> items) {
        System.out.print("Enter id of item: ");
        int id = scanner.nextInt();
        for (Item item : items) {
            if (item.getId() == id) {
                System.out.print("Enter item's new price: $");
                double newPrice = scanner.nextDouble();
                item.setPrice(newPrice);
                System.out.println("Price updated.");
                return;
            }
        }
        System.out.println("No item with that id.");
    }
}

// Base class for all items
class Item {
    private int id;
    private static int nextId = 0;
    private double price;
    private String name;

    public Item(String name, double price) {
        this.id = nextId++;
        this.name = name;
        setPrice(price);
    }

    public int getId() { return id; }
    public double getPrice() { return price; }
    public String getName() { return name; }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
    }

    @Override
    public String toString() {
        return "Item: " + name + " (#" + id + ") | Price: $" + price;
    }
}

class Software extends Item {
    private String publisher;

    public Software(String name, double price, String publisher) {
        super(name, price);
        this.publisher = publisher.isEmpty() ? "Unknown" : publisher;
    }

    public String getPublisher() { return publisher; }

    @Override
    public String toString() {
        return super.toString() + "\n\tPublisher: " + publisher;
    }
}

class VideoGame extends Software {
    private boolean multiplayerSupport;

    public VideoGame(String name, double price, String publisher, boolean multiplayerSupport) {
        super(name, price, publisher);
        this.multiplayerSupport = multiplayerSupport;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t\tMultiplayer support: " + multiplayerSupport;
    }
}

class DevelopmentKit extends Software {
    private ArrayList<String> targetPlatforms;

    public DevelopmentKit(String name, double price, String publisher, ArrayList<String> targetPlatforms) {
        super(name, price, publisher);
        this.targetPlatforms = targetPlatforms.isEmpty() ? new ArrayList<>(java.util.List.of("None")) : targetPlatforms;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tTarget platforms:\n\t" + String.join("\n\t", targetPlatforms);
    }
}

class DigitalGood extends Item {
    private String description;

    public DigitalGood(String name, double price, String description) {
        super(name, price);
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tDescription: " + description;
    }
}

class Tradable extends DigitalGood {
    private int saleDelay;

    public Tradable(String name, double price, String description, int saleDelay) {
        super(name, price, description);
        this.saleDelay = Math.max(0, saleDelay);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tItem can only be sold after being owned for " + saleDelay + " days";
    }
}

class Collectable extends DigitalGood {
    private String type;

    public Collectable(String name, double price, String description, String type) {
        super(name, price, description);
        this.type = type.matches("emoticon|avatar|background") ? type : "nothing";
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tCan be used as " + type;
    }
}