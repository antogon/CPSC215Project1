package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureParser;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.NoSuchTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <p> A <code>MyParser</code> is a class that implements AdventureParser
 *      and parses in-game commands.</p>
 * @author ApertureScience
 */
public class MyParser implements AdventureParser {

    /**
     * <p>Parses the String input from the user as an AdventureCommand.</p>
     * 
     * @param command The string to be parsed.  Must be non-null.
     * @param e The AdventureEngine with which the command will be interpreted. Must be initialized.
     * @return A non-null AdventureCommand object that represented the parsed String.
     * @throws NoSuchTargetException when the target mentioned in the String
     *  is nonexistent in the context of the game.
     */
    public AdventureCommand parse(String command, AdventureEngine e)
            throws NoSuchTargetException {
        AdventureCommand returnCommand = null;  //Finished Command
        int i = 0;                     //index used to determine parts of speech
        Scanner parser = new Scanner(command);//scanner that parses input string
        HashSet<AdventureTarget> myTargets;     //list of targets from engine
        String verb = null;             //holds the verb to be put into command
        AdventureTarget dirObj = null;  //direct object to be put into command 
        String dirObjInv = null;        //invocation of direct object
        AdventureTarget indObj = null;  //indirect object to be put into command
        String indObjInv = null;        //invocation of inderect object

        //getting list of targets from local location
        //TO HAMPTON:  The following statement happens only because
        //we broke your encapsulation.
        myTargets =
                (HashSet<AdventureTarget>) ((HashSet<AdventureTarget>) e.getPlayerLocation().getLocalTargets()).clone();
        myTargets.addAll(e.getPlayerInventory());

        //variable used to hold Adventure targets while being assigned in loop
        AdventureTarget tempVar = null;

        //takes fist word in incomming command string as the output command verb
        verb = parser.next();

        //driver to parse input command string
        while (parser.hasNext()) {

            //iterator over all of the targets in the current location
            Iterator listItr = myTargets.iterator();

            //gets the next word in the input command string
            String tempParse = parser.next();

            //makes sure the parsed word is compared to ever local target
            for (int j = 0; j < myTargets.size(); j++) {
                tempVar = (AdventureTarget) listItr.next();

                //used to ignore unneccisary words
                if (tempVar.canBeReferredToAs(tempParse)) {

                    //assigns the direct object
                    if (i == 0) {
                        dirObj = tempVar;
                        dirObjInv = tempParse;
                        j = myTargets.size();
                    }
                    //assigns the indirect object
                    if (i == 1) {
                        indObj = tempVar;
                        indObjInv = tempParse;
                    }
                    i++;
                }

            }
            //if there are words after the verb and they are unrecognized then
            //this throws a NoSuchTargetException
            if (i == 0 && !parser.hasNext()) {
                throw new NoSuchTargetException(tempParse);
            }
        }

        //three if statements that determine what kind of adventure command to
        //create
        if (i == 0) {
            returnCommand = new AdventureCommand(verb);
        }
        if (i == 1) {
            returnCommand = new AdventureCommand(verb, dirObj, dirObjInv);
        }
        if (i == 2) {
            returnCommand = new AdventureCommand(verb, dirObj, dirObjInv,
                    indObj, indObjInv);
        }
        return returnCommand;
    }
}
