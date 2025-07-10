package LLD.ConceptAndCoding.H_StateDesignPattern.Code.impl;

import LLD.ConceptAndCoding.H_StateDesignPattern.Code.Item;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.ItemShelf;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VMState;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VendingMachine;

public class DispenseProductState extends VMState {
    public DispenseProductState(VendingMachine vendingMachine, ItemShelf slot) throws Exception {
        System.out.println("Vending machine is in Dispense Product State");
        Item item = dispenseProduct(vendingMachine, slot);
        System.out.println("Item - " + item.itemType + " is dispensed for price : " + item.price);
    }

    @Override
    public Item dispenseProduct(VendingMachine vendingMachine, ItemShelf slot) throws Exception {
        slot.isSoldout=true;
        vendingMachine.vendingMachineState=new IdleState();
        return slot.item;
    }
}
