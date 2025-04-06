package LLD.HeadFirstDesignPattern.B_ObserverPattern;

import LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer.CurrentConditionsDisplay;
import LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer.ForeCastDisplay;
import LLD.HeadFirstDesignPattern.B_ObserverPattern.Observer.StatisticsDisplay;
import LLD.HeadFirstDesignPattern.B_ObserverPattern.Subject.WeatherData;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay= new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay=new StatisticsDisplay(weatherData);
        ForeCastDisplay foreCastDisplay=new ForeCastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
