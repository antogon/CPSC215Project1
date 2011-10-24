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
    public static void main(String[] args) throws FileNotFoundException {

        //String filename = JOptionPane.showInputDialog("Enter XML game file:");
        //GameReader game = new GameReader(filename);

        GameReader game = new GameReader(args[0]);
        AdventureLocation init = game.getInitialLocation();
        MyParser parse = new MyParser();
        AdventureWindow userInterface =
                new AdventureWindow(init, parse);
    }
}
