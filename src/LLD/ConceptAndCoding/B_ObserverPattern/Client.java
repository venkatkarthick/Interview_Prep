package LLD.ConceptAndCoding.B_ObserverPattern;

import LLD.ConceptAndCoding.B_ObserverPattern.Observer.EmailObserver;
import LLD.ConceptAndCoding.B_ObserverPattern.Observer.NotificationAlertObserver;
import LLD.ConceptAndCoding.B_ObserverPattern.Observer.SMSObserver;

public class Client {

    public static void main(String[] args) {
        StocksObservable iphone=new IphoneObservable();
        StocksObservable helmet=new HelmetStockObservable();

        NotificationAlertObserver observer1=new EmailObserver("xyz@gmail.com", iphone);
        NotificationAlertObserver observer2=new EmailObserver("abc@gmail.com", iphone);
        NotificationAlertObserver observer3=new SMSObserver("venkat", iphone);

        NotificationAlertObserver observer4=new EmailObserver("ghi@gmail.com", helmet);

        iphone.add(observer1);
        iphone.add(observer2);
        iphone.add(observer3);
        helmet.add(observer4);

        iphone.setStockCount(1);
        iphone.setStockCount(0);
        iphone.setStockCount(100);
        helmet.setStockCount(10);
    }
}
