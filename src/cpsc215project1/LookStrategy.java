package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;
import java.util.ArrayList;

/**
 *
 * @author amalvagomes
 */
public class LookStrategy implements VerbStrategy {

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        w.println(e.getPlayerLocation().getDescription());
        String output = "You can see ";
        int ndx = 0;
        int size = 0;
        ArrayList<AdventureTarget> visibleItems = new ArrayList<AdventureTarget>();

        for (AdventureTarget t : e.getPlayerLocation().getLocalTargets()) {
            if (((Target) t).getVisible()) {
                visibleItems.add((AdventureTarget) t);
            }
        }
        
        size = visibleItems.size();

        for (AdventureTarget t : visibleItems) {
            if (size > 2) {
                if (ndx < size - 1) {
                    output += t.getShortDescription() + ", ";
                    ndx++;
                } else {
                    output += "and " + t.getShortDescription() + ".";
                }
            } else if (size == 1) {
                output += t.getShortDescription() + ".";
            } else if (size == 2) {
                if (ndx == 0) {
                    output += t.getShortDescription() + " and ";
                    ndx++;
                } else {
                    output += t.getShortDescription() + ".";
                }
            }

        }
        w.println(output);

    }
}
