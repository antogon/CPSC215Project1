/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David Cohen
 */
public abstract class Target implements AdventureTarget {

	protected String myName;
	protected String myDesc;
	protected ArrayList<String> myIndirectObjectCommands;
		// things you can do WITH the Target instance
	protected HashMap<String, String> myDirectObjectCommands;
		// things you can do TO the Target instance
	protected ArrayList<String> myAliases;
        		// list of words that could also refer to this instance
        protected String myUpdatedDescription;

        protected boolean isUsable = true;
        protected boolean isVisible = true;

	/**
	 * Default constructor for the <code>Target</code> class.
	 * @param name given name, or short description, of the <code>Target</code>
	 * @param desc a longer description of the <code>Target</code>
	 * @param indirectObjCmds list of verbs you can do <b>with</b> the
	 * <code>Target</code> instance.
	 * @param directObjCmds !?!?!?!?
	 * @param aliases list of words that could refer to this <code>Target</code>
	 * instance. <code>name</code> needs not be included in this list.
	 */
	public Target(String name, String desc, ArrayList<String> indirectObjCmds,
			HashMap<String, String> directObjCmds, ArrayList<String> aliases) {

		myName = name;
		myDesc = desc;
                myUpdatedDescription = desc;
		myIndirectObjectCommands = indirectObjCmds;
		myDirectObjectCommands = directObjCmds;
		myAliases = aliases;

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
		return ( text.equals(myName) || myAliases.contains(text) );
	}

	/**
	 * Returns the <code>Target</code>'s name.
	 * @return <code>name</code> of the object as supplied in the constructor.
	 */
	public String getShortDescription() {
		return "a " + myName;
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

	public abstract void doCommandWith(AdventureCommand c,
			AdventureEngine e,
			AdventureWindow w) throws DoNotUnderstandException;
/*	public abstract void doCommandWith(AdventureCommand c,
			AdventureEngine e,
			AdventureWindow w) throws DoNotUnderstandException {
		// throw new UnsupportedOperationException("Not supported yet.");
		if(!myIndirectObjectCommands.contains(c.getVerb()) )
			throw new DoNotUnderstandException(c);

	}
*/
        public void updateDescription(String iDiscription) {
            myUpdatedDescription = iDiscription;
        }

        public void appendDescription(String iDescription){
            myUpdatedDescription = myDesc +"\n" + iDescription;
        }

        public void setUsable(boolean a)
        {
            isUsable = a;
        }

        public boolean getVisible()
        {
            return isVisible;
        }

        public void setVisible(boolean a)
        {
            isVisible = a;
        }

}

