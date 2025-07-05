package LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code;

import LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code.Product.Vehicle;
import LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<User> users=addUsers();
        List<Store> stores=addStores();

        VehicleRentalSystem vehicleRentalSystem = new VehicleRentalSystem(stores, users);

        System.out.println("Vehicle Rental System : " + vehicleRentalSystem);

        //0. User comes
        User user = users.get(0);

        //1. User search store based on location
        Location location=new Location(null,"Bangalore", "Karnataka", 3456);
        Store store=vehicleRentalSystem.searchStore(location);

        System.out.println("User searched store : " + store);

        //2. get All vehicles you are interested in (based upon filters)
        List<Vehicle> storeVehicles = store.getVehicles(VehicleType.CAR);

        System.out.println("Search vehicles : " + storeVehicles);

        //3. Reserve the particular vehicle
        Reservation reservation = store.createReservation(storeVehicles.get(0), user);

        System.out.println("Reservation status : " + reservation);

        //4. Generate Bill
        Bill bill = new Bill(reservation);

        System.out.println("Bill : " + bill);

        //5. Bill payment
        Payment payment = new Payment();
        payment.payBill(bill);

        //6. Trip completed
        store.completeReservation(reservation);

        System.out.println("Trip completed : " + reservation);
    }

    private static List<Store> addStores() {
        List<Store> stores=new ArrayList<>();

        List<Vehicle> vehicles=addVehicles();
        stores.add(new Store(1, vehicles, new Location("xyz", "Bangalore", "Karnataka", 3456)));

        return stores;
    }

    private static List<Vehicle> addVehicles() {
        List<Vehicle> vehicles=new ArrayList<>();
        vehicles.add(new Vehicle(1, 500, VehicleType.CAR));
        vehicles.add(new Vehicle(1, 500, VehicleType.CAR));
        return vehicles;
    }

    private static List<User> addUsers() {
        List<User> users=new ArrayList<>();
        users.add(new User(1));
        return users;
    }


}


