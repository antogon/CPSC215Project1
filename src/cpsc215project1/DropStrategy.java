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
public class DropStrategy implements VerbStrategy {

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        AdventureTarget out = null;
        for (AdventureTarget t : e.getPlayerInventory()) {
            if (((Target) t).getVisible()) {
                if (t == c.getDirectObject()) {
                    out = t;

                }
            }

        }
        e.removeFromPlayerInventory(out);
        ((Item) out).setUsable(false);
        e.getPlayerLocation().addLocalTarget(out);
        w.println("You drop " + out.getShortDescription() + ".");
    }
}
