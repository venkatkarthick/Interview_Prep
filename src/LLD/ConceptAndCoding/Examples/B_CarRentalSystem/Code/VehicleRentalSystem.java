package LLD.ConceptAndCoding.Examples.B_CarRentalSystem.Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleRentalSystem {
    List<Store> stores; //We can have Map<Location, Store>
    List<User> user;
    Map<String, List<Store>> storeMap=new HashMap<>();

    public VehicleRentalSystem(List<Store> stores, List<User> user) {
        this.stores = stores;
        this.user = user;
        for(Store store : stores) {
            List<Store> locationStore= storeMap.getOrDefault(store.location.city, new ArrayList<>());
            locationStore.add(store);
            storeMap.put(store.location.city, locationStore);
        }

    }

    public Store searchStore(Location location) {
        //based on location we will filter out Stores from StoreList
        return storeMap.get(location.city).get(0);
    }

    @Override
    public String toString() {
        return "VehicleRentalSystem{" +
                "stores=" + stores +
                ", user=" + user +
                ", storeMap=" + storeMap +
                '}';
    }
}
