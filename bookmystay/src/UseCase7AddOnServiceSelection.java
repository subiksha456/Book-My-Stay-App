import java.util.*;

public class UseCase7AddOnServiceSelection {

    static class Reservation {
        String reservationId;
        String guestName;
        String roomType;

        Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    static class Service {
        String name;
        double cost;

        Service(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return name + " ($" + cost + ")";
        }
    }

    public static void main(String[] args) {
        // Sample reservations
        Reservation res1 = new Reservation("R001", "Alice", "Single");
        Reservation res2 = new Reservation("R002", "Bob", "Double");

        // Available add-on services
        Service breakfast = new Service("Breakfast", 10);
        Service airportPickup = new Service("Airport Pickup", 25);
        Service spa = new Service("Spa Session", 50);

        // Map reservations to their selected services
        Map<String, List<Service>> reservationServices = new HashMap<>();

        // Guest selects services
        reservationServices.put(res1.reservationId, Arrays.asList(breakfast, spa));
        reservationServices.put(res2.reservationId, Arrays.asList(airportPickup));

        // Display reservations with selected add-ons and total cost
        for (Reservation res : Arrays.asList(res1, res2)) {
            List<Service> services = reservationServices.getOrDefault(res.reservationId, new ArrayList<>());
            double totalCost = services.stream().mapToDouble(s -> s.cost).sum();
            System.out.println(res.guestName + " (" + res.roomType + ") selected services: " + services);
            System.out.println("Total add-on cost: $" + totalCost + "\n");
        }
    }
}