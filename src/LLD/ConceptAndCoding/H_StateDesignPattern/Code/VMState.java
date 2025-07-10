package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

import java.util.List;

public abstract class VMState {

    public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
        throw new Exception("User not allowed to access this function in the state - "+vendingMachine.vendingMachineState);
    }
    public void clickOnStartProductSelectionButton(VendingMachine vendingMachine) throws Exception {
        throw new Exception("User not allowed to access this function in the state - "+vendingMachine.vendingMachineState);
    }
    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
        throw new Exception("User not allowed to access this function in the state - "+vendingMachine.vendingMachineState);
    }
    public void chooseProduct(VendingMachine vendingMachine, int codeNumber) throws Exception {
        throw new Exception("User not allowed to access this function in the state - "+vendingMachine.vendingMachineState);
    }
    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("User not allowed to access this function in the current state ");
    }
    public Item dispenseProduct(VendingMachine vendingMachine, ItemShelf slot) throws Exception {
        throw new Exception("User not allowed to access this function in the state - "+vendingMachine.vendingMachineState);
    }
    public List<Coin> refundFullMoney(VendingMachine vendingMachine) throws Exception {
        throw new Exception("User not allowed to access this function in the state ");
    }
    public void updateInventory(VendingMachine vendingMachine, Item item, int codeNumber) throws Exception {
        throw new Exception("User not allowed to access this function in the current state - "+vendingMachine.vendingMachineState);
    }
}
