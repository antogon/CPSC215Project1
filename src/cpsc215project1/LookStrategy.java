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
public class LookStrategy implements VerbStrategy{

    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
		w.println(e.getPlayerLocation().getDescription());
		String output = "You can see ";
        for(AdventureTarget t : e.getPlayerLocation().getLocalTargets())
        {
                        if(((Target)t).getVisible())
                            output += t.getShortDescription() + ", ";
        }
        w.println(output);
    }
    
}
