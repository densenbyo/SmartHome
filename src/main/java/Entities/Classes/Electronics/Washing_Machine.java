package entities.classes.electronics;

import controller.Controller;
import entities.classes.electronics.utils.Consumption;
import entities.classes.house.Room;


/**
 * @author Mukan Atazhanov
 * Created on 14-Jan-22
 */

public class Washing_Machine extends Device{

    Controller controller = Controller.getInstance();

    public Washing_Machine(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }
}
