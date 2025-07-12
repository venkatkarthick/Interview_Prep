package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code;

public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public int withdrawAmount(int amount) {
        balance=balance-amount;
        return balance;
    }

    //getters and setters
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
