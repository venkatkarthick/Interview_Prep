package LLD.ConceptAndCoding.B_ObserverPattern.Observer;

import LLD.ConceptAndCoding.B_ObserverPattern.StocksObservable;

public class EmailObserver implements NotificationAlertObserver{

    String email;
    StocksObservable stocksObservable;
    public EmailObserver(String email, StocksObservable stocksObservable) {
        this.email=email;
        this.stocksObservable=stocksObservable;
    }
    @Override
    public void update() {
        System.out.println("Sent Email to email ID - " + email+" "+stocksObservable.getStockCount());
    }
}
