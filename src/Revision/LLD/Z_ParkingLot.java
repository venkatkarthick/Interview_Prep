package Revision.LLD;

import LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Z_ParkingLot {

    //Parking spot consists of entry gate, exit gate and a building with different levels. Each level has 2 wheeler or 4 wheeler spots.
    //At entry gate, Based on vehicle type, parking spot is looked for.
    //For handling concurrency, for 1 vehicle we should not block whole parking lot itself.
    //After booking, ticket is generated with vehicle no, parking level no, parking spot no, Entry Time
    //At Exit gate, Cost computation happens. Different strategies can be applied for cost computation. - Fixed price, hourly based computation

    //Object has property or behaviour

    //Major objects - Vehicle, Parking spot, Parking level, Entry gate, Exit gate, Ticket, Payment

    static class Vehicle{
        public String VehicleNumber;
        public VehicleType vehicleType;
        public Vehicle(String vehicleNumber, VehicleType vehicleType) {
            VehicleNumber = vehicleNumber;
            this.vehicleType = vehicleType;
        }
    }
    enum VehicleType {
        TWO_WHEELER, FOUR_WHEELER
    }

    //POJO
    static class ParkingSpot{
        public int spotId;
        public boolean isFree;
        public ParkingSpot(int spotId) {this.spotId = spotId; isFree=true;}
        public void occupySpot() {isFree=false;}
        public void releaseSpot() {isFree=true;}
        public Boolean isSpotFree() {return isFree;}
    }
    //Intelligent class
    static abstract class ParkingSpotManager{
        public List<ParkingSpot> spots;
        public ParkingSpotLookupStartegy strategy;
        ReentrantLock lock=new ReentrantLock(true);
        public ParkingSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStartegy strategy) {
            this.spots = spots;
            this.strategy = strategy;
        }
        public ParkingSpot park() {
            lock.lock();
            try{
                ParkingSpot spot=strategy.selectSpot(spots);
                if(spot==null) return null;
                spot.occupySpot();
                return spot;
            } finally {
                lock.unlock();
            }
        }
        public Boolean unpark(int spotId) {
            for(ParkingSpot spot : spots){
                if(spotId==spot.spotId) {
                    spot.releaseSpot();
                    return true;
                }
            }
            return false;
        }
        public boolean hasFreeSpot() {
            return spots.stream().anyMatch(ParkingSpot::isSpotFree);
        }
    }
    static class TwoWheelerSpotManager extends ParkingSpotManager{
        public TwoWheelerSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStartegy strategy) {
            super(spots, strategy);
        }
    }
    static class FourWheelerSpotManager extends ParkingSpotManager{
        public FourWheelerSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStartegy strategy) {
            super(spots, strategy);
        }
    }
    interface ParkingSpotLookupStartegy{
        ParkingSpot selectSpot(List<ParkingSpot> spots);
    }
    static class OrderedLookupStrategy implements ParkingSpotLookupStartegy{
        @Override
        public ParkingSpot selectSpot(List<ParkingSpot> spots) {
            for(ParkingSpot spot: spots) {
                if(spot.isFree) return spot;
            }
            return null;
        }
    }

    static class ParkingLevel{
        int levelNumber;
        Map<VehicleType, ParkingSpotManager> managers;
        public ParkingLevel(int levelNumber, Map<VehicleType, ParkingSpotManager> managers) {
            this.levelNumber = levelNumber;
            this.managers = managers;
        }
        public boolean hasAvailability(VehicleType vehicleType) {
            ParkingSpotManager parkingSpotManager=managers.get(vehicleType);
            return parkingSpotManager!=null && parkingSpotManager.hasFreeSpot();
        }
        public ParkingSpot park(VehicleType vehicleType) throws Exception {
            ParkingSpotManager parkingSpotManager=managers.get(vehicleType);
            if(parkingSpotManager==null) throw new Exception("VehicleType not supported at the level : " + levelNumber);
            return parkingSpotManager.park();
        }
        public boolean unpark(VehicleType vehicleType, int spotId) {
            ParkingSpotManager parkingSpotManager=managers.get(vehicleType);
            return parkingSpotManager.unpark(spotId);
        }
    }

    static class ParkingBuilding{
        List<ParkingLevel> levels;
        public ParkingBuilding(List<ParkingLevel> levels) {
            this.levels = levels;
        }
        public Ticket allocate(VehicleType vehicle) throws Exception {
            Ticket ticket=null;
            for(ParkingLevel parkingLevel: levels) {
                if(parkingLevel.hasAvailability(vehicle)) {
                    ParkingSpot parkingSpot=parkingLevel.park(vehicle);
                    if(parkingSpot!=null) {
                        ticket = new Ticket(vehicle, parkingLevel, parkingSpot);
                        System.out.println("Vehicle Parking Allocated!!");
                        System.out.println("Ticket generated : "+ ticket);
                        return ticket;
                    }
                }
            }
            System.out.println("Slots are full!! Ticket not allocated.");
            return null;
        }
        public boolean release(Ticket ticket) {
            return ticket.level.unpark(ticket.vehicleType, ticket.spot.spotId);
        }
    }
    static class Ticket{
        VehicleType vehicleType;
        ParkingLevel level;
        ParkingSpot spot;
        public Ticket(VehicleType vehicle, ParkingLevel levelNumber, ParkingSpot spotId) {
            this.vehicleType=vehicle;
            this.level=levelNumber;
            this.spot=spotId;
        }
        @Override
        public String toString() {return "{vehicleType=" + vehicleType + ", levelNumber=" + level.levelNumber + ", spotId=" + spot.spotId + '}';}
    }
    static class EntranceGate {
        public Ticket enter(ParkingBuilding parkingBuilding, Vehicle vehicle) throws Exception {
            return parkingBuilding.allocate(vehicle.vehicleType);
        }
    }
    static class ExitGate{
        public CostComputation costComputation;
        public ExitGate(CostComputation costComputation) {this.costComputation = costComputation;}
        public boolean completeExit(ParkingBuilding parkingBuilding, Ticket ticket, Payment payment) throws Exception {
            double amount = calculatePrice(ticket);
            System.out.println("------\nAmount to be paid : " + amount);
            boolean success = payment.pay(amount);
            if(!success) throw new Exception("Payment Failed. Exit denied.");
            parkingBuilding.release(ticket);
            System.out.println("Exit successful. Gate opened. \n------");
            return true;
        }
        private double calculatePrice(Ticket ticket) {
            return costComputation.compute(ticket);
        }

    }
    static class ParkingLot{
        ParkingBuilding parkingBuilding;
        EntranceGate entranceGate;
        ExitGate exitGate;
        public ParkingLot(ParkingBuilding parkingBuilding, EntranceGate entranceGate, ExitGate exitGate) {
            this.parkingBuilding = parkingBuilding;
            this.entranceGate = entranceGate;
            this.exitGate = exitGate;
        }
        public Ticket vehicleArrives(Vehicle vehicle) throws Exception {
            return entranceGate.enter(parkingBuilding, vehicle);
        }
        public boolean vehicleLeaves(Ticket ticket, Payment payment) throws Exception {
            return exitGate.completeExit(parkingBuilding, ticket, payment);
        }
    }
    static class CostComputation{
        PricingStrategy pricingStrategy;
        public CostComputation(PricingStrategy pricingStrategy) {this.pricingStrategy = pricingStrategy;}
        public double compute(Ticket ticket) {
            return pricingStrategy.calculate(ticket);
        }
    }
    interface PricingStrategy {
        public double calculate(Ticket ticket);
    }
    static class FixedPriceStrategy implements PricingStrategy{
        @Override public double calculate(Ticket ticket) {return 100;}
    }
    interface Payment{
        public boolean pay(double amount);
    }
    static class UPIPayment implements Payment{
        public boolean pay(double amount) {
            System.out.println("Payment completed through UPI!");
            return true;
        }
    }

    public static void main(String[] args) {
        ParkingLot parkingLot=buildParkingLot();
        //Car enters
        try {
            Ticket ticket1=parkingLot.entranceGate.enter(parkingLot.parkingBuilding, new Vehicle("123", VehicleType.TWO_WHEELER));
            Ticket ticket12=parkingLot.entranceGate.enter(parkingLot.parkingBuilding, new Vehicle("1123", VehicleType.TWO_WHEELER));
            Ticket ticket2=parkingLot.entranceGate.enter(parkingLot.parkingBuilding, new Vehicle("456", VehicleType.FOUR_WHEELER));
            Ticket ticket3=parkingLot.entranceGate.enter(parkingLot.parkingBuilding, new Vehicle("789", VehicleType.FOUR_WHEELER));

            //Car exits
            parkingLot.exitGate.completeExit(parkingLot.parkingBuilding, ticket12, new UPIPayment());
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    private static ParkingLot buildParkingLot() {
        List<ParkingLevel> parkingLevels=new ArrayList<>();
        //Level 1 -> 2w, level2 -> 2w, 4w //Each has 4spots
        for (int i = 0; i < 2; i++) {
            Map<VehicleType, ParkingSpotManager> mp=new HashMap<>();
            if(i==1) {
                mp.put(VehicleType.FOUR_WHEELER, new FourWheelerSpotManager(generateParkingSpots(1), new OrderedLookupStrategy()));
            }
            mp.put(VehicleType.TWO_WHEELER, new TwoWheelerSpotManager(generateParkingSpots(3), new OrderedLookupStrategy()));
            parkingLevels.add(new ParkingLevel(i, mp));
        }
        return new ParkingLot(new ParkingBuilding(parkingLevels), new EntranceGate(), new ExitGate(new CostComputation(new FixedPriceStrategy())));
    }
    private static List<ParkingSpot> generateParkingSpots(int n) {
        List<ParkingSpot> parkingSpots=new ArrayList<>();
        for (int i=0; i<n; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
        return parkingSpots;
    }


}
