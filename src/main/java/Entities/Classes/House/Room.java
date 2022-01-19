package entities.classes.house;

import entities.classes.house.utils.State;
import entities.classes.house.utils.Utils;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Room {
    protected State state;

    private final int floor;
    private final String name;

    private boolean windows = Utils.WINDOWS_CLOSED;
    private boolean curtains = Utils.CURTAINS_OPENED;
    private int devices;
    private int people;

    public Room(String name, int floor){
        this.name = name;
        this.floor = floor;

        state = State.OPEN;
        devices = 0;
        people = 0;
    }

    public int getFloor(){
        return floor;
    }

    public String getName(){
        return name;
    }

    public int getDeviceNum(){
        return devices;
    }

    public int getPeopleNum(){
        return people;
    }

    public void addDevice(){
        devices++;
    }

    public boolean winOpen(){
        return windows;
    }

    public boolean curtainsOpen(){
        return curtains;
    }

    public void setWindows(boolean bool){
        this.windows = bool;
    }

    public void setCurtains(boolean bool){
        this.curtains = bool;
    }

    public void deleteDevices() {
        if(devices > 0){
            devices--;
        }
    }

    public void addPeople(){
        people++;
    }

    public void deletePeople(){
        if(people > 0){
            people--;
        }
    }

    @Override
    public String toString(){
        return "Room: " + name + " on Floor: " + floor;
    }
}
