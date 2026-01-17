package Revision.LLD;

import java.util.ArrayList;
import java.util.List;

public class I_StatePattern {

    static class VendingMachine{
        Inventory inventory;
        VMState vmState;
        List<Coin> coins;
        public VendingMachine(Inventory inventory, VMState vmState, List<Coin> coins) {
            this.inventory = inventory;
            this.vmState = vmState;
            this.coins = coins;
        }
        @Override
        public String toString() {
            return "{inventory=" + inventory + ", vmState=" + vmState + ", coins=" + coins + '}';
        }
    }

    static class Inventory{
        List<ItemShelf> itemShelf;
        Inventory(int size) {
            itemShelf=new ArrayList<>(size);
        }
        @Override
        public String toString() {
            return "Inventory{" + itemShelf + '}';
        }
    }
    static class ItemShelf{
        Item item;
        int codeNumber;
        boolean isSoldOut;
        ItemShelf(Item item, int codeNumber, boolean isSoldOut) {
            this.item=item;
            this.codeNumber=codeNumber;
            this.isSoldOut=isSoldOut;
        }
        @Override
        public String toString() {
            return "[item=" + item + ", codeNumber=" + codeNumber + ", isSoldOut=" + isSoldOut + ']';
        }
    }
    static class Item{
        ItemType itemType;
        int price;
        Item(ItemType itemType, int price) {
            this.itemType=itemType;
            this.price=price;
        }
        @Override
        public String toString() {
            return "{itemType=" + itemType + ", price=" + price + '}';
        }
    }
    enum ItemType{
        COKE, PEPSI, JUICE, SODA
    }

    enum Coin{
        ONE(1),TWO(2),FIVE(5),TEN(10);
        int value;
        Coin(int value) {this.value=value;}
    }

    static abstract class VMState{
        public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception {
            throw new Exception("User not allowed to access clickOnInsertCoinButton function in the state - "+vendingMachine.vmState);
        }
        public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
            throw new Exception("User not allowed to access insertCoin function in the state - "+vendingMachine.vmState);
        }
        public void clickOnProductSelectionButton(VendingMachine vendingMachine) throws Exception {
            throw new Exception("User not allowed to access clickOnProductSelectionButton function in the state - "+vendingMachine.vmState);
        }
        public void selectProduct(VendingMachine vendingMachine, int codeNumber) throws Exception {
            throw new Exception("User not allowed to access selectProduct function in the state - "+vendingMachine.vmState);
        }
        public Item dispenseProduct(VendingMachine vendingMachine, ItemShelf itemShelf) throws Exception {
            throw new Exception("User not allowed to access dispenseProduct function in the state - "+vendingMachine.vmState);
        }
        public void refundAmount(int refund) throws Exception {
            throw new Exception("User not allowed to access refundAmount function in the state");
        }
    }
    static class IdleState extends VMState{
        IdleState() {
            System.out.println("VM is in Idle State");
        }
        public void clickOnInsertCoinButton(VendingMachine vendingMachine) throws Exception{
            vendingMachine.vmState=new AcceptCoinState();
        }
    }
    static class AcceptCoinState extends VMState{
        AcceptCoinState() {
            System.out.println("VM is in Accept Coin State");
        }
        public void insertCoin(VendingMachine vendingMachine, Coin coin) {
            vendingMachine.coins.add(coin);
        }
        public void clickOnProductSelectionButton(VendingMachine vendingMachine) throws Exception{
            vendingMachine.vmState=new SelectionState();
        }
    }
    static class SelectionState extends VMState{
        SelectionState() {
            System.out.println("VM is in Selection State");
        }
        public void selectProduct(VendingMachine vendingMachine, int codeNumber) throws Exception {
            ItemShelf selectedShelf=null;
            for(ItemShelf shelf : vendingMachine.inventory.itemShelf) {
                if(codeNumber == shelf.codeNumber) {
                    selectedShelf=shelf;
                    break;
                }
            }
            if(selectedShelf==null) throw new Exception("Item not found in Inventory");
            //count coins
            int totalCash=0;
            for(Coin coin: vendingMachine.coins) {
                totalCash+= coin.value;
            }
            if(totalCash>=selectedShelf.item.price) {
                System.out.println("Item with codenumber : " + selectedShelf.codeNumber + " is sold out for price : " + selectedShelf.item.price);
                int refund = totalCash-selectedShelf.item.price;
                if(refund>0) {
                    System.out.println("Refund Amount : " + refund);
                    refundAmount(refund);
                }
                vendingMachine.vmState=new DispenseProduct();
                vendingMachine.vmState.dispenseProduct(vendingMachine, selectedShelf);
            } else {
                System.out.println("Insufficient Amount! Collect your cash!");
                refundAmount(totalCash);
            }
        }
        public void refundAmount(int refund) {
            System.out.println("Collect the refunded Amount : " + refund);
        }
    }
    static class DispenseProduct extends VMState{
        DispenseProduct() {
            System.out.println("VM is in Dispense Product State");
        }
        public Item dispenseProduct(VendingMachine vendingMachine, ItemShelf itemShelf) {
            itemShelf.isSoldOut=true;
            System.out.println("Collect the item" + itemShelf.item.itemType);
            vendingMachine.vmState=new IdleState();
            return itemShelf.item;
        }
    }

    public static void main(String[] args) {
        //VendingMachine vendingMachine=new VendingMachine();
        System.out.println("Filling the Inventory\n");
        Inventory inventory = fillupInventory();
        VendingMachine vendingMachine=new VendingMachine(inventory, new IdleState(), new ArrayList<>());
        System.out.println(vendingMachine);
        try {
            vendingMachine.vmState.clickOnInsertCoinButton(vendingMachine);
            vendingMachine.vmState.insertCoin(vendingMachine, Coin.TEN);
            vendingMachine.vmState.insertCoin(vendingMachine, Coin.TEN);
            vendingMachine.vmState.clickOnProductSelectionButton(vendingMachine);
            vendingMachine.vmState.selectProduct(vendingMachine, 7);

            //Expected to throw error when insert coin is accessed from idle state
            vendingMachine.vmState.insertCoin(vendingMachine, Coin.FIVE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static Inventory fillupInventory() {
        Inventory inventory=new Inventory(10);
        for(int i=0; i<3; i++) inventory.itemShelf.add(new ItemShelf(new Item(ItemType.COKE,30),i,false));
        for (int i=3; i<6; i++) inventory.itemShelf.add(new ItemShelf(new Item(ItemType.SODA,10),i,false));
        for (int i=6; i<8; i++) inventory.itemShelf.add(new ItemShelf(new Item(ItemType.JUICE,15),i,false));
        for (int i=8; i<10; i++) inventory.itemShelf.add(new ItemShelf(new Item(ItemType.PEPSI,25),i,false));
        return inventory;
    }
}
