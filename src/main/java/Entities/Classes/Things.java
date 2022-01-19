package entities.classes;

import controller.Controller;
import entities.classes.creatures.Person;
import entities.classes.electronics.utils.State;
import entities.classes.house.Room;
import entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Things.
 * By using state design pattern and observable design pattern
 *
 * @author Mukan Atazhanov Created on 13-Jan-22
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

    /**
     * Boolean method of repair function
     * if device is fixed return true, otherwise false
     *
     * @return boolean
     */
    public boolean repair(){
        if(getState() == State.BROKEN){
            initHP = 100;
            setState(State.IDLE);
            announce("fixing");
            return true;
        }
        return false;
    }

    /**
     * Boolean method of breakdown function
     * if device is broken return true, otherwise false
     *
     * @return boolean
     */
    public boolean breakdown(){
        setState(State.BROKEN);
        announce("I AM DONE");
        return true;
    }

    public void addObserver(Person observer){
        people.add(observer);
    }

    /**
     * Boolean method of usability function
     * after using device HP of device going to decrease by 1
     * after HP becomes 0, state of device going to be set as broken
     *
     * @return boolean
     */
    public boolean Usable(){
        if (getState() == State.IDLE) {
            setState(State.ON);
            initHP--;
            if (initHP < 1) {
                setState(State.BROKEN);
                System.out.println(name + " broke");
                announce("I AM DONE, but after use");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method announce any change of things
     *
     * @param info
     */
    public void announce(String info){
        if (!people.isEmpty()) {
            StringBuilder strObsList = new StringBuilder();
            for (Person observer : people) {
                observer.update(this, info);
                strObsList.append(observer.getName()).append(", ");
            }
            strObsList = new StringBuilder(strObsList.substring(0, strObsList.length() - 2));
            houseController.eventLog.writeToLog(name + " broke down. Notify sent to: " + strObsList);
        } else {
            System.out.println(name + " has no observers.");
        }
    }

    /**
     * New lap method, creates new lap
     * In new lap each thing will be set to idle state
     */
    @Override
    public void newLap(){
        setState(State.IDLE);
    }
}
