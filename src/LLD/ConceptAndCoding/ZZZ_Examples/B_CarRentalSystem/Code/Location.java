package LLD.ConceptAndCoding.ZZZ_Examples.B_CarRentalSystem.Code;

public class Location {
    String address;
    String city;
    String state;
    int pincode;

    public Location(String address, String city, String state, int pincode) {
        this.address=address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
