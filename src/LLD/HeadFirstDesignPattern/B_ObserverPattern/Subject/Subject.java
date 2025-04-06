package LLD.HeadFirstDesignPattern.B_ObserverPattern.Subject;

import LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer.Observer;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
