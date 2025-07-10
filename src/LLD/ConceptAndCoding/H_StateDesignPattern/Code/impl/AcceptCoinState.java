package LLD.ConceptAndCoding.H_StateDesignPattern.Code.impl;

import LLD.ConceptAndCoding.H_StateDesignPattern.Code.Coin;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VMState;
import LLD.ConceptAndCoding.H_StateDesignPattern.Code.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class AcceptCoinState extends VMState {

    public AcceptCoinState() {
        System.out.println("Vending Machine is in Accept Coin state");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine vendingMachine) throws Exception {
        vendingMachine.vendingMachineState=new SelectionState();
    }

    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
        vendingMachine.coins.add(coin);
        System.out.println("Coins in Machine : " + vendingMachine.coins);
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine vendingMachine) throws Exception {
        List<Coin> returnedCoins = new ArrayList<>(vendingMachine.coins);
        vendingMachine.coins=new ArrayList<>();
        vendingMachine.vendingMachineState=new IdleState();
        return returnedCoins;
    }
}
