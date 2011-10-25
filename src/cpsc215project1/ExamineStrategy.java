package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;
import java.util.ArrayList;

/**
 * <p><code>ExamineStrategy</code> is an implementation of the <code>VerbStrategy</code>
 *      interface.</p>
 */
public class ExamineStrategy implements VerbStrategy {

    /**
     * <p>The <code>doCommand()</code> method handles calls, which are put forth
     *      when a caller wishes to get a closer look at an {@link Item} in the current 
     *      {@link Location}. When the strategy is called, it is already 
     *      determined that this is the correct strategy to use, so a 
     *      <code>DoNotUnderStandException</code> does not need to be thrown. 
     *      A description of the {@link Location} is printed to the screen.</p>
     * @param c The command that needs to be processed.
     * @param e The game engine, which this target may use to change the state
     *      of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *      text to the terminal if it must in order to process the command.
     * @author ApertureScience
     */
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
