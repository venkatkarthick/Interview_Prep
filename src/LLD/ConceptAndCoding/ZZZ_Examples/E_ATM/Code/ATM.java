package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States.ATMState;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States.IdleState;

public class ATM {
    private ATMState atmState;
    private int atmBalance;
    private int noOfTwoThousandNotes;
    private int noOfFiveHundredNotes;
    private int noOfOneHundredNotes;

    public ATM(int atmBalance, int noOfTwoThousandNotes, int noOfFiveHundredNotes, int noOfOneHundredNotes) {
        this.atmState = new IdleState();
        this.atmBalance = atmBalance;
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
        this.noOfOneHundredNotes = noOfOneHundredNotes;
    }

    public void printStatus() {
        System.out.println(this);
    }

    //Getters and setters
    public ATMState getAtmState() {
        return atmState;
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    public int getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(int atmBalance) {
        this.atmBalance = atmBalance;
    }

    public int getNoOfTwoThousandNotes() {
        return noOfTwoThousandNotes;
    }

    public void setNoOfTwoThousandNotes(int noOfTwoThousandNotes) {
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
    }

    public int getNoOfFiveHundredNotes() {
        return noOfFiveHundredNotes;
    }

    public void setNoOfFiveHundredNotes(int noOfFiveHundredNotes) {
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
    }

    public int getNoOfOneHundredNotes() {
        return noOfOneHundredNotes;
    }

    public void setNoOfOneHundredNotes(int noOfOneHundredNotes) {
        this.noOfOneHundredNotes = noOfOneHundredNotes;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "atmState=" + atmState +
                ", atmBalance=" + atmBalance +
                ", noOfTwoThousandNotes=" + noOfTwoThousandNotes +
                ", noOfFiveHundredNotes=" + noOfFiveHundredNotes +
                ", noOfOneHundredNotes=" + noOfOneHundredNotes +
                '}';
    }
}
