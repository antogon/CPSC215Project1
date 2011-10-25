package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;
import java.util.ArrayList;

/**
 * <p><code>LookStrategy</code> is an implementation of the<code>VerbStrategy</code>
 *      interface.</p>
 */
public class LookStrategy implements VerbStrategy {

    /**
     * <p>The <code>doCommand()</code> method handles calls, which are put forth
     *      when a caller wishes to look around the current {@link Location}.
     *      A description of the {@link Location} is printed to the screen,
     *      as well as a list of all the visible {@link Item}s in the 
     *      {@link Location}. When the strategy is called, it is already determined 
     *      that this is the correct strategy to use, so a 
     *      <code>DoNotUnderStandException</code> does not need to be thrown.</p>
     * @param c The command that needs to be processed.
     * @param e The game engine, which this target may use to change the state
     *      of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *      text to the terminal if it must in order to process the command.
     * @author ApertureScience
     */
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
