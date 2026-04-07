import java.util.HashMap;

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App v3.1");
        System.out.println("-------------------------------");

        RoomInventory inventory = new RoomInventory();

        // Initialize inventory with room types and availability counts
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        // Display current inventory state
        inventory.displayInventory();

        // Example of updating availability
        inventory.updateAvailability("Double", 4);
        System.out.println("\nAfter updating Double room availability:");
        inventory.displayInventory();
    }
}

class RoomInventory {
    private HashMap<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if(roomAvailability.containsKey(roomType)) {
            roomAvailability.put(roomType, newCount);
        } else {
            System.out.println("Room type " + roomType + " does not exist in inventory.");
        }
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : roomAvailability.keySet()) {
            System.out.printf("Room Type: %s, Available: %d%n", roomType, roomAvailability.get(roomType));
        }
    }
}
