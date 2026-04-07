import java.util.*;

public class UseCase6RoomAllocationService {

    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        @Override
        public String toString() {
            return guestName + " booked " + roomType;
        }
    }

    public static void main(String[] args) {
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Single"));

        // Inventory setup
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);

        // Track allocated rooms
        Map<String, Set<String>> allocatedRooms = new HashMap<>();

        while (!bookingQueue.isEmpty()) {
            Reservation req = bookingQueue.poll();
            int available = inventory.getOrDefault(req.roomType, 0);

            if (available > 0) {
                // Generate unique room ID
                String roomId = req.roomType + "_" + UUID.randomUUID().toString().substring(0, 5);

                // Store allocated room ID
                allocatedRooms.computeIfAbsent(req.roomType, k -> new HashSet<>()).add(roomId);

                // Update inventory
                inventory.put(req.roomType, available - 1);

                System.out.println(req + " -> Room ID: " + roomId + " confirmed.");
            } else {
                System.out.println("No rooms available for " + req.roomType + " for " + req.guestName);
            }
        }

        System.out.println("\nRemaining Inventory: " + inventory);
    }
}
