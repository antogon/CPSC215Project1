package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.VerbStrategy;

/**
 * <p><code>DeathStrategy</code> is an implementation of the <code>VerbStrategy</code>
 *      interface.</p>
 */
public class DeathStrategy implements VerbStrategy {

    /**
     * <p>The <code>doCommand()</code> method handles calls, which are put forth
     *      when a caller wishes to die. When the strategy is called,
     *      it is already determined that this is the correct strategy to use, so
     *      a <code>DoNotUnderStandException</code> does not need to be thrown.</p>
     * @param c The command that an AdventureTarget is to process.
     * @param e The game engine, which this target may use to change the state
     *      of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *      text to the terminal if it must in order to process the command.
     * @author ApertureScience
     */
    public void doCommand(AdventureCommand c, AdventureEngine e, AdventureWindow w) {
        e.setPlayerLocation(new Location("DEAD","It appears that" +
                " you have been slain.  As the blood pools around your" +
                " head, you slip into sweet unconsciousness.", null));
    }
}
