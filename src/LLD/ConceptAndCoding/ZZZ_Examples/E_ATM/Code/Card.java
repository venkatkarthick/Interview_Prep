package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code;

import java.util.Date;

public class Card {
    public int cvv;
    public Date expiryDate;
    private String holderName;
    private int pinNumber = 122113;
    private BankAccount bankAccount;

    public Card(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean isCorrectPinEntered(int pin) {
        if(pin==pinNumber) return true;
        return false;
    }

    public int getBankBalance() {
        return bankAccount.getBalance();
    }

    public int deductBalance(int amount) {
        bankAccount.withdrawAmount(amount);
    }

    //getters and setters
    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
