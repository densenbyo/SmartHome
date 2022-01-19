package entities.classes.creatures;

import controller.Controller;
import entities.classes.Things;
import entities.classes.house.Room;
import entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Animal extends Entity {

    private String typeOfAnimal;
    protected boolean hungry = false;
    protected List<Person> observers = new ArrayList<>();
    private final List<Things> electro = Controller.getInstance().getElectro();
    protected Controller controller = Controller.getInstance();

    public Animal(String typeOfAnimal ,String name, Room room) {
        super(name, room);
        this.typeOfAnimal = typeOfAnimal;
    }

    public String getType(){
        return typeOfAnimal;
    }

    public void setType(String type){
        typeOfAnimal = type;
    }

    public boolean isHungry(){
        return hungry;
    }

    public void setHungry(){
        hungry = true;
        announce("I AM HUNGRY");
    }

    public boolean feed(){
        if(isHungry()){
            hungry = false;
            announce("I AM FULL");
            return true;
        }
        return false;
    }

    public void addPerson(Person person){
        observers.add(person);
    }

    @Override
    public void newLap() {
        int n = new Random().nextInt(100);

        if (n <= 5) {
            Collections.shuffle(electro);
            for (Things tool : electro) {
                if (tool.breakdown()) {
                    controller.eventLog.writeToLog(name + " destroyed " + tool.getName());
                    controller.activityUsageLog.writeToLog(name + " destroyed " + tool.getName());
                    break;
                }
            }
        } else if(n <= 40){
            setHungry();
            announce("I AM HUNGRY");
        }
    }

    public void announce(String log) {
        if (!observers.isEmpty()) {
            StringBuilder INFO = new StringBuilder();

            for (Person observer : observers) {
                observer.update(this, log);
                INFO.append(observer.getName()).append(", ");
            }
            INFO = new StringBuilder(INFO.substring(0, INFO.length() - 2));
            controller.eventLog.writeToLog(name + " is hungry. Notify sent to: " + INFO);
        } else {
            System.out.println(name + " has no observers.");
        }
    }
}
