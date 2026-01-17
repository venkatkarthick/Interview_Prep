package Revision.LLD;

import java.util.ArrayList;
import java.util.List;

public class B_ObserverPattern {
    //It is a behavioural design pattern where an object (aka 'observable' or 'publisher') maintains a list of dependents (or 'observers') and automatically notifies the observer when there is a change in yhe state.
    //2 Models - Push(Observable pushes he data). Pull(Observer holds the observable object and it pulls the data using observable object when the state of Observable got update)

    //Pull Model
    interface Observable{
        public void add(Observer observer);
        public void remove(Observer observer);
        public void notifyObservers();
        public void setStockCount(int count);
        public String getStockCount();
    }
    static class StockObservable implements Observable{
        int stockCount=0;
        String stockName;
        List<Observer> observers=new ArrayList<>();
        StockObservable(String name) {
            this.stockName=name;
        }
        @Override
        public void add(Observer observer) {
            observers.add(observer);
        }
        @Override
        public void remove(Observer observer) {
            observers.remove(observer);
        }
        @Override
        public void notifyObservers() {
            for (Observer observer: observers) {
                observer.update();
            }
        }
        @Override
        public void setStockCount(int count) {
            if(stockCount==0) {
                stockCount=count;
                notifyObservers();
            }
            stockCount=count;
        }
        @Override
        public String getStockCount() {
            return stockName +" : "+stockCount;
        }
    }


    interface Observer {
        public void update(); //Here, In Push model, update function has observable object as parameter passes it down to Observer
    }
    static class EmailObserver implements Observer{
        String user;
        Observable observable; //Pull model
        EmailObserver(String user, Observable observable) {
            this.user=user;
            this.observable=observable;
            observable.add(this);
        }
        @Override
        public void update() {
            System.out.println("Notifying the user " + user + " through email for the stock count : " +observable.getStockCount()); //Observer pulls only required data from observable
        }
    }
    static class SMSObserver implements Observer{
        String user;
        Observable observable;
        SMSObserver(String user, Observable observable) {
            this.user=user;
            this.observable=observable;
            observable.add(this);
        }
        @Override
        public void update() {
            System.out.println("Notifying the user " + user + " through SMS for the stock count : " +observable.getStockCount());
        }
    }

    public static void main(String[] args) {
        Observable phone = new StockObservable("phone");
        Observable laptop = new StockObservable("laptop");

        Observer obsA=new EmailObserver("obsA", phone);
        Observer obsB=new SMSObserver("obsB", laptop);
        Observer obsC=new EmailObserver("obsC", laptop);
        Observer obsD=new SMSObserver("obsD", phone);

        phone.setStockCount(5);
        phone.setStockCount(0);
        phone.setStockCount(10);
        laptop.setStockCount(15);
    }
}
