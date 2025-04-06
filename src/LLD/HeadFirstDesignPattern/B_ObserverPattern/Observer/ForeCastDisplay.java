package LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer;

import LLD.HeadFirstDesignPattern.B_ObserverPattern.Subject.Subject;

public class ForeCastDisplay implements DisplayElement, Observer {

    private Subject weatherData;
    private float currentPressure;
    private float lastPressure;

    public ForeCastDisplay(Subject weatherData) {
        this.weatherData=weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        if(lastPressure==currentPressure) {
            System.out.println("More of the same");
        } else if(currentPressure>lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if(currentPressure<lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        lastPressure=currentPressure;
        currentPressure=pressure;
        display();
    }
}
