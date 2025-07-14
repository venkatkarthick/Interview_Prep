package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;

public class FiveHundredWithdrawProcessor extends CashWithdrawalProcessor{
    public FiveHundredWithdrawProcessor(CashWithdrawalProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/500;
        int balance = remainingAmount%500;
        int available=atm.getNoOfFiveHundredNotes();
        int providedNotes=required;

        if(required<=available ) {
            atm.deductFiveHundredNotes(required);
        } else {
            providedNotes=available;
            atm.deductFiveHundredNotes(available);
            balance+=(500*(required-available));
        }
        System.out.println("500 Notes provided : " + providedNotes);
        if(balance!=0) {
            super.withdraw(atm, balance);
        }
    }
}
