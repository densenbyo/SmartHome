package Entities;

import Entities.Classes.House.Room;
import Utils.Lap;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public abstract class Entity implements Lap {
    protected String name;
    protected Room room;

    public Entity(String name, Room room){
        this.name = name;
        this.room = room;
    }

    public Room getRoom(){
        return room;
    }

    public String getName(){
        return name;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public void setName(String name){
        this.name = name;
    }
}