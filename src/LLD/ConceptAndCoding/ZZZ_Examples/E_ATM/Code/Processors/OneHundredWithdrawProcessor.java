package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;

public class OneHundredWithdrawProcessor extends CashWithdrawalProcessor{
    public OneHundredWithdrawProcessor(CashWithdrawalProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/100;
        int balance = remainingAmount%100;
        int available=atm.getNoOfOneHundredNotes();
        int providedNotes=required;

        if(required<=available ) {
            atm.deductOneHundredNotes(required);
        } else {
            providedNotes=available;
            atm.deductOneHundredNotes(available);
            balance+=(100*(required-available));
        }

        System.out.println("100 Notes provided : " + providedNotes);
        if(balance!=0) {
            super.withdraw(atm, balance);
        }
    }
}
