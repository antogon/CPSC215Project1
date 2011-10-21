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
public class Target implements AdventureTarget {

	private String myName;
	private String myDesc;
	private ArrayList<String> myCanBe;
	private HashMap<String, String> myCanDo;

	public Target(String name, String desc, ArrayList<String> cb,
			HashMap<String, String> cd) {

		this.myName = name;
		this.myDesc = desc;
		this.myCanBe = cb;
		this.myCanDo = cd;

	}

	/** ==== FROM SUPERCLASS ====
     * <p>Returns <code>true</code> if the given <code>text</code> is a valid
     * name for this target.  Used by the <code>Parser</code> to map textual
     * names to game objects.  As an example, a target representing a stone
     * gargoyle might be able to be referred to as a "gargoyle" or a "statue".
     * </p>
     *
     * @param text The text given by the user to refer to some object.
     *    Guaranteed to be non-null.
     *
     * @return <code>true</code> if this target may be referred to by the given
     *    text.
     */
    public boolean canBeReferredToAs(String text) {
		// TODO: Add some logic to iterate through a list
		return (text.equals(myName));
	}

	public String getShortDescription() {
		return myName;
	}

	public String getDescription() {
		return myDesc;
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
	public void doCommandTo(
			AdventureCommand c,
			AdventureEngine e,
			AdventureWindow w) throws DoNotUnderstandException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void doCommandWith(AdventureCommand c,
			AdventureEngine e,
			AdventureWindow w) throws DoNotUnderstandException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
