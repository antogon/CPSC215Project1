/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import java.util.ArrayList;
import java.util.HashMap;
import edu.clemson.cs.hamptos.adventure.*;

/**
 *
 * @author amalvag
 */
public class Item implements AdventureTarget {

	private String name;
	private String desc;
	private ArrayList<String> canBe;
	private HashMap<String, String> canDo;

    public Item(String name, String desc, ArrayList<String> b, HashMap<String, String> d) {
		this.name = name;
		this.desc = desc;
		canBe = b;
		canDo = d;
    }

    public boolean canBeReferredToAs(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getShortDescription() {
		return name;
    }

    public String getDescription() {
		return desc;
    }

    public void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doCommandWith(AdventureCommand c, AdventureEngine e, AdventureWindow w) throws DoNotUnderstandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
