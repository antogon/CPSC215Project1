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
        if(c.getVerb().equals("examine") && myDirectObjectCommands.get(c.getVerb()).equals(c.getVerb())){
            new ExamineStrategy().doCommand(c,e,w);
        }
        
    }

    public void doCommandWith(AdventureCommand c, AdventureEngine e, AdventureWindow w) throws DoNotUnderstandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
