import java.util.Scanner;

public class Lab5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Checking checkingAccount = new Checking(0);
        Savings savingsAccount = new Savings(500);

        while (true) {
            System.out.println("\n[Banking System]");
            System.out.println("1. Withdraw from Checking");
            System.out.println("2. Withdraw from Savings");
            System.out.println("3. Deposit to Checking");
            System.out.println("4. Deposit to Savings");
            System.out.println("5. Balance of Checking");
            System.out.println("6. Balance of Savings");
            System.out.println("7. Award Interest to Savings");
            System.out.println("8. Quit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("How much would you like to withdraw from Checking? $");
                    double checkWithdraw = scanner.nextDouble();
                    System.out.println("Current balance of Checking is $" + checkingAccount.withdraw(checkWithdraw));
                    break;

                case 2:
                    System.out.print("How much would you like to withdraw from Savings? $");
                    double saveWithdraw = scanner.nextDouble();
                    System.out.println("Current balance of Savings is $" + savingsAccount.withdraw(saveWithdraw));
                    break;

                case 3:
                    System.out.print("How much would you like to deposit to Checking? $");
                    double checkDeposit = scanner.nextDouble();
                    System.out.println("Current balance of Checking is $" + checkingAccount.deposit(checkDeposit));
                    break;

                case 4:
                    System.out.print("How much would you like to deposit to Savings? $");
                    double saveDeposit = scanner.nextDouble();
                    System.out.println("Current balance of Savings is $" + savingsAccount.deposit(saveDeposit));
                    break;

                case 5:
                    System.out.println(checkingAccount);
                    break;

                case 6:
                    System.out.println(savingsAccount);
                    break;

                case 7:
                    System.out.println("Current balance of Savings is $" + savingsAccount.addInterest());
                    break;

                case 8:
                    System.out.println("Shutting off...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

// Base Account class
class Account {
    private int accountNumber;
    private static int nextNumber = 10001;
    protected double accountBalance;

    public Account() {
        this.accountNumber = nextNumber++;
        this.accountBalance = 0;
    }

    public Account(double initialBalance) {
        this.accountNumber = nextNumber++;
        this.accountBalance = initialBalance;
    }

    public double withdraw(double amount) {
        accountBalance -= amount;
        return accountBalance;
    }

    public double deposit(double amount) {
        accountBalance += amount;
        return accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account #" + accountNumber + ", balance $" + accountBalance;
    }
}

// Checking Account subclass
class Checking extends Account {
    public Checking(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public double withdraw(double amount) {
        super.withdraw(amount);
        if (accountBalance < 0) {
            System.out.println("Charging an overdraft fee of $20 because account is below $0");
            accountBalance -= 20;
        }
        return accountBalance;
    }

    @Override
    public String toString() {
        return "Checking " + super.toString();
    }
}

// Savings Account subclass
class Savings extends Account {
    private int numberOfDeposits;

    public Savings(double initialBalance) {
        super(initialBalance);
        this.numberOfDeposits = 0;
    }

    @Override
    public double withdraw(double amount) {
        super.withdraw(amount);
        if (accountBalance < 500) {
            System.out.println("Charging a fee of $10 because you are below $500");
            accountBalance -= 10;
        }
        return accountBalance;
    }

    @Override
    public double deposit(double amount) {
        super.deposit(amount);
        numberOfDeposits++;
        System.out.println("This is deposit " + numberOfDeposits + " to this account");
        if (numberOfDeposits > 5) {
            System.out.println("Charging a fee of $10");
            accountBalance -= 10;
        }
        return accountBalance;
    }

    public double addInterest() {
        double interest = accountBalance * 0.015;
        System.out.println("Customer has earned $" + interest + " in interest");
        return deposit(interest);
    }

    @Override
    public String toString() {
        return "Savings " + super.toString();
    }
}