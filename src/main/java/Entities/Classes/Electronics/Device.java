package entities.classes.electronics;

import entities.classes.creatures.Person;
import entities.classes.electronics.utils.Consumption;
import entities.classes.electronics.utils.Manual;
import entities.classes.electronics.utils.State;
import entities.classes.house.Room;
import entities.classes.house.utils.Utils;
import entities.classes.Things;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Device extends Things {
    private final Consumption consumption;
    private Manual manual;
    private int CURRENT_FOOD;
    private boolean isEmpty = false;

    public Device(String name, Room room, Consumption consumption) {
        super(name, room);
        this.consumption = consumption;
        CURRENT_FOOD = Utils.MAX_FOOD;
    }

    public Consumption getConsumption(){
        return consumption;
    }

    @Override
    public boolean repair(){
        if(getState() == State.BROKEN){
            if(manual == null){
                manual = new Manual(this);
            }
            setState(State.IDLE);
            initHP = 100;
            return true;
        }
        return false;
    }

    @Override
    public void newLap(){
        consumption.update(state);
        setState(State.IDLE);
    }

    @Override
    public boolean Usable(){
        if (getState() == State.IDLE) {
            setState(State.ON);
            initHP--;
            if (initHP < 1) {
                setState(State.BROKEN);
                System.out.println(name + " broke down.");
                announce("repair device");
            }
            eat(5);
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

    public boolean fill(){
        announce("Feeling");
        CURRENT_FOOD = Utils.MAX_FOOD;
        return true;
    }


    public boolean eat(int amount){
        if(getFood() > amount && amount > 0 && amount < 10){
            CURRENT_FOOD -= amount;
            announce("Food eaten");
            if(CURRENT_FOOD <= 0){
                announce("Out of food");
                CURRENT_FOOD = 0;
                setEmpty();
            }
        }
        return true;
    }

    public int getFood(){
        return CURRENT_FOOD;
    }

    public void setEmpty(){
        isEmpty = !isEmpty;
    }
}