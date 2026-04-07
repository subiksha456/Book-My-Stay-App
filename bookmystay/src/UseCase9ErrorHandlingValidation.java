import java.util.*;

// Use Case 9: Error Handling & Validation
public class UseCase9ErrorHandlingValidation {

    // Custom exception for invalid booking
    static class InvalidBookingException extends Exception {
        InvalidBookingException(String message) {
            super(message);
        }
    }

    // Inventory with validation
    static class RoomInventory {
        Map<String, Integer> inventory = new HashMap<>();

        RoomInventory() {
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        // Book a room safely
        void bookRoom(String roomType) throws InvalidBookingException {
            if (!inventory.containsKey(roomType)) {
                throw new InvalidBookingException("Room type " + roomType + " does not exist.");
            }
            int available = inventory.get(roomType);
            if (available <= 0) {
                throw new InvalidBookingException("No available rooms for type " + roomType + ".");
            }
            inventory.put(roomType, available - 1);
            System.out.println("Room booked successfully: " + roomType);
        }

        // Display current inventory
        void displayInventory() {
            System.out.println("\nCurrent Room Inventory:");
            inventory.forEach((type, count) -> System.out.println(type + ": " + count));
        }
    }

    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        String[] testBookings = {"Single", "Double", "Suite", "Penthouse", "Double", "Double", "Double"};

        for (String roomType : testBookings) {
            try {
                inventory.bookRoom(roomType);
            } catch (InvalidBookingException e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        inventory.displayInventory();
    }
}