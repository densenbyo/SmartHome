package entities.classes.sensor;

import controller.Controller;
import entities.classes.house.Room;
import entities.classes.house.utils.Utils;
import entities.classes.house.Weather;
import utils.Lap;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Sensor implements Lap {
    private final Room room;
    private Weather.Type weather = null;
    protected int temp = 0;
    private Controller controller = Controller.getInstance();

    public Sensor(Room room) {
        this.room = room;
    }

    public Room getRoom(){
        return room;
    }

    @Override
    public void newLap() {
        weather = Weather.getInstance().getWeather();
        temp = Weather.getInstance().getTemp();

        if (weather == Weather.Type.WINDY || weather == Weather.Type.RAINY) {
            if (room.winOpen()) {
                room.setWindows(false);
                controller.eventLog.writeToLog(room.getName()+": windows closed.");
            }
            if (room.curtainsOpen()) {
                room.setCurtains(true);
                controller.eventLog.writeToLog(room.getName()+": blinds raised.");
            }
        } else {
            // i.e. cloudy or sunny
            if ((temp >= Utils.WEATHER_OPEN_WINDOWS_TEMP) && (!room.winOpen())) {
                room.setWindows(true);
                controller.eventLog.writeToLog(room.getName()+": windows opened.");
            }
            if ((temp < Utils.WEATHER_OPEN_WINDOWS_TEMP) && (room.winOpen())) {
                room.setWindows(true);
                controller.eventLog.writeToLog(room.getName()+": windows closed.");
            }
        }
    }
}
