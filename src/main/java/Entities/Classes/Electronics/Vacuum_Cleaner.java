package Entities.Classes.Electronics;

import Controller.Controller;
import Entities.Classes.Electronics.Utils.Consumption;
import Entities.Classes.Electronics.Utils.State;
import Entities.Classes.House.Room;

/**
 * @author Mukan Atazhanov
 * Created on 14-Jan-22
 */

public class Vacuum_Cleaner extends Device{

    Controller controller = Controller.getInstance();

    public Vacuum_Cleaner(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }
}
