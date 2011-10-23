/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author amalvag
 */
public class Item extends Target {    
    
    public Item(String name, String desc, ArrayList<String> b, HashMap<String, 
            String> d, ArrayList<String> ialiases) {
            super(name,desc,b,d,ialiases);
    }

    public void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        String key = myDirectObjectCommands.get(c.getVerb());
        boolean canBe = myIndirectObjectCommands.contains(key);
        if(canBe && key.equals("examine")){
            new ExamineStrategy().doCommand(c,e,w);
        }
        else if(canBe && key.equals("take")){
            new TakeStrategy().doCommand(c,e,w);
        }
        else if(canBe && key.equals("drop")){
            new DropStrategy().doCommand(c,e,w);
        }
        else if(canBe && key.equals("damage")){
            new DamageStrategy().doCommand(c,e,w);
        }
    }

    public void doCommandWith(AdventureCommand c, AdventureEngine e, AdventureWindow w) throws DoNotUnderstandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
