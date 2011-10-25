package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;

/**
 * <p><code>InventoryStrategy</code> is an implementation of the <code>VerbStrategy</code>
 *      interface.</p>
 */
public class InventoryStrategy implements VerbStrategy {

    /**
     * <p>The <code>doCommand()</code> method handles calls, which are put forth
     *     when the user wishes to check the current inventory. The current
     *      inventory is then printed to the screen. When the strategy is called,
     *      it is already determined that this is the correct strategy to use, so
     *      a <code>DoNotUnderStandException</code> does not need to be thrown.</p>
     * @param c The command that needs to be processed.
     * @param e The game engine, which this target may use to change the state
     *      of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *      text to the terminal if it must in order to process the command.
     * @author ApertureScience
     */
    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        w.println("Player Inventory");
        w.println("=================");

        for (AdventureTarget t : e.getPlayerInventory()) {
            w.println(t.getShortDescription());
        }
    }
}
