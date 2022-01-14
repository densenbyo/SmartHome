package Entities.Classes.Creatures;

import Controller.Controller;
import Entities.Classes.Electronics.Device;
import Entities.Classes.Things;
import Entities.Classes.House.Room;
import Entities.Entity;
import Logger.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Person extends Entity {
    private final int age;
    private boolean isHungry = false;

    private List<Event> toDo = new ArrayList<>();
    protected List<Device> usableD  = new ArrayList<>();
    protected List<Things> usableE = new ArrayList<>();
    protected Controller houseController = Controller.getInstance();

    public Person(String name, Room room, int age) {
        super(name, room);
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void useDevice(List<Device> target){
        this.usableD = target;
    }

    public void useThings(List<Things> target){
        this.usableE = target;
    }

    public void setRoom(Room room){
        if(this.room != null){
            this.room.deletePeople();
        }
        room.addPeople();
        this.room = room;
    }

    public boolean isHungry(){
        return isHungry;
    }

    public void setHungry(boolean bool){
        isHungry = bool;
        if(isHungry()){
            announce("I want to eat");
        }
    }

    @Override
    public void newLap() {
        int n = new Random().nextInt(100);

        if (!toDo.isEmpty()) {
            Event eventToDo = toDo.get(0);
            switch (eventToDo.getInfo()) {
                case "repair device" -> {
                    setRoom(eventToDo.devices.getRoom());
                    if (eventToDo.devices.repair()) {
                        houseController.eventLog.writeToLog("Device: " + eventToDo.devices.getName() + " fixed by: " + name);
                        houseController.activityUsageLog.writeToLog("Device: " + eventToDo.devices.getName() + " fixed by: " + name);
                    }
                }
                case "I AM DONE, but after use" -> {
                    setRoom(eventToDo.things.getRoom());
                    if (eventToDo.things.repair()) {
                        houseController.eventLog.writeToLog("Thing: " + eventToDo.things.getName() + " fixed by: " + name);
                        houseController.activityUsageLog.writeToLog("Thing: " + eventToDo.things.getName() + " fixed by: " + name);
                    }
                }
                case "I AM HUNGRY" -> {
                    setRoom(eventToDo.pet.getRoom());
                    if (eventToDo.pet.feed()) {
                        houseController.eventLog.writeToLog("Animal: " + eventToDo.pet.getName() + " is hungry, " + name + " fed him");
                        houseController.activityUsageLog.writeToLog("Animal: " + eventToDo.pet.getName() + " is hungry, " + name + " fed him");
                    }
                }
                case "Out of food" -> {
                    setRoom(eventToDo.devices.getRoom());
                    if(eventToDo.devices.fill()){
                        houseController.eventLog.writeToLog("Device: " + eventToDo.pet.getName() + " is hungry, " + name + " fed him");
                        houseController.activityUsageLog.writeToLog("Animal: " + eventToDo.pet.getName() + " is hungry, " + name + " fed him");
                    }
                }
                default -> {
                }
            }
            toDo.remove(0);
        } else {
            if (!nextActivity()) {
                houseController.eventLog.writeToLog(name + " has nothing to do. " + name + " is in " + room.getName());
                houseController.activityUsageLog.writeToLog(name + " has nothing to do. " + name + " is in " + room.getName());
            }
        }

        if(n <= 50){
            setHungry(true);
        }
    }

    public boolean nextActivity(){
        int n = new Random().nextInt(100);

        if (n >= 50) {
            Collections.shuffle(usableD);
            for (Device device : usableD) {
                if (device.Usable()) {
                    setRoom(device.getRoom());
                    houseController.activityUsageLog.writeToLog(name + " used " + device.getName() + " in " + room.toString());
                    return true;
                }
            }

            System.out.println(name + ": No usable devices. I'll look for some tool.");
            Collections.shuffle(usableE);
            for (Things tool : usableE) {
                if (tool.Usable()) {
                    setRoom(tool.getRoom());
                    houseController.activityUsageLog.writeToLog(name + " used " + tool.getName() + " in " + room.toString());
                    return true;
                }
            }


        } else {
            Collections.shuffle(usableE);
            for (Things tool : usableE) {
                if (tool.Usable()) {
                    setRoom(tool.getRoom());
                    houseController.activityUsageLog.writeToLog(name + " used " + tool.getName() + " in " + room.toString());
                    return true;
                }
            }

            System.out.println(name + ": No usable tools. I'll look for some devices.");
            Collections.shuffle(usableD);
            for (Device device : usableD) {
                if (device.Usable()) {
                    setRoom(device.getRoom());
                    houseController.activityUsageLog.writeToLog(name + " used " + device.getName() + " in " + room.toString());
                    return true;
                }
            }
        }
        System.out.println(name + ": No usable tools or devices found.");
        return false;
    }

    public void update(Device d, String info){
        toDo.add(new Event(d, info));
    }

    public void update(Things e, String info){
        toDo.add(new Event(e, info));
    }

    public void update(Animal a, String info){
        toDo.add(new Event(a, info));
    }

    public void update(Person p, String info){
        toDo.add(new Event(p, info));
    }

    public void announce(String log) {
            StringBuilder INFO = new StringBuilder();
            update(this, "I want to eat");
            houseController.eventLog.writeToLog(name + " is hungry.");
    }
}
