package cpsc215project1;

/**
 *
 * @author amalvag
 */
import edu.clemson.cs.hamptos.adventure.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            String filename = JOptionPane.showInputDialog("Enter path to XML game file:");
            GameReader game = new GameReader(filename);
            AdventureLocation init = game.getInitialLocation();
            MyParser parse = new MyParser();
            AdventureWindow userInterface =
                    new AdventureWindow(init, parse);
        } catch (NullPointerException npe) {
            System.err.println("There was an error with the file path.");
        } catch (IllegalArgumentException iae) {
            System.err.println("There was an error with the file path.");
        }
    }
}

