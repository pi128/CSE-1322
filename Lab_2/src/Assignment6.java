import java.io.*;
import java.util.*;

public class Assignment6 {

    /**
     * Reads a FYEMUS file from the given FileInputStream and returns
     * an ArrayList of FYENote objects.
     *
     * @param fis the FileInputStream for the FYEMUS file
     * @return an ArrayList of FYENote objects created from the file
     * @throws Exception if any file reading or specification errors occur
     */
    public static ArrayList<FYENote> loadMusic(FileInputStream fis) throws Exception {
        ArrayList<FYENote> notes = new ArrayList<>();
        int noteCount = fis.read();

        // If no bytes are available
        if (noteCount == -1) {
            throw new FYEMusicReaderException("File is empty");
        }

        // Check that the note count is within 0-127
        if (noteCount < 0 || noteCount > 127) {
            throw new FYEMusicReaderException("Note counter is out of range");
        }

        // For each FYENOTE, read two bytes: note and timing
        for (int i = 0; i < noteCount; i++) {
            int noteValue = fis.read();
            if (noteValue == -1) {
                throw new FYEMusicReaderException("File ended abruptly");
            }
            if (noteValue > 127) {
                throw new FYEMusicReaderException("Note out of range");
            }

            int timingValue = fis.read();
            if (timingValue == -1) {
                throw new FYEMusicReaderException("File ended abruptly");
            }
            if (timingValue > 127) {
                throw new FYEMusicReaderException("Timing out of range");
            }

            // Convert timing from 1/16th second to microseconds
            int convertedTiming = (timingValue * 1000000) / 16;

            // Create the Note object (which implements FYENote) and add it to the list
            notes.add(new Note((byte) noteValue, convertedTiming));
        }
        return notes;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[FYE Music Player]");
            System.out.println("1. Load music");
            System.out.println("2. Play music");
            System.out.println("3. Quit");
            System.out.print("Enter option: ");

            // Single try-catch block inside the menu loop
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (option == 1) {
                    System.out.print("Enter name of music file: ");
                    String filename = scanner.nextLine();
                    FileInputStream fis = new FileInputStream(filename);

                    // Attempt to load the music file into an ArrayList of FYENotes
                    ArrayList<FYENote> notes = loadMusic(fis);
                    FYEMusicPlayer.loadNotes(notes);
                    System.out.println("Music loaded.");
                } else if (option == 2) {
                    System.out.println("Playing music...");
                    FYEMusicPlayer.play();
                    System.out.println("Done playing.");
                } else if (option == 3) {
                    FYEMusicPlayer.close();
                    System.out.println("Shutting off...");
                    break;
                } else {
                    System.out.println("Invalid option.");
                }
            } catch (FYEMusicReaderException e) {
                // Exceptions related to file format errors
                System.out.println("Unable to load file: " + e.getMessage());
            } catch (Exception e) {
                // Other exceptions (for example, file not found)
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}

/*
 * The following classes are part of your submission.
 * FYENote.java is assumed to be provided.
 */

// Note.java
// Implements the FYENote interface. FYENote should define at least the methods getNote() and getTiming().
class Note implements FYENote {
    private byte note;
    private int timing; // in microseconds

    public Note(byte note, int timing) {
        this.note = note;
        this.timing = timing;
    }

    public byte getNote() {
        return note;
    }

    public int getTiming() {
        return timing;
    }
}

// FYEMusicReaderException.java
// This exception extends FYEMusicException.
class FYEMusicReaderException extends FYEMusicException {
    public FYEMusicReaderException(String message) {
        super(message);
    }
}

// FYEMusicException.java
// This is a minimal definition if not already provided.
class FYEMusicException extends Exception {
    public FYEMusicException(String message) {
        super(message);
    }
}

/*
 * FYEMusicPlayer and FYEMusicPlayerException are assumed to be provided.
 * For testing purposes you may include a simple stub:
 */
class FYEMusicPlayer {
    private static ArrayList<FYENote> notes;

    public static void loadNotes(ArrayList<FYENote> loadedNotes) {
        notes = loadedNotes;
    }

    public static void play() throws FYEMusicPlayerException {
        if (notes == null) {
            throw new FYEMusicPlayerException("No music loaded");
        }
        // Simulate playing music by waiting for the total duration of all notes.
        int totalDuration = 0;
        for (FYENote note : notes) {
            totalDuration += note.getTiming();
        }
        try {
            Thread.sleep(totalDuration / 1000); // sleep expects milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void close() {
        notes = null;
    }
}

class FYEMusicPlayerException extends Exception {
    public FYEMusicPlayerException(String message) {
        super(message);
    }
}