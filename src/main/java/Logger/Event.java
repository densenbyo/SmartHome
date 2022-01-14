package Logger;

import Entities.Classes.Creatures.Animal;
import Entities.Classes.Creatures.Person;
import Entities.Classes.Electronics.Device;
import Entities.Classes.Things;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Event {

    public Things things;
    public Device devices;
    public Animal pet;
    public Person person;
    public String info;

    public Event(Device devices, String info) {
        this.devices = devices;
        this.info = info;
    }

    public Event(Things things, String info) {
        this.things = things;
        this.info = info;
    }

    public Event(Animal pet, String info) {
        this.pet = pet;
        this.info = info;
    }

    public Event(Person p, String info) {
        this.person = p;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
