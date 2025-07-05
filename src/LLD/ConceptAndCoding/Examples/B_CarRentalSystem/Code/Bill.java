package LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code;


public class Bill {
    Reservation reservation;
    double totalBillAmount;
    boolean isBillPaid;

    public Bill(Reservation reservation) {
        this.reservation=reservation;
        this.totalBillAmount=computeBillAmount();
        isBillPaid=false;
    }

    private double computeBillAmount() {
        return 100.00;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "reservation=" + reservation +
                ", totalBillAmount=" + totalBillAmount +
                ", isBillPaid=" + isBillPaid +
                '}';
    }
}
