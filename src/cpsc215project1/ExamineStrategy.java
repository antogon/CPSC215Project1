package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;
import java.util.ArrayList;

/**
 *
 * @author toiletplumber
 */
public class ExamineStrategy implements VerbStrategy {

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        
        ArrayList<AdventureTarget> listy = new ArrayList<AdventureTarget>();
        listy.addAll(e.getPlayerLocation().getLocalTargets());
        listy.addAll(e.getPlayerInventory());
        
        for (AdventureTarget t : listy) {
            if (t.getShortDescription().equals(c.getDirectObject().getShortDescription()) && ((Target)t).getVisible())  {
                w.println(c.getDirectObject().getDescription());
            }
        }
    }
}
