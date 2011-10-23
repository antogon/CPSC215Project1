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
                int size = e.getPlayerLocation().getLocalTargets().size();
                int ndx = 0;

        for(AdventureTarget t : e.getPlayerLocation().getLocalTargets())
        {
            if (size > 2){
               if (ndx < size - 1){
                   output += t.getShortDescription() + ", ";
                   ndx++;
               }
               else{
               output += "and " + t.getShortDescription() + ".";
               }
            }
            else if(size == 1){
                output += t.getShortDescription() + ".";
            }
            else{
                if(ndx == 0){
                   output += t.getShortDescription() + " and ";
                   ndx++;
                }
                else{
                    output += t.getShortDescription() + ".";
                }
            }
        }
        w.println(output);
    }

}
