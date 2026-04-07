import java.util.LinkedList;
import java.util.Queue;

public class UseCase5BookingRequestQueue {

    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        @Override
        public String toString() {
            return guestName + " requested " + roomType;
        }
    }

    public static void main(String[] args) {
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Suite"));

        System.out.println("Booking requests in arrival order:");
        while (!bookingQueue.isEmpty()) {
            System.out.println(bookingQueue.poll());
        }
    }
}