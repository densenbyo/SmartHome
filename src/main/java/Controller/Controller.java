package Controller;

import Entities.Classes.Creatures.Animal;
import Entities.Classes.Creatures.Person;
import Entities.Classes.Electronics.Device;
import Entities.Classes.Things;
import Entities.Classes.House.Room;
import Entities.Classes.House.Utils.Utils;
import Entities.Classes.House.Weather;
import Entities.Classes.Sensor.Sensor;
import Logger.Logger;
import Utils.Lap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Controller {
    private static final Controller instance = new Controller();

    private final List<Lap> entities = new ArrayList<>();
    private final List<Sensor> sensors = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Person> people = new ArrayList<>();
    private final List<Animal> pets = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final List<Things> tools = new ArrayList<>();

    public final Logger configLog = new Logger("./Reports/HouseConfigurationReport.log");
    public final Logger eventLog = new Logger("./Reports/EventReport.log");
    public final Logger consumptionLog = new Logger("./Reports/ConsumptionReport.log");
    public final Logger activityUsageLog = new Logger("./Reports/ActivityAndUsageReport.log");

    public Controller(){}

    public static Controller getInstance(){
        return instance;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public List<Person> getPeople() {
        return people;
    }
    public List<Animal> getPets() {
        return pets;
    }
    public List<Device> getDevices() {
        return devices;
    }
    public List<Things> getElectro() {
        return tools;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        configLog.writeToLog("New room {" + room.getName() + "} on floor {" + room.getFloor() +"}");
    }

    public void addRooms(List<Room> rooms) {
        rooms.forEach(this::addRoom);
    }
    public void addPerson(Person person) {
        people.add(person);
        entities.add(person);
        configLog.writeToLog("New person {" + person.getName() + "} to room {" + person.getRoom().getName() + "}");
    }
    public void addPeople(List<Person> people) {
        people.forEach(this::addPerson);
    }
    public void addPet(Animal pet) {
        pets.add(pet);
        entities.add(pet);
        configLog.writeToLog("New animal {" + pet.getName() + "} to room {" + pet.getRoom().getName() + "}");
    }
    public void addPets(List<Animal> pets) {
        pets.forEach(this::addPet);
    }
    public void addThing(Things tool) {
        tools.add(tool);
        configLog.writeToLog("New electronic {" + tool.getName() + "} to room {" + tool.getRoom().getName() + "}");
    }
    public void addThings(List<Things> tools) {
        tools.forEach(this::addThing);
    }
    public void addDevice(Device device) {
        devices.add(device);
        entities.add(device);
        configLog.writeToLog("New device {" + device.getName() + "} to room {" + device.getRoom().getName() + "}");
    }
    public void addDevices(List<Device> devices) {
        devices.forEach(this::addDevice);
    }
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        configLog.writeToLog("New sensor in room {" + sensor.getRoom().getName() + "}");
    }
    public void addSensors(List<Sensor> sensors) {
        sensors.forEach(this::addSensor);
    }

    private void beginReport(){
        eventLog.writeToLog("==================Event report==================");
        eventLog.writeToLog(" ");
        consumptionLog.writeToLog("==================Consumption report==================");
        consumptionLog.writeToLog(" ");
        activityUsageLog.writeToLog("==================Activity report==================");
        activityUsageLog.writeToLog(" ");
    }

    private void newLapReport(int lap) {
        eventLog.writeToLog(" ");
        eventLog.writeToLog("Lap :" + lap + " started");
        activityUsageLog.writeToLog(" ");
        activityUsageLog.writeToLog("Lap :" + lap + " started");
    }

    private void consumptionReport(){
        for (Device device : devices) {
            consumptionLog.writeToLog(device.getName());
            consumptionLog.writeToLog("Electricity: "+device.getConsumption().getElectricityConsumption());
            consumptionLog.writeToLog("Gas: "+device.getConsumption().getGasConsumption());
            consumptionLog.writeToLog("Water: "+device.getConsumption().getWaterConsumption());
            consumptionLog.writeToLog("\n\n");
        }
    }

    public void Runner() {
        System.out.println("----------------------------------");
        System.out.println("Starting...");
        System.out.println("----------------------------------");
        beginReport();

        int maxLap = 100;
        for (int round = 0; round < maxLap; round++) {
            System.out.println("\nLap: " + round + " started");
            newLapReport(round);

            if (round % Utils.WEATHER_CYCLE == 0) {
                Weather.getInstance().update();
                for (Sensor sensor : sensors) {
                    sensor.newLap();
                }
            }

            for (Device device : devices) {
                device.newLap();
            }

            for (Things tool : tools) {
                tool.newLap();
            }

            Collections.shuffle(people);
            for (Person person : people) {
                person.newLap();
            }

            Collections.shuffle(pets);
            for (Animal pet : pets) {
                pet.newLap();
            }
        }
        configLog.closeLog();
        eventLog.closeLog();
        consumptionReport();
        consumptionLog.closeLog();
    }
}
