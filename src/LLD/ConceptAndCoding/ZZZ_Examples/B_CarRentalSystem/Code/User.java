package LLD.ConceptAndCoding.ZZZ_Examples.B_CarRentalSystem.Code;

public class User {
    int userID;
    String userName;
    String drivingLicense; //S3 storage or something (I can have as MutlipartFile datatype in spring)

    public User(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                '}';
    }
}
