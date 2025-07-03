package LLD.ConceptAndCoding.B_ObserverPattern;

import LLD.ConceptAndCoding.B_ObserverPattern.Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class HelmetStockObservable implements StocksObservable{

    public int stockCount=0;
    String productName="Helmet";
    List<NotificationAlertObserver> observerList=new ArrayList<>();

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (NotificationAlertObserver observer: observerList) {
            observer.update();
        }
    }

    @Override
    public void setStockCount(int count) {
        int temp=stockCount;
        stockCount=count;
        if(temp==0) {
            notifySubscribers();
        }
    }

    @Override
    public String getStockCount() {
        return "Available Stocks for the " +productName+ " is "+stockCount;
    }
}
