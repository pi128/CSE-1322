import java.util.ArrayList;
import java.util.Scanner;

class Tenant {
    public String name;
    public int age;
    public double rent;

    public Tenant(String name, int age, double rent) {
        this.name = name;
        this.age = age;
        this.rent = rent;
    }

    @Override
    public String toString() {
        return name + " (" + age + ") | $" + rent + "/mo";
    }
}

class Building {
    private Tenant[] apartments;
    private static double balance = 0;
    private static int nextId = 1;
    private int id;

    public Building(int size) {
        this.apartments = new Tenant[size];
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public static double getBalance() {
        return balance;
    }

    public int countTenants() {
        int count = 0;
        for (Tenant tenant : apartments) {
            if (tenant != null) count++;
        }
        return count;
    }

    public boolean addTenant(Tenant tenant, int aptNumber) {
        if (aptNumber < 0 || aptNumber >= apartments.length || apartments[aptNumber] != null) {
            return false;
        }
        apartments[aptNumber] = tenant;
        return true;
    }

    public boolean removeTenant(int aptNumber) {
        if (aptNumber < 0 || aptNumber >= apartments.length || apartments[aptNumber] == null) {
            return false;
        }
        apartments[aptNumber] = null;
        return true;
    }

    public String listTenants() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < apartments.length; i++) {
            sb.append("Apartment ").append(i).append(": ");
            if (apartments[i] != null) {
                sb.append(apartments[i].toString());
            } else {
                sb.append("Empty");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Tenant getTenant(int aptNumber) {
        if (aptNumber < 0 || aptNumber >= apartments.length) {
            return null;
        }
        return apartments[aptNumber];
    }

    public static void collectRent(Tenant tenant) {
        balance += tenant.rent;
    }

    public static void payForService(double amount) {
        balance -= amount;
    }
}

public class Assignment2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Building> buildings = new ArrayList<>();

        while (true) {
            System.out.println("\n[Apartment Complex Management System]");
            System.out.println("1. Add building");
            System.out.println("2. Remove building");
            System.out.println("3. List all buildings");
            System.out.println("4. List all tenants");
            System.out.println("5. Add tenant");
            System.out.println("6. Remove tenant");
            System.out.println("7. Update tenant");
            System.out.println("8. Charge rent");
            System.out.println("9. Pay for service");
            System.out.println("0. Quit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("How many apartments? ");
                int size = scanner.nextInt();
                buildings.add(new Building(size));
                System.out.println("Building " + buildings.get(buildings.size() - 1).getId() + " created.");
            } else if (choice == 2) {
                System.out.print("Enter building ID to remove: ");
                int id = scanner.nextInt();
                buildings.removeIf(b -> b.getId() == id);
                System.out.println("Building removed.");
            } else if (choice == 3) {
                for (Building b : buildings) {
                    System.out.println("Building " + b.getId() + ": " + b.countTenants() + " tenants.");
                }
            } else if (choice == 4) {
                System.out.print("Enter building ID: ");
                int id = scanner.nextInt();
                Building building = findBuilding(buildings, id);
                if (building != null) {
                    System.out.println(building.listTenants());
                } else {
                    System.out.println("No such building exists.");
                }
            } else if (choice == 5) {
                System.out.print("Enter building ID: ");
                int id = scanner.nextInt();
                Building building = findBuilding(buildings, id);
                if (building == null) {
                    System.out.println("No such building.");
                    continue;
                }
                System.out.println(building.listTenants());
                System.out.print("Enter tenant name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.print("Enter tenant age: ");
                int age = scanner.nextInt();
                System.out.print("Enter tenant rent: ");
                double rent = scanner.nextDouble();
                System.out.print("Enter apartment number: ");
                int aptNumber = scanner.nextInt();
                if (building.addTenant(new Tenant(name, age, rent), aptNumber)) {
                    System.out.println("Tenant added.");
                } else {
                    System.out.println("Could not add tenant. Invalid apartment.");
                }
            } else if (choice == 6) {
                System.out.print("Enter building ID: ");
                int id = scanner.nextInt();
                Building building = findBuilding(buildings, id);
                if (building == null) {
                    System.out.println("No such building.");
                    continue;
                }
                System.out.println(building.listTenants());
                System.out.print("Enter apartment number to remove: ");
                int aptNumber = scanner.nextInt();
                if (building.removeTenant(aptNumber)) {
                    System.out.println("Tenant removed.");
                } else {
                    System.out.println("Invalid apartment.");
                }
            } else if (choice == 7) {
                System.out.print("Enter building ID: ");
                int id = scanner.nextInt();
                Building building = findBuilding(buildings, id);
                if (building == null) {
                    System.out.println("No such building.");
                    continue;
                }
                System.out.println(building.listTenants());
                System.out.print("Enter apartment number to update: ");
                int aptNumber = scanner.nextInt();
                Tenant tenant = building.getTenant(aptNumber);
                if (tenant == null) {
                    System.out.println("Invalid apartment.");
                    continue;
                }
                System.out.print("Enter new name: ");
                scanner.nextLine();
                tenant.name = scanner.nextLine();
                System.out.print("Enter new age: ");
                tenant.age = scanner.nextInt();
                System.out.print("Enter new rent: ");
                tenant.rent = scanner.nextDouble();
                System.out.println("Tenant updated.");
            } else if (choice == 8) {
                System.out.print("Enter building ID: ");
                int id = scanner.nextInt();
                Building building = findBuilding(buildings, id);
                if (building == null) {
                    System.out.println("No such building.");
                    continue;
                }
                System.out.println(building.listTenants());
                System.out.print("Enter apartment number to charge rent: ");
                int aptNumber = scanner.nextInt();
                Tenant tenant = building.getTenant(aptNumber);
                if (tenant == null) {
                    System.out.println("Invalid apartment.");
                    continue;
                }
                Building.collectRent(tenant);
                System.out.println("Rent charged. New balance: $" + Building.getBalance());
            } else if (choice == 9) {
                System.out.println("Current balance: $" + Building.getBalance());
                System.out.print("Enter service amount: ");
                double amount = scanner.nextDouble();
                Building.payForService(amount);
                System.out.println("New balance: $" + Building.getBalance());
            } else if (choice == 0) {
                System.out.println("Shutting down...");
                break;
            }
        }
        scanner.close();
    }

    private static Building findBuilding(ArrayList<Building> buildings, int id) {
        for (Building b : buildings) {
            if (b.getId() == id) return b;
        }
        return null;
    }
}