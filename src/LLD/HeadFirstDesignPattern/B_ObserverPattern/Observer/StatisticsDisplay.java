package LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer;

import LLD.HeadFirstDesignPattern.B_ObserverPattern.Subject.Subject;

public class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0;
    private float minTemp = 200;
    private float tempSum = 0;
    private int numReadings;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum/numReadings) + "/" + maxTemp + "/" + minTemp);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        maxTemp=Math.max(temp, maxTemp);
        minTemp=Math.min(temp, minTemp);
        tempSum+=temp;
        numReadings++;
        display();
    }
}
