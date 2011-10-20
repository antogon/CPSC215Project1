/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc215project1;

import edu.clemson.cs.hamptos.adventure.*;

/**
 *
 * @author amalvag
 */
public class Target implements AdventureTarget {

    public Target(String name, String desc) {
    }

    public boolean canBeReferredToAs(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getShortDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
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
