package cpsc215project1;

import java.util.Set;
import java.util.HashSet;
import edu.clemson.cs.hamptos.adventure.*;
import java.util.ArrayList;

/**
 * <p>A Location is an implementation of AdventureLocation that represents a single
 * location in-game.</p>
 * @author ApertureScience
 */
public class Location implements AdventureLocation {

    private Set<AdventureTarget> myLocalTargets;
    private String myName;
    private String myDescription;
    private String myUpdatedDescription;
    private ArrayList<Location> myWorld;

    /**
     * <p>Constructs a new Location with the given initial attributes.</p>
     * @param name The String representation of this Location's name. Must be non-null.
     * @param desc The String representation of this Location's description. Must be non-null.
     * @param w A reference to the world with respect to the game in the form of an ArrayList.
     */
    public Location(String name, String desc, ArrayList<Location> w) {
        myName = name;
        myUpdatedDescription = desc;
        myDescription = desc;
        myLocalTargets = new HashSet<AdventureTarget>();
        myWorld = w;
    }

    /**
     * <p>Adds a target to this Location's collection of targets.</p>
     * @param t The AdventureTarget to be added.
     */
    public void addLocalTarget(AdventureTarget t) {
        myLocalTargets.add(t);
    }

    /**
     * <p>Checks to see if this Location holds an AdventureTarget.</p>
     * @param t The target to be checked.
     * @return Whether or not this Location contains t.
     */
    public boolean containsLocalTarget(AdventureTarget t) {
        return myLocalTargets.contains(t);
    }

    /**
     * <p>Requests that this location attempt to process the given command.
     * So, if the command given were
     * <code>"look"</code>, the game engine would call this method to ask
     * that the current location deal with being looked at.  If this location does not understand
     * the command it's being asked to process, it will throw a
     * <code>DoNotUnderstandException</code>.</p>
     *
     * <p>Because if there were an indirect object, this command would have been
     * routed there, it is guaranteed that calls to <code>c</code>'s
     * <code>getIndirectObject()</code> and
     * <code>getIndirecObjectInvocation()</code> methods will return
     * <code>null</code>.</p>
     *
     * @param c The command the location is to process.
     * @param e The game engine, which this location may use to change the state
     *    of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this location may use to print
     *    text to the terminal if it must in order to process the command.
     *
     * @throws DoNotUnderstandException If this location does not know how to
     *    process the given command.
     */
    public void doCommand(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        if (c.getVerb().equals("look")) {
            new LookStrategy().doCommand(c, e, w);
        }
        else if (c.getVerb().equals("inventory")) {
            new InventoryStrategy().doCommand(c, e, w);
        }
        else { throw new DoNotUnderstandException(c); }
    }

    /**
     * <p>Returns the updated description of this location if, in fact, it has been updated.
     * Otherwise, returns the original description of this location as described in the XML
     * gamefile.</p>
     * @return The description of this location.
     */
    public String getDescription() {
        return myUpdatedDescription;
    }

    public void updateDescription(String iDiscription) {
        myUpdatedDescription = myDescription + "\n" + iDiscription;
    }

    public Set<AdventureTarget> getLocalTargets() {
        return myLocalTargets;
    }

    public void removeLocalTarget(AdventureTarget t) {
        myLocalTargets.remove(t);
    }

    public String getName() {
        return myName;
    }

    public ArrayList<Location> getWorld() {
        return (ArrayList<Location>) (myWorld.clone());
    }
}
