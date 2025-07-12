package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States.TransactionType;

public class ATMRoom {
    ATM atm;
    User user;

    public static void main(String[] args) {
        ATMRoom atmRoom=new ATMRoom();
        atmRoom.initialize();

        try {
            atmRoom.atm.printStatus();
            atmRoom.atm.getAtmState().insertCard(atmRoom.atm, atmRoom.user.card);
            atmRoom.atm.getAtmState().authenticatPin(atmRoom.atm, atmRoom.user.card, 122113);
            atmRoom.atm.getAtmState().selectOperation(atmRoom.atm, atmRoom.user.card);
            //atmRoom.atm.getAtmState().selectOperation(atmRoom.atm, atmRoom.user.card, TransactionType.CHECK_BALANCE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void initialize() {

        //create ATM
        atm=new ATM(3500, 1, 2, 5);

        //create User
        user=new User(new Card(new BankAccount(2000)));
    }
}
