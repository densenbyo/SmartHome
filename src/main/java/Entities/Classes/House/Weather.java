package Entities.Classes.House;

import Entities.Classes.House.Utils.Utils;

import java.util.Random;

/**
 * @author Mukan Atazhanov
 * Created on 14-Jan-22
 */

public class Weather {
    private static final Weather instance = new Weather();

    public enum Type {
        SUNNY,
        WINDY,
        RAINY,
    }

    private Type weather = Type.SUNNY;
    private int temp = Utils.WEATHER_TEMP;

    private Weather(){}

    public static Weather getInstance(){
        return instance;
    }

    public Type getWeather(){
        return weather;
    }

    public int getTemp(){
        return temp;
    }

    public boolean getType(){
        if(weather == Type.SUNNY){
            return true;
        }else if(weather == Type.RAINY){
            return true;
        }else return weather == Type.WINDY;
    }

    public void setWeather(){
        int num = new Random().nextInt(100);
        if(num > 75){
            num = new Random().nextInt(10);
            if (num <= 3){
                weather = Type.SUNNY;
            } else if(num <= 6){
                weather = Type.RAINY;
            } else {
                weather = Type.WINDY;
            }
        }
    }

    public void setTemp(){
        int num = new Random().nextInt(5);

        switch (weather) {
            case RAINY -> {
                if (temp < Utils.WEATHER_RAINY_TEMP) {
                    temp += num;
                }
                temp -= num;
            }
            case SUNNY -> {
                if (temp < Utils.WEATHER_SUNNY_TEMP) {
                    temp += num;
                }
                temp -= num;
            }
            default -> {
                if (temp < Utils.WEATHER_WINDY_TEMP) {
                    temp += num;
                }
                temp -= num;
            }
        }
    }

    public void update(){
        setWeather();
        setTemp();
    }
}
