/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import java.util.ArrayList;
import java.util.HashMap;
import edu.clemson.cs.hamptos.adventure.*;

/**
 *
 * @author amalvag
 */
public class Portal extends Target {

    private boolean isOpen = false;

    public Portal(String name, String desc, ArrayList<String> b, HashMap<String, String> d, ArrayList<String> a) {
        super(name, desc, b, d, a);
    }

    public void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        String key = myDirectObjectCommands.get(c.getVerb());
        boolean canBe = myIndirectObjectCommands.contains(key);

        if (canBe && key.equals("examine")) {
            new ExamineStrategy().doCommand(c, e, w);
        }

        else if (canBe && key.equals("open"))
        {
            w.println("The " + myName + " is now open.");
            isOpen = true;
            this.updateDescription("The door is open.");
        }

        else if (canBe && key.equals("close")) {
            w.println("The " + myName + " is now closed.");
            isOpen = false;
            this.updateDescription("The door is closed.");
        }
<<<<<<< HEAD
        else if(!myDirectObjectCommands.containsKey(c.getVerb())){
            throw new DoNotUnderstandException(c);
        }
        else
        {
            for(Location l : ((Location)e.getPlayerLocation()).getWorld())
            {
                if(l.getName().equals(key))
                {
                    if(isOpen)
                    {
=======

        else if (!myDirectObjectCommands.containsKey(c.getVerb())){
            throw new DoNotUnderstandException(c);
        }

        else {
            for (Location l : ((Location) e.getPlayerLocation()).getWorld()) {
                if (l.getName().equals(key)) {
                    if (isOpen) {
>>>>>>> 624cb3bc4396949f37e9d2ac6d294a4f357a9f22
                        e.setPlayerLocation(l);
                    } else if (!isOpen){
                        w.println("The " + myName + " is closed.  You try to enter it"
                                + " and fail dismally, hitting your head.");
                    }
                    break;
                }  
            }

        }
    }

    public void doCommandWith(AdventureCommand c, AdventureEngine e, AdventureWindow w) throws DoNotUnderstandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
