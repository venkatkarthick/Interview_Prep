package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Card;

public class HasCardState extends ATMState {

    public HasCardState() {
        System.out.println("Entered into Has Card State");
    }

    @Override
    public void authenticatPin(ATM atm, Card card, int pin) throws Exception {
        if(card.isCorrectPinEntered(pin)) {
            atm.setAtmState(new SelectOperationState());
        } else {
            System.out.println("Invalid Pin");
            exit(atm);
        }
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
}
