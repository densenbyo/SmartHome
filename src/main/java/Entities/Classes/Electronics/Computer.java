package entities.classes.electronics;

import controller.Controller;
import entities.classes.electronics.utils.Consumption;
import entities.classes.electronics.utils.State;
import entities.classes.house.Room;

import java.util.Random;

/**
 * @author Mukan Atazhanov
 * Created on 14-Jan-22
 */

public class Computer extends Device{

    Controller controller = Controller.getInstance();

    public Computer(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }

    @Override
    public boolean Usable() {
        if (getState() == State.IDLE) {
            state = State.ON;
            initHP--;
            if (initHP < 1) {
                state = State.BROKEN;
                houseController.eventLog.writeToLog(getName() + " I AM DONE");
                announce("repair device");
            }
            int num = new Random().nextInt(100);
            if (num < 2) {
                houseController.eventLog.writeToLog(getName() + " how tf u get virus in 2022?????.");
                announce("repair device");
            }
            return true;
        } else {
            return false;
        }
    }
}
