package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;

public class TwoThouandWithdrawProcessor extends CashWithdrawalProcessor{

    public TwoThouandWithdrawProcessor(CashWithdrawalProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/2000;
        int balance = remainingAmount%2000;
        int available=atm.getNoOfTwoThousandNotes();
        int providedNotes=required;

        if(required<=available ) {
            atm.deductTwoThousandNotes(required);
        } else {
            providedNotes=available;
            atm.deductTwoThousandNotes(available);
            balance+=(2000*(required-available));
        }
        System.out.println("2000 Notes provided : " + providedNotes);
        if(balance!=0) {
            super.withdraw(atm, balance);
        }
    }
}
