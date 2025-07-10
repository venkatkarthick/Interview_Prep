package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

public class ItemShelf {
    public Item item;
    public int codeNumber;
    public boolean isSoldout;

    public ItemShelf(Item item, int codeNumber, boolean isSoldout) {
        this.item = item;
        this.codeNumber = codeNumber;
        this.isSoldout = isSoldout;
    }

    @Override
    public String toString() {
        return "[" +
                "item=" + item +
                ", coder=" + codeNumber +
                ", sold=" + isSoldout +
                ']';
    }
}
