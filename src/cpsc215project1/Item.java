/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>An <code>Item</code> is a type of {@link Target}. Used as the direct
 * object of a command, an <code>Item</code> may be:<ul>
 * <li>examined (give more information to the player)</li>
 * <li>taken (picked up by the player for use)</li>
 * <li>dropped (removing from player's possession)</li>
 * <li>damaged (rendered unusable)</li></ul>
 * Used as an indirect object, <code>Item</code>s have an effect on the
 * command's direct object by invoking anything in the XML file's
 * <code>uses</code> attribute.</p>
 */
public class Item extends Target {

	/**
	 * Default constructor for the <code>Item</code> class.
	 * @param name given name, or short description, of the <code>Item</code>
	 * @param desc a longer description of the <code>Item</code>
	 * @param indirectObjCmds list of verbs you can do <b>with</b> the
	 * <code>Item</code> instance.
	 * @param directObjCmds Hash map of commands the <code>Item</code> can
	 * execute. The key is the <code>verb</code> parsed through {@link MyParser}.
	 * In the case of intransitive verbs (i.e. "open door"), the value is a
	 * command that can be understood by {@link doCommandTo}, such that
	 * the indirect object is implied to be itself. Otherwise, the value is
	 * another <code>Item</code> being dealt with (i.e. entering a door would
	 * change the location).
	 * @param aliases list of words that could refer to this <code>Item</code>
	 * instance. <code>name</code> needs not be included in this list.
	 */

    public Item(String name, String desc, ArrayList<String> indirectObjCmds,
			HashMap<String, String> directObjCmds, ArrayList<String> aliases) {
            super(name,desc,indirectObjCmds,directObjCmds,aliases);
    }

    public void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        String key = myDirectObjectCommands.get(c.getVerb());
        boolean canBe = myIndirectObjectCommands.contains(key);

        if(canBe && key.equals("examine")){
            new ExamineStrategy().doCommand(c,e,w);
        }
        else if(canBe && key.equals("take")){
            new TakeStrategy().doCommand(c,e,w);

        }
        else if(canBe && key.equals("drop")){
            new DropStrategy().doCommand(c,e,w);
        }
        else if(canBe && key.equals("damage")){
      
            new DamageStrategy().doCommand(c,e,w);
        }
        else if(myUseListDO.containsKey(c.getVerb()))
        {
            String[] effects = myUseListDO.get(c.getVerb());
            w.println(effects[0]);
            ((Location) e.getPlayerLocation()).updateDescription(effects[1]);
        }
        else{
            throw new DoNotUnderstandException(c);
        }
    }

    public void doCommandWith(AdventureCommand c, AdventureEngine e, AdventureWindow w) throws DoNotUnderstandException {
        if(c.getVerb().equals("use")
                && myIndirectObjectCommands.contains(c.getVerb())
                && ((Item) c.getDirectObject()).getUsable()
                && myUseListIO.containsKey(((Target)c.getDirectObject()).getName()))
        {
            String[] effects = myUseListIO.get(((Target)c.getDirectObject()).getName());
            w.println(effects[0]);
            ((Item) c.getIndirectObject()).setUsable(true);
            ((Item) c.getDirectObject()).setUsable(false);
            e.removeFromPlayerInventory(c.getDirectObject());
            ((Location) e.getPlayerLocation()).updateDescription(effects[1]);
        }
        else {
            w.println("You might want to pick that up first.\n");
        }
    }
}
