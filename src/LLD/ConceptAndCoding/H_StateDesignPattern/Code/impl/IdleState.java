package LLD.ConceptAndCoding.H_StateDesignPattern.Code.impl;

import LLD.ConceptAndCoding.H_StateDesignPattern.Code.Item;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VMState;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VendingMachine;

public class IdleState extends VMState {

    public IdleState() {
        System.out.println("Vending Machine is in Idle State");
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
        vendingMachine.vendingMachineState=new AcceptCoinState();
    }

    @Override
    public void updateInventory(VendingMachine vendingMachine, Item item, int codeNumber) throws Exception {
        super.updateInventory(vendingMachine, item, codeNumber);
    }
}
