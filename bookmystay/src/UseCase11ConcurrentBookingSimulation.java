import java.util.*;
import java.util.concurrent.*;

// Simulation of concurrent booking
public class UseCase11ConcurrentBookingSimulation {

    // Reservation model
    static class Reservation {
        String reservationId;
        String roomType;

        Reservation(String id, String type) {
            this.reservationId = id;
            this.roomType = type;
        }
    }

    // Thread-safe inventory
    static class RoomInventory {
        private final Map<String, Integer> inventory = new HashMap<>();

        RoomInventory() {
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        // Synchronized booking
        public synchronized boolean bookRoom(Reservation res) {
            int available = inventory.getOrDefault(res.roomType, 0);
            if (available <= 0) {
                System.out.println(Thread.currentThread().getName() +
                        " failed: No " + res.roomType + " rooms left.");
                return false;
            }
            inventory.put(res.roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " booked " + res.roomType + " (Reservation ID: " + res.reservationId + ")");
            return true;
        }

        public synchronized void displayInventory() {
            System.out.println("\nCurrent Inventory:");
            inventory.forEach((type, count) -> System.out.println(type + ": " + count));
        }
    }

    // Runnable booking task
    static class BookingTask implements Runnable {
        private final RoomInventory inventory;
        private final Reservation reservation;

        BookingTask(RoomInventory inventory, Reservation reservation) {
            this.inventory = inventory;
            this.reservation = reservation;
        }

        @Override
        public void run() {
            inventory.bookRoom(reservation);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RoomInventory inventory = new RoomInventory();

        // Sample reservations
        List<Reservation> reservations = List.of(
                new Reservation("R201", "Single"),
                new Reservation("R202", "Single"),
                new Reservation("R203", "Double"),
                new Reservation("R204", "Suite"),
                new Reservation("R205", "Single"),
                new Reservation("R206", "Double"),
                new Reservation("R207", "Suite")
        );

        // Thread pool for concurrent execution
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (Reservation r : reservations) {
            executor.submit(new BookingTask(inventory, r));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        inventory.displayInventory();
    }
}