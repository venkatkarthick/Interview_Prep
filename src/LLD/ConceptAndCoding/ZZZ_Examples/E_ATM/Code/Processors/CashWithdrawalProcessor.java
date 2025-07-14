package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.Processors;

import LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.ATM;

public abstract class CashWithdrawalProcessor {

    CashWithdrawalProcessor nextProcessor;

    public CashWithdrawalProcessor(CashWithdrawalProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public void withdraw(ATM atm, int remainingAmount) {
        if(nextProcessor!=null) {
            nextProcessor.withdraw(atm, remainingAmount);
        }
    }

}
