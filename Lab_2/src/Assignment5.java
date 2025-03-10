import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[DNA Reverser and Translator]");
        System.out.print("Enter a sequence: ");
        String input = scanner.nextLine().trim().toUpperCase();

        // Validate the input DNA sequence.
        if (DNALib.validator(input)) {
            // Process the DNA sequence by reversing, then inversing, and finally translating.
            String reversed = DNALib.reverser(input);
            String inversed = DNALib.inverser(reversed);
            String translated = DNALib.translator(inversed);
            System.out.println("Your DNA sequence reversed and translated is: ");
            System.out.println(translated);
        } else {
            System.out.println("Your DNA sequence is not valid.");
        }
        scanner.close();
    }
}