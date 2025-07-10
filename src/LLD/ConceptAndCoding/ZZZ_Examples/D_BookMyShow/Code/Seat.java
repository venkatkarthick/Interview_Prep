package LLD.ConceptAndCoding.ZZZ_Examples.D_BookMyShow.Code;

public class Seat {
    int id;
    int row;
    SeatCategory seatCategory;

    public Seat(int id, int row, SeatCategory seatCategory) {
        this.id = id;
        this.row = row;
        this.seatCategory = seatCategory;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", seatCategory=" + seatCategory +
                '}';
    }
}
