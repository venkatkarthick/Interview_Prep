package LLD.ConceptAndCoding.H_StateDesignPattern.Code;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<ItemShelf> itemShelf;

    public Inventory(int size) {
        itemShelf=new ArrayList<>(size);
    }

    //public ItemShelf getItem(int codeNumber)

}
