package Entities.Classes.Electronics.Utils;

import Entities.Classes.Things;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Manual {
    public Manual(Things device){
        System.out.println("Searching documentation for device: " + device.getName());
        System.out.println("Documentation found!");
        System.out.println("Downloading manual from the website. Please wait...");
    }
}
