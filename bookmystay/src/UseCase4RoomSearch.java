import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        // Initialize with all room types you want to support
        roomAvailability.put("Single", 0);
        roomAvailability.put("Double", 0);
        roomAvailability.put("Suite", 0);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (roomAvailability.containsKey(roomType)) {
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