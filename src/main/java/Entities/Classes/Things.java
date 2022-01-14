package Entities.Classes;

import Controller.Controller;
import Entities.Classes.Creatures.Person;
import Entities.Classes.Electronics.Utils.State;
import Entities.Classes.House.Room;
import Entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Things extends Entity {
    public int initHP;

    protected State state;
    protected Controller houseController = Controller.getInstance();
    protected List<Person> people = new ArrayList<>();

    public Things(String name, Room room) {
        super(name, room);
        initHP = 100;
        state = State.IDLE;
    }

    public int getHP(){
        return initHP;
    }

    public void setState(State state){
        this.state = state;
    }

    public boolean isBroken(){
        return state == State.BROKEN;
    }

    public State getState(){
        return state;
    }

    public boolean repair(){
        if(getState() == State.BROKEN){
            initHP = 100;
            setState(State.IDLE);
            //todo notice
            announce("fixing");
        }
        return false;
    }

    public boolean breakdown(){
        setState(State.BROKEN);
        //todo notice
        announce("I AM DONE");
        return true;
    }

    public void addObserver(Person observer){
        people.add(observer);
    }

    public boolean Usable(){
        if (getState() == State.IDLE) {
            setState(State.ON);
            initHP--;
            if (initHP < 1) {
                setState(State.BROKEN);
                System.out.println(name + " broke");
                //todo notice
                announce("I AM DONE, but after use");
            }
            return true;
        } else {
            return false;
        }
    }

    public void announce(String info){
        if (!people.isEmpty()) {
            StringBuilder strObsList = new StringBuilder();
            for (Person observer : people) {
                observer.update(this, info);
                strObsList.append(observer.getName()).append(", ");
            }
            strObsList = new StringBuilder(strObsList.substring(0, strObsList.length() - 2));
            System.out.println(name + " broke down. Notify sent to: " + strObsList);
            houseController.eventLog.writeToLog(name + " broke down. Notify sent to: " + strObsList);
        } else {
            System.out.println(name + " has no observers.");
        }
    }

    @Override
    public void newLap(){
        setState(State.IDLE);
    }
}
