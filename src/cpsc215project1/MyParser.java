/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureParser;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.NoSuchTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author toiletplumber
 */
public class MyParser implements AdventureParser {

    public AdventureCommand parse(String command, AdventureEngine e) throws NoSuchTargetException {
        AdventureCommand returnCommand = null;
        int i = 0;
        Scanner parser = new Scanner(command);
        ArrayList<AdventureTarget> myTargets;
        String verb = null;
        AdventureTarget dirObj = null;
        String dirObjInv = null;
        AdventureTarget indObj = null;
        String indObjInv = null;

        myTargets = 
          (ArrayList<AdventureTarget>) e.getPlayerLocation().getLocalTargets();
        
        Iterator listItr = myTargets.iterator();

        AdventureTarget tempVar;
        while (parser.hasNext()) {

            if ((tempVar = (AdventureTarget) listItr.next()).canBeReferredToAs(parser.next())) {

                if (i == 0) {
                    verb = parser.next();
                }
                if (i == 1) {
                    dirObj = tempVar;
                    dirObjInv = parser.next();
                }
                if (i == 2) {
                    indObj = tempVar;
                    indObjInv = parser.next();
                }
                i++;
            }
        }

        if (i == 1) {
            returnCommand = new AdventureCommand(verb);
        }
        if (i == 2) {
            returnCommand = new AdventureCommand(verb,dirObj,dirObjInv);
        }
        if (i == 3) {
            returnCommand = new AdventureCommand(verb,dirObj,dirObjInv,indObj, indObjInv);
        }

        return returnCommand;
    
    }
}
