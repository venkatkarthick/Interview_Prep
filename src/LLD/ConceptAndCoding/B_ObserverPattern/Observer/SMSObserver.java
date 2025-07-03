package LLD.ConceptAndCoding.B_ObserverPattern.Observer;

import LLD.ConceptAndCoding.B_ObserverPattern.StocksObservable;

public class SMSObserver implements NotificationAlertObserver{

    String uname;
    StocksObservable stocksObservable;
    public SMSObserver(String uname, StocksObservable stocksObservable) {
        this.uname=uname;
        this.stocksObservable=stocksObservable;
    }

    @Override
    public void update() {
        System.out.println("Sent SMS for user ID - " + uname+" "+stocksObservable.getStockCount());
    }
}
