package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Card;

import java.util.Scanner;

public class SelectOperationState extends ATMState {
    TransactionType transactionType;
    public SelectOperationState() {
        System.out.println("Entered into Select Operation State");
        showOperations();
    }

    private void showOperations() {
        System.out.println("Select the number for the following operation\n     1. Check Balance        2. Withdraw Amount\n");
        Scanner scanner=new Scanner(System.in);
        Integer val=scanner.nextInt();
        transactionType=TransactionType.getTranscation(val);
    }

    @Override
    public void selectOperation(ATM atm, Card card) throws Exception {
        switch (transactionType) {
            case CHECK_BALANCE -> {
                atm.setAtmState(new CheckBalanceState());
                break;
            }
            case WITHDRAW_AMOUNT -> {
                atm.setAtmState(new CashWithdrawalState());
                break;
            }
            default -> {
                System.out.println("Invalid option");
                exit(atm);
            }
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
