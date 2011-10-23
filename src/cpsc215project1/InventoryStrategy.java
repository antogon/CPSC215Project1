/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;

/**
 *
 * @author toiletplumber
 */
public class InventoryStrategy implements VerbStrategy{

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        w.println("Player Inventory");
        w.println("******************************");
        
        for(AdventureTarget t : e.getPlayerInventory()){
            w.println(t.getShortDescription());
        }
    }
}
