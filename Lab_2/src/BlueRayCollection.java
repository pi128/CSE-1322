public class BlueRayCollection {
    private BlueRayDisk head;

    // Constructor
    public BlueRayCollection() {
        this.head = null;
    }

    // Method to add a new disk to the collection
    public void addDisk(String title, String director, int yearOfRelease, double cost) {
        BlueRayDisk newDisk = new BlueRayDisk(title, director, yearOfRelease, cost);
        if (head == null) {
            head = newDisk;
        } else {
            BlueRayDisk current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newDisk;
        }
        System.out.println("BlueRay Disk added to collection.");
    }

    // Method to show all disks in the collection
    public String showAll() {
        if (head == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        BlueRayDisk current = head;
        while (current != null) {
            result.append(current.toString()).append("\n");
            current = current.next;
        }
        return result.toString();
    }
}