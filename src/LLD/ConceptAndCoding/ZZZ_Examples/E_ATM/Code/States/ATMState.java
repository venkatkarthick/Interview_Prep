package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Card;

public abstract class ATMState{
    public void insertCard(ATM atm, Card card) throws Exception {
        throw new Exception("OOPS!! Something went wrong");
    }
    public void authenticatPin(ATM atm, Card card, int pin) throws Exception {
        throw new Exception("OOPS!! Something went wrong");
    }
    public void selectOperation(ATM atm, Card card) throws Exception {
        throw new Exception("OOPS!! Something went wrong");
    }
    public void cashWithdrawal(ATM atm, Card card, int withdrawAmount) throws Exception {
        throw new Exception("OOPS!! Something went wrong");
    }
    public void displayBalance(ATM atm, Card card) throws Exception {
        throw new Exception("OOPS!! Something went wrong");
    }
    public void returnCard() throws Exception{
        throw new Exception("OOPS!! Something went wrong");
    }
    public void exit(ATM atm) throws Exception{
        throw new Exception("OOPS!! Something went wrong");
    }

}
