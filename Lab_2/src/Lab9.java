import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab9 {

    /**
     * Reads both files line by line and returns a string describing
     * the differences.
     *
     * @param f1 the first file
     * @param f2 the second file
     * @return a string indicating differences or if the files are identical
     */
    public static String diff(File f1, File f2) {
        Scanner sc1 = null;
        Scanner sc2 = null;
        StringBuilder result = new StringBuilder();
        int lineNumber = 1;

        try {
            // Attempt to open both files
            sc1 = new Scanner(f1);
            sc2 = new Scanner(f2);

            // Read both files line by line
            while (sc1.hasNextLine() && sc2.hasNextLine()) {
                String line1 = sc1.nextLine();
                String line2 = sc2.nextLine();

                if (!line1.equals(line2)) {
                    result.append("Line " + lineNumber + "\n");
                    result.append("< " + line1 + "\n");
                    result.append("> " + line2 + "\n");
                }
                lineNumber++;
            }

            // Check if one file has extra lines
            if (sc1.hasNextLine() || sc2.hasNextLine()) {
                result.append("Files have different number of lines");
            }

            // If no differences were recorded, files are identical
            if (result.length() == 0) {
                return "Files are identical";
            }

            return result.toString();

        } catch (FileNotFoundException e) {
            // Determine which file could not be opened
            if (!f1.exists()) {
                return f1.getName() + " (The system cannot find the file specified)";
            } else if (!f2.exists()) {
                return f2.getName() + " (The system cannot find the file specified)";
            } else {
                // Fallback in case the file exists but cannot be opened for some other reason
                return e.getMessage();
            }
        } finally {
            // Always close the scanners if they were opened
            if (sc1 != null) {
                sc1.close();
            }
            if (sc2 != null) {
                sc2.close();
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("[Diff Detector]");
        System.out.print("Enter the name of file 1: ");
        String file1Name = input.nextLine();
        System.out.print("Enter the name of file 2: ");
        String file2Name = input.nextLine();

        File file1 = new File(file1Name);
        File file2 = new File(file2Name);

        String diffOutput = diff(file1, file2);
        System.out.println("\n" + diffOutput);
        System.out.println("\nProgram complete");

        input.close();
    }
}