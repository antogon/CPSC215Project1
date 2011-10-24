package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;

/**
 *
 * @author amalvagomes
 */
public class TakeStrategy implements VerbStrategy {

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        AdventureTarget out = null;

        for (AdventureTarget t : e.getPlayerLocation().getLocalTargets()) {
            if (((Target) t).getVisible()) {
                if (t == c.getDirectObject()) {
                    out = t;
                    
                    
                }
            }
        }
        e.addToPlayerInventory(out);
        ((Item) out).setUsable(true);
        e.getPlayerLocation().removeLocalTarget(out);
        w.println("You pick up " + out.getShortDescription());
    }
}
