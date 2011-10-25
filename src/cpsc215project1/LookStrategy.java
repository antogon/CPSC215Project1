package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;
import java.util.ArrayList;

/**
 *
 * @author Aperture Science
 */
public class LookStrategy implements VerbStrategy {

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        w.println(e.getPlayerLocation().getDescription());
        String output = "You can see:\n";
        int ndx = 0;
        int size = 0;
        ArrayList<AdventureTarget> visibleItems = new ArrayList<AdventureTarget>();

        for (AdventureTarget t : e.getPlayerLocation().getLocalTargets()) {
            if (((Target) t).getVisible()) {
                visibleItems.add((AdventureTarget) t);
            }
        }
        
        size = visibleItems.size();

        for (AdventureTarget t: visibleItems){
            output += "\t - " + t.getShortDescription() + "\n";
        }
        w.println(output);

    }
}
