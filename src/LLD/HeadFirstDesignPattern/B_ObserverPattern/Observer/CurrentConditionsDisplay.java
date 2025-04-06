package LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer;

import LLD.HeadFirstDesignPattern.B_ObserverPattern.Subject.Subject;

public class CurrentConditionsDisplay implements DisplayElement, Observer {
    private float temp;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temp + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }
}
