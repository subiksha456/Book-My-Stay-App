import java.io.*;
import java.util.*;

// Main class for UC12
public class UseCase12DataPersistenceRecovery {

    // Serializable Room Inventory
    static class RoomInventory implements Serializable {
        private static final long serialVersionUID = 1L;
        Map<String, Integer> inventory = new HashMap<>();

        RoomInventory() {
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        public boolean bookRoom(String roomType) {
            int available = inventory.getOrDefault(roomType, 0);
            if (available <= 0) return false;
            inventory.put(roomType, available - 1);
            return true;
        }

        public void cancelRoom(String roomType) {
            int available = inventory.getOrDefault(roomType, 0);
            inventory.put(roomType, available + 1);
        }

        public void displayInventory() {
            System.out.println("Current Inventory:");
            inventory.forEach((type, count) -> System.out.println(type + ": " + count));
        }
    }

    // Serializable Reservation
    static class Reservation implements Serializable {
        private static final long serialVersionUID = 1L;
        String reservationId;
        String roomType;

        Reservation(String id, String type) {
            this.reservationId = id;
            this.roomType = type;
        }
    }

    // Booking History
    static class BookingHistory implements Serializable {
        private static final long serialVersionUID = 1L;
        List<Reservation> reservations = new ArrayList<>();

        public void addReservation(Reservation r) {
            reservations.add(r);
        }

        public void displayHistory() {
            System.out.println("Booking History:");
            for (Reservation r : reservations) {
                System.out.println(r.reservationId + " -> " + r.roomType);
            }
        }
    }

    // File paths
    static final String INVENTORY_FILE = "inventory.ser";
    static final String HISTORY_FILE = "history.ser";

    // Save objects to file
    public static void saveData(RoomInventory inventory, BookingHistory history) {
        try (ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(INVENTORY_FILE));
             ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE))) {

            oos1.writeObject(inventory);
            oos2.writeObject(history);

            System.out.println("Data persisted successfully!");

        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Load objects from file
    public static Object loadData(String filePath, Class<?> clazz) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(filePath + " not found. Creating new instance.");
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create new instance: " + e.getMessage());
            }
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (Exception e) {
            System.err.println("Error loading data from " + filePath + ": " + e.getMessage());
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to create new instance: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Load previous state
        RoomInventory inventory = (RoomInventory) loadData(INVENTORY_FILE, RoomInventory.class);
        BookingHistory history = (BookingHistory) loadData(HISTORY_FILE, BookingHistory.class);

        // Example bookings
        Reservation r1 = new Reservation("R301", "Single");
        if (inventory.bookRoom(r1.roomType)) history.addReservation(r1);

        Reservation r2 = new Reservation("R302", "Double");
        if (inventory.bookRoom(r2.roomType)) history.addReservation(r2);

        // Display current state
        inventory.displayInventory();
        history.displayHistory();

        // Persist state before shutdown
        saveData(inventory, history);
    }
}