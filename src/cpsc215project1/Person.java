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
public class Person extends Target {

    public Person(String name, String desc, ArrayList<String> b, HashMap<String, String> d, ArrayList<String> a) {
        super(name, desc, b, d, a);
    }

    public void doCommandTo(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        String key = myDirectObjectCommands.get(c.getVerb());
        boolean canBe = myIndirectObjectCommands.contains(key);
        if (canBe && key.equals("examine")) {
            new ExamineStrategy().doCommand(c, e, w);
        }
    }

    public void doCommandWith(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) throws DoNotUnderstandException {
        if (c.getVerb().equals("use")
                && myIndirectObjectCommands.contains(c.getVerb())
                && ((Target) c.getDirectObject()).getUsable()
                && myUseListIO.containsKey(
                ((Target) c.getDirectObject()).getName())) {
            String[] effects = myUseListIO.get(
                    ((Target) c.getDirectObject()).getName());
            w.println(effects[0]);
            ((Target) c.getIndirectObject()).setUsable(true);
            ((Target) c.getDirectObject()).setUsable(false);
            e.removeFromPlayerInventory(c.getDirectObject());
            ((Location) e.getPlayerLocation()).updateDescription(effects[1]);
            if (myDirectObjectCommands.containsKey("use")) {
                String key = myDirectObjectCommands.get("use");
                boolean canBe = myIndirectObjectCommands.contains(key);

                if (canBe && key.equals("examine")) {
                    new ExamineStrategy().doCommand(c, e, w);
                } else if (canBe && key.equals("take")) {
                    new TakeStrategy().doCommand(c, e, w);
                } else if (canBe && key.equals("drop")) {
                    new DropStrategy().doCommand(c, e, w);
                } else if (canBe && key.equals("damage")) {
                    new DamageStrategy().doCommand(c, e, w);
                }
            }
        } else {
            w.println("You might want to pick that up first.\n");
        }
    }
}
