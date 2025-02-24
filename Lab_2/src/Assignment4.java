import java.util.*;

interface ElectricEngine {
    double rechargeBattery(double amount);
    double getMaxBattery();
    double getCurrentCharge();
}

interface GasEngine {
    double refuelTank(double amount);
    double getTankCapacity();
    double getCurrentVolume();
}

abstract class Car {
    private static int nextId = 0;
    private int id;
    public Car() { id = nextId++; }
    public int getId() { return id; }
}

class GasCar extends Car implements GasEngine {
    private double tankCapacity, currentVolume;
    public GasCar(double tankCapacity, double currentVolume) {
        this.tankCapacity = tankCapacity;
        this.currentVolume = currentVolume;
    }
    public double refuelTank(double amount) {
        double refueled = Math.min(amount, tankCapacity - currentVolume);
        currentVolume += refueled;
        return refueled;
    }
    public double getTankCapacity() { return tankCapacity; }
    public double getCurrentVolume() { return currentVolume; }
    public String toString() { return "Car: " + getId() + " | Current Fuel: " + currentVolume + "/" + tankCapacity + " gals"; }
}

class ElectricCar extends Car implements ElectricEngine {
    private double maxBattery, currentCharge;
    public ElectricCar(double maxBattery, double currentCharge) {
        this.maxBattery = maxBattery;
        this.currentCharge = currentCharge;
    }
    public double rechargeBattery(double amount) {
        double recharged = Math.min(amount, maxBattery - currentCharge);
        currentCharge += recharged;
        return recharged;
    }
    public double getMaxBattery() { return maxBattery; }
    public double getCurrentCharge() { return currentCharge; }
    public String toString() { return "Car: " + getId() + " | Current Charge: " + currentCharge + "/" + maxBattery + " kW"; }
}

class HybridCar extends Car implements GasEngine, ElectricEngine {
    private double maxBattery, currentCharge, tankCapacity, currentVolume;
    public HybridCar(double maxBattery, double currentCharge, double tankCapacity, double currentVolume) {
        this.maxBattery = maxBattery;
        this.currentCharge = currentCharge;
        this.tankCapacity = tankCapacity;
        this.currentVolume = currentVolume;
    }
    public double rechargeBattery(double amount) {
        double recharged = Math.min(amount, maxBattery - currentCharge);
        currentCharge += recharged;
        return recharged;
    }
    public double getMaxBattery() { return maxBattery; }
    public double getCurrentCharge() { return currentCharge; }
    public double refuelTank(double amount) {
        double refueled = Math.min(amount, tankCapacity - currentVolume);
        currentVolume += refueled;
        return refueled;
    }
    public double getTankCapacity() { return tankCapacity; }
    public double getCurrentVolume() { return currentVolume; }
    public String toString() { return "Car: " + getId() + " | Current Charge: " + currentCharge + "/" + maxBattery + " kW | Current Fuel: " + currentVolume + "/" + tankCapacity + " gals"; }
}

public class Assignment4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<Car> cars = new ArrayList<>();
        double balance = 0;
        while (true) {
            System.out.println("1. Add gas car\n2. Add electric car\n3. Add hybrid car\n4. Refuel all gas engines\n5. Recharge all electric engines\n6. Refuel and recharge all vehicles\n7. Display all vehicles\n8: Dispatch all vehicles\n9. Quit");
            int option = scanner.nextInt();
            if (option == 9) break;
            switch (option) {
                case 1 -> cars.add(new GasCar(random.nextInt(16) + 15, random.nextInt(13) + 2));
                case 2 -> cars.add(new ElectricCar(random.nextInt(61) + 40, random.nextInt(29) + 2));
                case 3 -> cars.add(new HybridCar(random.nextInt(61) + 40, random.nextInt(29) + 2, random.nextInt(16) + 15, random.nextInt(13) + 2));
                case 4 -> balance += cars.stream().filter(c -> c instanceof GasEngine).mapToDouble(c -> ((GasEngine) c).refuelTank(((GasEngine) c).getTankCapacity() - ((GasEngine) c).getCurrentVolume()) * 4).sum();
                case 5 -> balance += cars.stream().filter(c -> c instanceof ElectricEngine).mapToDouble(c -> ((ElectricEngine) c).rechargeBattery(((ElectricEngine) c).getMaxBattery() - ((ElectricEngine) c).getCurrentCharge()) * 0.2).sum();
                case 6 -> balance += cars.stream().filter(c -> c instanceof GasEngine).mapToDouble(c -> ((GasEngine) c).refuelTank(((GasEngine) c).getTankCapacity() - ((GasEngine) c).getCurrentVolume()) * 4).sum() + cars.stream().filter(c -> c instanceof ElectricEngine).mapToDouble(c -> ((ElectricEngine) c).rechargeBattery(((ElectricEngine) c).getMaxBattery() - ((ElectricEngine) c).getCurrentCharge()) * 0.2).sum();
                case 7 -> cars.forEach(System.out::println);
                case 8 -> cars.clear();
            }
        }
        System.out.println("We've made a total of $" + balance + " today\nShutting off...");
    }
}
