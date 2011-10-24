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
public class ExamineStrategy implements VerbStrategy {

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {

        for (AdventureTarget t : e.getPlayerLocation().getLocalTargets()) {
            if (t.getShortDescription().equals(c.getDirectObject().getShortDescription())) {
                w.println(c.getDirectObject().getDescription());
            }
        }
    }
}
