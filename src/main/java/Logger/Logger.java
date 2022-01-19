package logger;

import controller.Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Mukan Atazhanov
 * Created on 13-Jan-22
 */

public class Logger {
    private FileOutputStream logg;

    public Logger(String name) {
        try {
            logg = new FileOutputStream(name, true);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeToLog(String text) {
        text += System.lineSeparator();
        try {
            logg.write(text.getBytes());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeLog() {
        try {
            logg.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
