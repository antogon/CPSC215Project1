package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.AdventureCommand;
import edu.clemson.cs.hamptos.adventure.AdventureEngine;
import edu.clemson.cs.hamptos.adventure.AdventureTarget;
import edu.clemson.cs.hamptos.adventure.AdventureWindow;
import edu.clemson.cs.hamptos.adventure.DoNotUnderstandException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A {@code Target} is a generic implementation of {@link AdventureTarget}.
 * @author David Alexander Cohen, II.
 */
public abstract class Target implements AdventureTarget {

	/**
	 * <p>A uniquely identifiable, descriptive name that is visible to the
	 * player through {@link #getShortDescription}</p>
	 */
	protected String myName;

	/**<p>
	 * A longer, more complete description of this object. Never changed after
	 * set by the constructor; instead, {@link #myUpdatedDescription} is used as
	 * the description printed by {@link #getDescription()}.
	 * </p>
	 */
	protected final String myDesc;

	/**<p>
	 * Hash map of commands the {@code Target} can
     * execute. The key is the {@code verb} parsed through {@link MyParser}.
     * In the case of intransitive verbs (i.e. "open door"), the value is a
     * command that can be understood by {@link #doCommandTo}, such that
     * the indirect object is implied to be itself. Otherwise, the value is
     * another {@code Target} being dealt with (i.e. entering a door would
     * change the location).
	 * </p>
	 */
	protected ArrayList<String> myIndirectObjectCommands;

	/**<p>
	 * List of verbs that this {@code Target} can do as an indirect object,
	 * i.e. commands that you can do <b>with</b> this object.
	 * </p>
	 */
	protected HashMap<String, String> myDirectObjectCommands;

	/**<p>
	 * List of words that could refer to this {@code Target}
     * instance. {@code name} needs not be included in this list.
	 * </p>
	 */
	protected ArrayList<String> myAliases;

	/**
	 * The description that is returned by {@link #getDescription() }. It can
	 * be updated, preserving the original description
	 */
	protected String myUpdatedDescription;

	/**<p>
	 * Usability flag, for which {@code true} makes the item usable by the
	 * player. By default, this is set to {@code false}.
	 * </p>
	 */
	protected boolean isUsable = false;

	/**<p>
	 * Visibility flag, for which {@code true} signifies that this is
	 * visible to the player. By default, this flag is set to {@code true}.
	 * </p>
	 */
	protected boolean isVisible = true;

	/**<p>
	 * {@link HashMap} used in the implementation of {@link #doCommandWith}.
	 * The {@code key} represents the name of another {@code Target}, and
	 * the {@code value} is a 2-element array of {@code String}s, in which the
	 * 0th element represents the {@code String} to be printed out when called,
	 * and the 1st element represents a {@code String} to be appended to the
	 * description of the {@code Target} of the command's Direct Object.
	 * </p>
	 */
	protected HashMap<String, String[]> myUseListIO;

	/**<p>
	 *{@link HashMap} used in implementation of {@link #doCommandTo}.
	 * The {@code key} represents the verb of a command that can be
	 * executed upon this {@code Target}, and
	 * the {@code value} is a 2-element array of {@code String}s, in which the
	 * 0th element represents the {@code String} to be printed out when called,
	 * and the 1st element represents the {@code String} to be appended to the
	 * description of this {@code Target}.
	 * </p>
	 */
	protected HashMap<String, String[]> myUseListDO;


    /**
     * Default constructor for the {@code Target} class.
     * @param name given name, or short description, of the {@code Target}
     * @param desc a longer description of the {@code Target}
     * @param indirectObjCmds list of verbs you can do <b>with</b> the
     * {@code Target} instance.
     * @param directObjCmds Hash map of commands the {@code Target} can
     * execute. The key is the {@code verb} parsed through {@link MyParser}.
     * In the case of intransitive verbs (i.e. "open door"), the value is a
     * command that can be understood by {@link #doCommandTo}, such that
     * the indirect object is implied to be itself. Otherwise, the value is
     * another {@code Target} being dealt with (i.e. entering a door would
     * change the location).
     * @param aliases list of words that could refer to this {@code Target}
     * instance. {@code name} needs not be included in this list.
     */
    public Target(String name, String desc, ArrayList<String> indirectObjCmds,
            HashMap<String, String> directObjCmds, ArrayList<String> aliases) {

        myName = name;
        myDesc = desc;
        myUpdatedDescription = desc;
        myIndirectObjectCommands = indirectObjCmds;
        myDirectObjectCommands = directObjCmds;
        myAliases = aliases;
        myUseListIO = new HashMap<String, String[]>();
        myUseListDO = new HashMap<String, String[]>();

    }

    /** <p>Returns <code>true</code> if <code>text</code> is either the <code>
     * name</code> of the Target or any element in ArrayList <code>aliases
     * </code>, both of which are supplied to the constructor.</p>
     *
     * @param text The text given by the user to refer to some object.
     *    Guaranteed to be non-<code>null</code>.
     * @return <code>true</code> if this <code>target</code> may be referred to by the given
     *    text.
	 */
	public boolean canBeReferredToAs(String text) {
        return (text.equals(myName) || myAliases.contains(text));
    }


    /**
     * <p>Returns a short description of the object.  A word or three to remind
     * the player what it is. It's based on this {@code Target}'s {@code myName} field.</p>
     *
     * @return The target's short description.
     */
    public String getShortDescription() {
        return "a " + myName;
    }

    /**
     * Returns the <code>Target</code>'s name.
     * @return <code>name</code> of the object as supplied in the constructor.
     */
    public String getName(){
        return myName;
    }

    /**
     * Returns the <code>Target</code>'s description.
     * @return the description of the object as supplied in the constructor as
     * <code>desc</code>.
     */
    public String getDescription() {
        return myUpdatedDescription;
    }

    /**
     * <p>Requests that this target attempt to process the given command as the
     * direct object of the command.  So, if the command given were
     * <code>"take key"</code>, the game engine would call this method to ask
     * that the key deal with being taken.  If this target does not understand
     * the command it's being asked to process, it will throw a
     * <code>DoNotUnderstandException</code>.</p>
     *
     * <p>Because if there were an indirect object, this command would have been
     * routed there, it is guaranteed that calls to <code>c</code>'s
     * <code>getIndirectObject()</code> and
     * <code>getIndirecObjectInvocation()</code> methods will return
     * <code>null</code>.</p>
     *
     * @param c The command the target is to process.
     * @param e The game engine, which this target may use to change the state
     *    of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *    text to the terminal if it must in order to process the command.
     *
     * @throws DoNotUnderstandException If this target does not know how to
     *    process the given command.
     */
    public abstract void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException;

    /**
     * <p>Requests that this target attempt to process the given command as the
     * indirect object of the command.  So, if the command given were
     * <code>"unlock door with key"</code>, the game engine would call this
     * method to ask that the key deal with unlocking something.  If this target
     * does not understand the command it's being asked to process, it will
     * throw a <code>DoNotUnderstandException</code>.</p>
     *
     * @param c The command the target is to process.
     * @param e The game engine, which this target may use to change the state
     *    of the game appropriately if it must in order to process the command.
     * @param w The input/output window, which this target may use to print
     *    text to the terminal if it must in order to process the command.
     *
     * @throws DoNotUnderstandException If this target does not know how to
     *    process the given command.
     */

    public abstract void doCommandWith(AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException;

  /**
	 * <p>Updates the {@code Target}'s description, accessible with {@link
	 * #getDescription}. The original description passed into the constructor
	 * is kept such that it can always be restored, but subsequent updates
	 * overwrite all other updates</p>
	 *
	 * @param iDiscription the new description to be stored by this instance.
	 */
	public void updateDescription(String iDiscription) {
        myUpdatedDescription = iDiscription;
    }

	/**
	 * <p>Appends a newline and then the argument to the <b>original</b>
	 * description provided in the constructor. This method will overwrite the
	 * description. Only the original description is stored for recall.</p>
	 *
	 * @param iDescription the description that should be appended to the
	 * original description passed in from the constructor
	 */
    public void appendDescription(String iDescription) {
        myUpdatedDescription = myDesc + "\n" + iDescription;
    }

	/**
	 * <p>Flags the {@code Target}'s usability flag to the sole argument
	 * (in which {@code true} means "usable"). Usability is checked with
	 * {@link #getUsable}. The default value of a newly constructed
	 * {@code Target} is {@code false}, or "unusable."</p>
	 * @param newUsabilityFlag the new usability flag to be changed
	 */
    public void setUsable(boolean newUsabilityFlag) {
        isUsable = newUsabilityFlag;
    }
/**
	 * <p>Returns {@code true} if the target has been set to "usable" via
	 * {@link #setUsable}. The default value of this flag is
	 * {@code false}.</p>
	 * @return {@code true} if the {@code Target} is flagged as
	 * usable.
	 */
    public boolean getUsable() {
        return isUsable;
    }

	/**
	 * <p>Returns {@code true} if this target has been set to "visible" via
	 * {@link #setVisible}. By default, a {@code Target}'s
	 * visibility flag is set to {@code true}.</p>
	 * @return {@code true} if the {@code Target} is flagged as
	 * visible.
	 */
    public boolean getVisible() {
        return isVisible;
    }
	/**<p>
	 * Sets the visibility flag of this {@code Target} to the sole argument,
	 * in which {@code true} signifies "visible." By default, the
	 * visibility flag of a newly constructed {@code Target} is set
	 * to {@code true}.
	 * </p>
	 * @param visibilityFlag the new visibility flag to be set
	 */
    public void setVisible(boolean visibilityFlag) {
        isVisible = visibilityFlag;
    }

	/**<p>
	 * Adds another use to this {@code Target} as an Indirect Object by adding
	 * an entry to the protected field {@link #myUseListIO} with the specified
	 * parameters.
	 * </p>
	 * @param targetName the {@code myName}  field of the {@code Target} with
	 * which this {@code Target} should interact.
	 * @param effectArray 2-element array of {@code String}s, in which the
	 * 0th element represents the {@code String} to be printed out when called,
	 * and the 1st element represents a {@code String} to be appended to the
	 * description of the {@code Target} of the command's Direct Object.
	 */
	public void addUseIO(String targetName, String[] effectArray) {
        myUseListIO.put(targetName, effectArray);
    }

	/**<p>
	 * Adds another use to this {@code Target} as a Direct Object by adding an
	 * entry to protected field {@link #myUseListDO} with the specified
	 * parameters.
	 *</p>
	 * @param objectCommand the verb of a command that can be executed upon this
	 * {@code Target}
	 * @param effectArray 2-element array of {@code String}s, in which the
	 * 0th element represents the {@code String} to be printed out when called,
	 * and the 1st element represents the {@code String} to be appended to the
	 * description of this {@code Target}.
	 */
	public void addUseDO(String objectCommand, String[] effectArray) {
        myUseListDO.put(objectCommand, effectArray);
    }


}
