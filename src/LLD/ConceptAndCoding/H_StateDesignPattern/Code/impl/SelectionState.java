package LLD.ConceptAndCoding.H_StateDesignPattern.Code.impl;

import LLD.ConceptAndCoding.H_StateDesignPattern.Code.Coin;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.ItemShelf;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VMState;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class SelectionState extends VMState {

    public SelectionState() {
        System.out.println("Vending Machine is in Product Selection State");
    }

    @Override
    public void chooseProduct(VendingMachine vendingMachine, int codeNumber) throws Exception {
        ItemShelf currentSlot=null;
        //Search inventory
        for(ItemShelf slot : vendingMachine.inventory.itemShelf) {
            if(codeNumber==slot.codeNumber) {
                currentSlot=slot;
                break;
            }
        }
        if(currentSlot==null) System.out.println("Invalid code number");
        int totalMoney = 0;
        for(Coin coin : vendingMachine.coins) totalMoney+=coin.value;
        if(totalMoney>= currentSlot.item.price) {
            int change = getChange(totalMoney-currentSlot.item.price);
            System.out.println("Amount returned : " + change);
            vendingMachine.vendingMachineState=new DispenseProductState(vendingMachine, currentSlot);
        } else {
            List<Coin> amount = refundFullMoney(vendingMachine);
            vendingMachine.vendingMachineState=new IdleState();
            System.out.println("Amount returned : " + amount);
            throw new Exception("Insufficient fund");
        }

    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        //Atm kind of logic fetch money from money pool
        return returnChangeMoney;
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine vendingMachine) throws Exception {
        List<Coin> returnedCoins = new ArrayList<>(vendingMachine.coins);
        vendingMachine.coins=new ArrayList<>();
        vendingMachine.vendingMachineState=new IdleState();
        return returnedCoins;
    }


}
