package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Card;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors.CashWithdrawalProcessor;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors.FiveHundredWithdrawProcessor;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors.OneHundredWithdrawProcessor;
import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors.TwoThouandWithdrawProcessor;

import java.util.Scanner;

public class CashWithdrawalState extends ATMState {

    Integer amountToWithdraw=0;

    public CashWithdrawalState() {
        System.out.println("Entered into Cash Withdrawal State");
        getAmount();
    }

    private void getAmount() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter amount to withdraw : ");
        amountToWithdraw=sc.nextInt();
    }

    @Override
    public void cashWithdrawal(ATM atm, Card card) throws Exception {
        if(atm.getAtmBalance()<amountToWithdraw) {
            System.out.println("Insufficient fund in the ATM Machine");
            exit(atm);
        } else if(card.getBankBalance()<amountToWithdraw) {
            System.out.println("Insufficient fund in your bank account");
        } else {
            card.deductBalance(amountToWithdraw);

            // Using chain of responsibility logic
            CashWithdrawalProcessor cashWithdrawalProcessor=new TwoThouandWithdrawProcessor(new FiveHundredWithdrawProcessor(new OneHundredWithdrawProcessor(null)));
            cashWithdrawalProcessor.withdraw(atm, amountToWithdraw);
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

    @Override
    public String toString() {
        return "Cash Withdrawal State";
    }
}
