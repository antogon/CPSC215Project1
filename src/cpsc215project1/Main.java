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
        /*
         *  TEST GameReader
         */
        GameReader game = new GameReader(
                "/home/amalvag/NetBeansProjects/CPSC215Project1/src/cpsc215project1/savegame");
        /*
         *  END GameReader test
         */
	AdventureLocation init = game.getInitialLocation();
	MyParser parse = new MyParser();
	AdventureWindow w = new AdventureWindow(init, parse);
    }
}
