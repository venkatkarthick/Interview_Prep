package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

public class Item {

    public ItemType itemType;
    public int price;

    public Item(ItemType itemType, int price) {
        this.itemType = itemType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" +
                "itemName=" + itemType +
                ", price=" + price +
                ']';
    }
}
