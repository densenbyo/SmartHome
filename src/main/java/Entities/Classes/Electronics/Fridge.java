package entities.classes.electronics;

import controller.Controller;
import entities.classes.electronics.utils.Consumption;
import entities.classes.electronics.utils.State;
import entities.classes.house.Room;

/**
 * @author Mukan Atazhanov
 * Created on 14-Jan-22
 */

public class Fridge extends Device{

    Controller controller = Controller.getInstance();

    public Fridge(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }

    @Override
    public boolean Usable() {
        switch (getState()){
            case ON:
                return true;
            case IDLE:
                setState(State.ON);
                initHP--;
                if (initHP < 1) {
                    state = State.BROKEN;
                    houseController.eventLog.writeToLog(getName() + " help!");
                    announce("repair device");
                }
                return true;
            default:
                return false;
        }
    }
}
