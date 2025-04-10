public class Alarm extends Thread {
    private int timer;
    private String name;
    private int id;
    private static int nextId = 1;

    // Constructor
    public Alarm(String name, int seconds) {
        this.id = nextId++;
        this.timer = seconds * 1000; // Convert seconds to milliseconds
        if (name.isEmpty()) {
            this.name = "Alarm " + id;
        } else {
            this.name = name;
        }
    }

    // run() method to start the alarm
    @Override
    public void run() {
        try {
            while (timer > 0) {
                Thread.sleep(1000); // Sleep for 1 second
                timer -= 1000;

                // Check if 10 seconds remaining
                if (timer == 10000) {
                    System.out.println(name + " will go off in 10 seconds.");
                }
            }
            System.out.println(name + " has gone off!");
        } catch (InterruptedException e) {
            System.out.println(name + " has been interrupted at " + (timer / 1000) + " seconds.");
        }
    }

    // toString() method
    @Override
    public String toString() {
        return name + " is currently at " + (timer / 1000) + " seconds";
    }
}