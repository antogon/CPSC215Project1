package cpsc215project1;

/**
 *
 * @author amalvag
 */
import edu.clemson.cs.hamptos.adventure.*;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GameReader game = new GameReader (
                "/home/toiletplumber/NetBeansProjects/CPSC215Project1/src/cpsc21"
                + "5project1/savegame");
        
        AdventureLocation init = game.getInitialLocation();
	MyParser parse = new MyParser();
	AdventureWindow userInterface = 
     new AdventureWindow(init, parse);
    }
}
