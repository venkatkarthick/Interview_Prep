package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

import java.util.List;
import java.util.Stack;

public class Client {
    public static void main(String[] args) {
        VendingMachine vendingMachine=new VendingMachine();
        try {
            System.out.println("|");
            System.out.println("Filling the inventory up");
            System.out.println("|");

            fillUpInventory(vendingMachine);
            displayInventory(vendingMachine);

            System.out.println("|");
            System.out.println("Clicking on InsertCoinButton");
            System.out.println("|");

            VMState vmState = vendingMachine.vendingMachineState;
            vmState.clickOnInsertCoinButton(vendingMachine);

            vmState = vendingMachine.vendingMachineState;
            vmState.insertCoin(vendingMachine,Coin.TEN);
            vmState.insertCoin(vendingMachine,Coin.TEN);
            vmState.insertCoin(vendingMachine,Coin.TEN);

            System.out.println("|");
            System.out.println("Clicking on ProductSelectionButton");
            System.out.println("|");
            vmState.clickOnStartProductSelectionButton(vendingMachine);

            vmState = vendingMachine.vendingMachineState;
            vmState.chooseProduct(vendingMachine, 7);

            displayInventory(vendingMachine);

        } catch (Exception e) {
            System.out.println("Error thrown : " + e.getMessage());
        }
    }

    private static void displayInventory(VendingMachine vendingMachine) {
        System.out.println("-----BEGIN-----");
        for(ItemShelf itemShelf : vendingMachine.inventory.itemShelf) {
            System.out.println(itemShelf);
        }
        System.out.println("-----END-------");
    }

    private static void fillUpInventory(VendingMachine vendingMachine) {
        List<ItemShelf> slots = vendingMachine.inventory.itemShelf;
        for(int i=0; i<10; i++) {
            if(i<3) slots.add(new ItemShelf(new Item(ItemType.COKE, 30), i, false));
            else if(i>=3 && i<5) slots.add(new ItemShelf(new Item(ItemType.JUICE, 20), i, false));
            else if(i>=5 && i<7) slots.add(new ItemShelf(new Item(ItemType.SODA, 35), i, false));
            else slots.add(new ItemShelf(new Item(ItemType.PEPSI, 25), i, false));
        }
    }
}
