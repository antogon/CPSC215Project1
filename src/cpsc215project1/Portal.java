/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import java.util.ArrayList;
import java.util.HashMap;
import edu.clemson.cs.hamptos.adventure.*;

/**
 *	A <code>portal</code> is a
 * @author David Alexander Cohen, II.
 */
public class Portal extends Target{

<<<<<<< HEAD
	private String name;
	private String desc;
	private ArrayList<String> canBe;
	private HashMap<String, String> canDo;

    public Portal(String name, String desc, ArrayList<String> indirectObjCmds,
			HashMap<String, String> directObjCmds, ArrayList<String> aliases) {
		super(name, desc, indirectObjCmds, directObjCmds, aliases);
    }

    public boolean canBeReferredToAs(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getShortDescription() {
		return name;
    }

    public String getDescription() {
		return desc;
=======
    public Portal(String name, String desc, ArrayList<String> b, HashMap<String, String> d, ArrayList<String> a) {
		super(name, desc, b, d, a);
>>>>>>> 53987fd5d6218a71ab574c3a974c74d0f009f699
    }

    public void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet.");
=======
        String key = myDirectObjectCommands.get(c.getVerb());
        boolean canBe = myIndirectObjectCommands.contains(key);
        if(canBe && key.equals("examine")){
            new ExamineStrategy().doCommand(c,e,w);
        }
        else
        {
            for(Location l : ((Location)e.getPlayerLocation()).getWorld())
            {
                if(l.getName().equals(key))
                {
                    e.setPlayerLocation(l);
                    break;
                }
            }
        }
>>>>>>> 53987fd5d6218a71ab574c3a974c74d0f009f699
    }

    public void doCommandWith(AdventureCommand c, AdventureEngine e, AdventureWindow w) throws DoNotUnderstandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
