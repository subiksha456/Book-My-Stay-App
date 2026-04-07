import java.util.*;

public class UseCase10BookingCancellation {

    // Simple reservation model
    static class Reservation {
        String reservationId;
        String roomType;

        Reservation(String id, String type) {
            this.reservationId = id;
            this.roomType = type;
        }
    }

    // Inventory with rollback support
    static class RoomInventory {
        Map<String, Integer> inventory = new HashMap<>();
        Stack<String> rollbackStack = new Stack<>();

        RoomInventory() {
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        // Book a room
        boolean bookRoom(Reservation res) {
            int available = inventory.getOrDefault(res.roomType, 0);
            if (available <= 0) {
                System.out.println("Booking failed: No rooms available for " + res.roomType);
                return false;
            }
            inventory.put(res.roomType, available - 1);
            rollbackStack.push(res.reservationId);
            System.out.println("Room booked: " + res.roomType + " (Reservation ID: " + res.reservationId + ")");
            return true;
        }

        // Cancel a booking
        boolean cancelBooking(Reservation res) {
            if (!rollbackStack.contains(res.reservationId)) {
                System.out.println("Cancellation failed: Reservation ID not found.");
                return false;
            }
            int current = inventory.get(res.roomType);
            inventory.put(res.roomType, current + 1);
            rollbackStack.remove(res.reservationId);
            System.out.println("Booking cancelled: " + res.roomType + " (Reservation ID: " + res.reservationId + ")");
            return true;
        }

        // Display inventory
        void displayInventory() {
            System.out.println("\nCurrent Room Inventory:");
            inventory.forEach((type, count) -> System.out.println(type + ": " + count));
        }
    }

    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        Reservation r1 = new Reservation("R101", "Single");
        Reservation r2 = new Reservation("R102", "Double");
        Reservation r3 = new Reservation("R103", "Suite");
        Reservation r4 = new Reservation("R104", "Single");

        // Book rooms
        inventory.bookRoom(r1);
        inventory.bookRoom(r2);
        inventory.bookRoom(r3);
        inventory.bookRoom(r4);

        // Cancel a booking
        inventory.cancelBooking(r2);
        inventory.cancelBooking(new Reservation("R999", "Single")); // Invalid cancellation

        inventory.displayInventory();
    }
}