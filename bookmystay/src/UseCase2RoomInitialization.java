// Entry point for Use Case 2
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App v2.1");

        // Initialize room availability (static variables)
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Create room objects
        Room singleRoom = new SingleRoom("Single", 1, 15, 50);
        Room doubleRoom = new DoubleRoom("Double", 2, 25, 80);
        Room suiteRoom = new SuiteRoom("Suite", 3, 50, 150);

        // Display room details and availability
        displayRoomInfo(singleRoom, singleRoomAvailable);
        displayRoomInfo(doubleRoom, doubleRoomAvailable);
        displayRoomInfo(suiteRoom, suiteRoomAvailable);
    }

    private static void displayRoomInfo(Room room, int available) {
        System.out.println("----------------------------");
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Number of Beds: " + room.getBeds());
        System.out.println("Size (sqm): " + room.getSize());
        System.out.println("Price per Night: $" + room.getPrice());
        System.out.println("Available Rooms: " + available);
    }
}

// Abstract Room class
abstract class Room {
    private String roomType;
    private int beds;
    private int size;
    private int price;

    public Room(String roomType, int beds, int size, int price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }
}

// Concrete Room Types
class SingleRoom extends Room {
    public SingleRoom(String roomType, int beds, int size, int price) {
        super(roomType, beds, size, price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom(String roomType, int beds, int size, int price) {
        super(roomType, beds, size, price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(String roomType, int beds, int size, int price) {
        super(roomType, beds, size, price);
    }
}