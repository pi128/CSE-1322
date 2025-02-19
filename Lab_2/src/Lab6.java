import java.util.Scanner;

public class Lab6 {
    public interface FindFib {
        int calculateFib(int n);

        class FibIteration implements FindFib {
            @Override
            public int calculateFib(int num) {
                if (num == 0) return 0;
                if (num == 1) return 1;

                int a = 0, b = 1, new_b;
                for (int i = 2; i <= num; i++) {
                    new_b = b + a;
                    a = b;
                    b = new_b;
                }
                return b;
            }

        }

        class FibFormula implements FindFib {
            @Override
            public int calculateFib(int num) {
                double sqrt5 = Math.sqrt(5);
                double GR = (1 + sqrt5) / 2;  // Golden Ratio
                double GRC = (1 - sqrt5) / 2; // Golden Ratio Conjugate

                return (int) Math.round((Math.pow(GR, num) - Math.pow(GRC, num)) / sqrt5);
            }
        }



    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        FindFib iterative = new FindFib.FibIteration();
        FindFib.FibFormula formula = new FindFib.FibFormula();

        System.out.print("Find which position in the Fibonacci Sequence? ");
        int resp = scan.nextInt();

        if (resp >= 40) {
            System.out.println("Please enter a number between 0 and 40.");
        } else {
            System.out.println("Fib of " + resp + " using iteration is " + iterative.calculateFib(resp));
            System.out.println("Fib of " + resp + " using Binet's Formula is " + iterative.calculateFib(resp));
        }

        System.out.println("Program complete.");
        scan.close();

    }
}
