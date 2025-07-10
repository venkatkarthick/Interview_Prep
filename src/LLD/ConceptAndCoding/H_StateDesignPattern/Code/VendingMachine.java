package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

import LLD.ConceptAndCoding.H_StateDesignPattern.Code.impl.IdleState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    public VMState vendingMachineState;
    public Inventory inventory;
    public List<Coin> coins;

    public VendingMachine() {
        this.vendingMachineState = new IdleState();
        this.inventory = new Inventory(10);
        this.coins = new ArrayList<>();
    }
}
