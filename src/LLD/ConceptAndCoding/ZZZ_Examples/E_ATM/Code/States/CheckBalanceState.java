package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Card;

public class CheckBalanceState extends ATMState {

    public CheckBalanceState(ATM atm, Card card) throws Exception {
        System.out.println("Entered into check balance state");
    }

    @Override
    public void displayBalance(ATM atm, Card card) throws Exception {
        System.out.println("User balance is : " + card.getBankBalance());
        exit(atm);
    }

    @Override
    public void returnCard() throws Exception {
        System.out.println("Collect your card");
    }

    @Override
    public void exit(ATM atm) throws Exception {
        returnCard();
        System.out.println("Exitted transaction");
        atm.setAtmState(new IdleState());
    }

    @Override
    public String toString() {
        return "Check Balance State";
    }
}
