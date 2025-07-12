package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Card;

public class IdleState extends ATMState {

    public IdleState() {
        System.out.println("Entered into Idle State");
    }

    @Override
    public void insertCard(ATM atm, Card card) throws Exception {
        System.out.println("Card is inserted");
        atm.setAtmState(new HasCardState());
    }
}
