import java.util.*;

public class UseCase8BookingHistoryReport {

    static class Reservation {
        String reservationId;
        String guestName;
        String roomType;
        double totalCost;

        Reservation(String reservationId, String guestName, String roomType, double totalCost) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
            this.totalCost = totalCost;
        }

        @Override
        public String toString() {
            return reservationId + ": " + guestName + " booked " + roomType + " ($" + totalCost + ")";
        }
    }

    static class BookingHistory {
        List<Reservation> history = new ArrayList<>();

        void addReservation(Reservation res) {
            history.add(res);
        }

        List<Reservation> getAllReservations() {
            return new ArrayList<>(history); // return a copy to avoid external modification
        }
    }

    public static void main(String[] args) {
        BookingHistory bookingHistory = new BookingHistory();

        // Sample confirmed bookings
        Reservation res1 = new Reservation("R001", "Alice", "Single", 100.0);
        Reservation res2 = new Reservation("R002", "Bob", "Double", 150.0);
        Reservation res3 = new Reservation("R003", "Charlie", "Suite", 250.0);

        // Add to booking history
        bookingHistory.addReservation(res1);
        bookingHistory.addReservation(res2);
        bookingHistory.addReservation(res3);

        // Admin views booking history
        System.out.println("Booking History:");
        for (Reservation r : bookingHistory.getAllReservations()) {
            System.out.println(r);
        }

        // Optional: Summary report
        double totalRevenue = bookingHistory.getAllReservations()
                .stream()
                .mapToDouble(r -> r.totalCost)
                .sum();
        System.out.println("\nTotal Revenue: $" + totalRevenue);
    }
}