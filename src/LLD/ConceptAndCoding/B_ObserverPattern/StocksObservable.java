package LLD.ConceptAndCoding.B_ObserverPattern;

import LLD.ConceptAndCoding.B_ObserverPattern.Observer.NotificationAlertObserver;

public interface StocksObservable {

    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();
    public void setStockCount(int count);
    public String getStockCount();

}
