import controller.Controller;
import entities.classes.creatures.Animal;
import entities.classes.creatures.Person;
import entities.classes.electronics.*;
import entities.classes.electronics.utils.Consumption;
import entities.classes.house.Room;
import entities.classes.sensor.Sensor;
import entities.classes.Things;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mukan Atazhanov
 * Created on 16-Jan-22
 */

public class Config2 {
    public static void main(String[] args) {
        Controller controller = Controller.getInstance();

        List<Room> rooms = new ArrayList<>();

        Room livingRoom = new Room("living room",1);
        Room kitchen = new Room("kitchen",1);
        Room bathroom = new Room("bathroom",1);
        Room bathroom2 = new Room("bathroom2",2);
        Room bedRoom1 = new Room("bedroom1",2);
        Room bedRoom2 = new Room("bedroom2",2);
        Room bedRoom3 = new Room("bedroom3",2);
        Room bedRoom4 = new Room("bedroom4",2);
        Room bedRoom5 = new Room("bedroom5", 3);
        Room cherdak = new Room("Cherdak", 3);
        Room garage = new Room("garage", 1);
        Room gymMaster = new Room("?GYM", -1);

        rooms.add(livingRoom);
        rooms.add(kitchen);
        rooms.add(bathroom);
        rooms.add(bathroom2);
        rooms.add(bedRoom1);
        rooms.add(bedRoom2);
        rooms.add(bedRoom3);
        rooms.add(bedRoom4);
        rooms.add(bedRoom5);
        rooms.add(cherdak);
        rooms.add(garage);
        rooms.add(gymMaster);


        List<Sensor> sensors = new ArrayList<>();
        for (Room room : rooms) {
            sensors.add(new Sensor(room));
        }

        List<Person> people = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();

        Person person1 = new Person("Mukan", cherdak, 27);
        Person person2 = new Person("Stas", gymMaster, 28);
        Person person3 = new Person("Dimas", kitchen, 29);
        Person person4 = new Person("Artyom", bathroom, 30);

        Animal dog1 = new Animal("Cat", "Tomas", livingRoom);
        Animal dog2 = new Animal("Dog", "Jerry", livingRoom);
        Animal dog3 = new Animal("Cow", "Milka", livingRoom);
        Animal dog4 = new Animal("Goat", "Gosha", livingRoom);

        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);

        animals.add(dog1);
        animals.add(dog2);
        animals.add(dog3);
        animals.add(dog4);


        List<Device> devices= new ArrayList<>();

        devices.add(new Computer("MSI", bedRoom1, new Consumption(50, 1, 0, 0, 0, 0)));
        devices.add(new Computer("DELL", bedRoom2, new Consumption(50, 1, 0, 0, 0, 0)));
        devices.add(new Computer("HP", bedRoom3, new Consumption(50, 1, 0, 0, 0, 0)));
        devices.add(new Computer("LENOVO", bedRoom4, new Consumption(50, 1, 0, 0, 0, 0)));
        devices.add(new Fridge("Fridge", kitchen, new Consumption(120, 120, 0, 0, 0, 0)));
        devices.add(new Microwave("Wave", kitchen, new Consumption(100, 1, 10, 0, 0, 0)));
        devices.add(new Oven("Oven", kitchen, new Consumption(120, 150, 0, 0, 0, 0)));
        devices.add(new Play_Station("PS5", livingRoom, new Consumption(70, 1, 0, 0, 0, 0)));
        devices.add(new TV("TV", livingRoom, new Consumption(100, 10, 0, 0, 0, 0)));
        devices.add(new Vacuum_Cleaner("Cleaner", bedRoom2, new Consumption(120, 10, 0, 0, 0, 0)));
        devices.add(new Washing_Machine("Washer1", bathroom, new Consumption(150, 150, 0, 0, 0, 0)));
        devices.add(new Washing_Machine("Washer2", bathroom2, new Consumption(150, 150, 0, 0, 0, 0)));
        devices.add(new TV("TV", bedRoom1, new Consumption(100, 10, 0, 0, 0, 0)));
        devices.add(new TV("TV", bedRoom2, new Consumption(100, 10, 0, 0, 0, 0)));
        devices.add(new TV("TV", bedRoom3, new Consumption(100, 10, 0, 0, 0, 0)));
        devices.add(new TV("TV", bedRoom4, new Consumption(100, 10, 0, 0, 0, 0)));

        for(Device d : devices){
            people.forEach(d::addObserver);
        }

        //set usability of all devices
        people.forEach((p) -> {
            p.useDevice(devices);
        });

        List<Things> allTools = new ArrayList<>();

        allTools.add(new Things("Ski 1", garage));
        allTools.add(new Things("Bench", garage));
        allTools.add(new Things("Ski 2", garage));
        allTools.add(new Things("Car", garage));
        allTools.add(new Things("Arcade", garage));

        for (Things tool : allTools) {
            people.forEach(tool::addObserver);
        }

        people.forEach((p) -> {
            p.useThings(allTools);
        });

        controller.addRooms(rooms);
        controller.addSensors(sensors);
        controller.addPeople(people);
        controller.addDevices(devices);
        controller.addThings(allTools);
        controller.addPets(animals);
        controller.Runner();
    }
}
