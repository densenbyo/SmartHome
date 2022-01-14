package Entities.Classes.Electronics;

import Controller.Controller;
import Entities.Classes.Electronics.Utils.Consumption;
import Entities.Classes.Electronics.Utils.State;
import Entities.Classes.House.Room;

/**
 * @author Mukan Atazhanov
 * Created on 14-Jan-22
 */

public class Microwave extends Device{

    Controller controller = Controller.getInstance();

    public Microwave(String name, Room room, Consumption consumption) {
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
