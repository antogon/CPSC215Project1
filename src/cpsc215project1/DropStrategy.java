package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;

/**
 * <code>DropStrategy</code> is an implementation of the <code>VerbStrategy</code>
 *      interface.  
 */
public class DropStrategy implements VerbStrategy {

    /**
     * <p>The <code>doCommand()</code> method handles calls, which are put forth
     *      when a caller wishes to drop an {@link Item} in the current 
     *      {@link Location}. The name of the {@link Item} dropped is printed
     *      to the screen. When the strategy is called, it is already determined
     *      that this is the correct strategy to use, so a <code>DoNotUnderStandException</code> 
     *      does not need to be thrown.</p> 
     * @param c The command to process.
     * @param e The game engine, which this target may use to change the state
     *      of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *      text to the terminal if it must in order to process the command.
     * @author ApertureScience
     */
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
