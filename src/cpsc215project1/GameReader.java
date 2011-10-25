package cpsc215project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import nu.xom.*;
import edu.clemson.cs.hamptos.adventure.*;

/**
 * <p>A <code>GameReader</code> takes in an XML game file and parses it accordingly.  Results in a fully
 *      built game ready to be fed into the AdventureEngine.
 * @author ApertureScience
 */
public class GameReader {

    private File myGame;
    private Document myDocument;
    private Builder myBuilder;
    private Element myCurrentElement;
    private AdventureLocation myLocation;
    private ArrayList<Location> myWorld;

    /**
     * <p>Contructs a new GameReader that reads the game passed in.</p>
     * <p>XML File Structure:<br/><br/>
     *      A game file contains an opening and closing <code>Game</code> tag.
     *      For each location in the game there are <code>Location</code> tags.
     *      The <code>Location</code> tags have attributes <code>name</code> (a one word name),
     *      <code>desc</code> (a long description of the location), and a possible <code>isInit</code>.  As long as
     *      <code>isInit</code> is defined, that <code>Location</code> will be
     *      the initial location of the player when the game is started.<br/><br/>
     *
     *      Within a <code>Location</code> tag, there are <code>Item</code>,
     *      <code>Person</code>, and <code>Portal</code> tags, each representing
     *      their respective objects in the game.  All of the aforementioned tags
     *      must include the following tags with proper syntax:<br/><br/>
     *
     *      &nbsp;&nbsp;<code>name</code> - a one word name for the object.<br/><br/>
     *      &nbsp;&nbsp;<code>desc</code> - a long description for the object.<br/><br/>
     *      &nbsp;&nbsp;<code>canBe</code> - a comma delimited list of strategies applicable to the object.<br/>
     *      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>canBe="examine, drop, take"</code><br/><br/>
     *      &nbsp;&nbsp;<code>canDo</code> - a mapping of verbs to strategies applicable in an indirect object context.<br/>
     *      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>canDo="examine=>examine, kick=>drop, drop=>drop"</code><br/><br/>
     *      &nbsp;&nbsp;<code>aliases</code> - a comma delimited list of other possible names for the object.<br/><br/><br/>
     *
     *      Each object may also define the following tags as needed for more complex game logic:<br/><br/>
     *
     *      &nbsp;&nbsp;<code>usesIO</code> - logic for an indirect object usage of the object.<br/>
     *      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>usesIO="This message will be printed on the screen when the
     *                      object is used.|OBJECTNAME|This message is appended to the
     *                      Location's description."</code><br/><br/>
     *      &nbsp;&nbsp;<code>usesDO</code> - logic for a direct object usage of the object.<br/>
     *      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>usesDO="This message will be printed on the screen when the
     *                      object is used.|TRIGGERVERB|This message is appended to the
     *                      Location's description."</code><br/>
     * </p>
     * @param filePath the path to the XML game file
     */
    public GameReader(String filePath) {
        try {
            myGame = new File(filePath);
            myBuilder = new Builder();
            myDocument = myBuilder.build(myGame);
            myCurrentElement = myDocument.getRootElement();
            myWorld = new ArrayList<Location>();
            buildGame(myCurrentElement, null);
        } catch (FileNotFoundException e) {
            System.err.println("The file requested was not found.");
        } catch (ParsingException e) {
            System.err.println("The file was not parsed correctly.");
        } catch (IOException e) {
            System.err.println("There was an input/output exception.");
        }
    }

    /**
     * <p>Gets the initial location after parsing the XML file.
     * This method must not be called before a call to buildGame is made.</p>
     *
     * @return Initial location of game.
     */
    public AdventureLocation getInitialLocation() {
        return myLocation;
    }

    /**
     * <p>Parses the file given in the constructor as a game.</p>
     *
     * @param curr The element given as the current element for recursion.
     * @param p The parent of the current element.
     */
    private void buildGame(Element curr, Element p) {
        String eleName = curr.getLocalName();
        if (eleName.equals("Location")) {
            ArrayList<String> b = new ArrayList<String>();
            Location a = new Location(
                    curr.getAttributeValue("name"),
                    curr.getAttributeValue("desc"), myWorld);

            if (curr.getAttributeValue("isInit") != null) {
                myLocation = a;
            }
            myWorld.add(a);
        }
        if (eleName.equals("Item")) {
            ArrayList<String> b = new ArrayList<String>();
            String cB = curr.getAttributeValue("canBe");
            int startNDX;
            for (startNDX = 0; cB.indexOf(',', startNDX) > 0;) {
                b.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                b.add(cB);
            } else {
                startNDX = cB.lastIndexOf(',') + 2;
                b.add(cB.substring(startNDX, cB.length()));
            }
            HashMap<String, String> d = new HashMap<String, String>();
            cB = curr.getAttributeValue("canDo");
            for (startNDX = 0; cB.indexOf(',', startNDX) > 0;) {
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.length()));
            } else {
                startNDX = cB.lastIndexOf(',') + 2;
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.length()));
            }
            cB = curr.getAttributeValue("aliases");
            ArrayList<String> ali = new ArrayList<String>();
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                ali.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                ali.add(cB);
            }
                Item a = new Item(
                    curr.getAttributeValue("name"),
                    curr.getAttributeValue("desc"),
                    b, d, ali);

            cB = curr.getAttributeValue("visibility");
            if (cB != null && cB.equals("false")) {
                a.setVisible(false);
            }
            cB = curr.getAttributeValue("usesIO");
            //uses must be as: [message one]|[object]|[scene effect];
            for (startNDX = 0; cB != null && cB.indexOf(';', startNDX) > 0;) {
                String[] val = new String[2];
                val[0] = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                String key = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                val[1] = cB.substring(startNDX, cB.indexOf(";", startNDX));
                startNDX = cB.indexOf(";", startNDX) + 2;
                a.addUseIO(key, val);
            }
            cB = curr.getAttributeValue("usesDO");
            //uses must be as: [message one]|[verb]|[scene effect];
            for (startNDX = 0; cB != null && cB.indexOf(';', startNDX) > 0;) {
                String[] val = new String[2];
                val[0] = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                String key = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                val[1] = cB.substring(startNDX, cB.indexOf(";", startNDX));
                startNDX = cB.indexOf(";", startNDX) + 2;
                a.addUseDO(key, val);
            }
            String pa = p.getAttributeValue("name");
            for (Location l : myWorld) {
                if (l.getName().equals(pa)) {
                    l.addLocalTarget(a);
                }
            }
        }
        if (eleName.equals("Person")) {
            ArrayList<String> b = new ArrayList<String>();
            String cB = curr.getAttributeValue("canBe");
            int startNDX;
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                b.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                b.add(cB);
            } else {
                startNDX = cB.lastIndexOf(',') + 2;
                b.add(cB.substring(startNDX, cB.length()));
            }
            HashMap<String, String> d = new HashMap<String, String>();
            cB = curr.getAttributeValue("canDo");
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.length()));
            } else {
                startNDX = cB.lastIndexOf(',') + 2;
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.length()));
            }
            cB = curr.getAttributeValue("aliases");
            ArrayList<String> ali = new ArrayList<String>();
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                ali.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                ali.add(cB);
            }
            Person a = new Person(
                    curr.getAttributeValue("name"),
                    curr.getAttributeValue("desc"),
                    b, d, ali);
            cB = curr.getAttributeValue("visibility");
            if (cB != null && cB.equals("false")) {
                a.setVisible(false);
            }
            cB = curr.getAttributeValue("usesIO");
            //uses must be as: [message one]|[object]|[scene effect];
            for (startNDX = 0; cB != null && cB.indexOf(';', startNDX) > 0;) {
                String[] val = new String[2];
                val[0] = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                String key = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                val[1] = cB.substring(startNDX, cB.indexOf(";", startNDX));
                startNDX = cB.indexOf(";", startNDX) + 2;
                a.addUseIO(key, val);
            }
            cB = curr.getAttributeValue("usesDO");
            //uses must be as: [message one]|[verb]|[scene effect];
            for (startNDX = 0; cB != null && cB.indexOf(';', startNDX) > 0;) {
                String[] val = new String[2];
                val[0] = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                String key = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                val[1] = cB.substring(startNDX, cB.indexOf(";", startNDX));
                startNDX = cB.indexOf(";", startNDX) + 2;
                a.addUseDO(key, val);
            }
            String pa = p.getAttributeValue("name");
            for (Location l : myWorld) {
                if (l.getName().equals(pa)) {
                    l.addLocalTarget(a);
                }
            }
        }
        if (eleName.equals("Portal")) {
            ArrayList<String> b = new ArrayList<String>();
            String cB = curr.getAttributeValue("canBe");
            int startNDX;
            for (startNDX = 0; cB.indexOf(',', startNDX) > 0;) {
                b.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                b.add(cB);
            } else {
                startNDX = cB.lastIndexOf(',') + 2;
                b.add(cB.substring(startNDX, cB.length()));
            }
            HashMap<String, String> d = new HashMap<String, String>();
            cB = curr.getAttributeValue("canDo");
            for (startNDX = 0; cB.indexOf(',', startNDX) > 0;) {
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.length()));
            } else {
                startNDX = cB.lastIndexOf(',') + 2;
                d.put(cB.substring(
                        startNDX,
                        cB.indexOf('=', startNDX)),
                        cB.substring(
                        cB.indexOf('=', startNDX) + 2,
                        cB.length()));
            }
            cB = curr.getAttributeValue("aliases");
            ArrayList<String> ali = new ArrayList<String>();
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                ali.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                ali.add(cB);
            }
            Portal a = new Portal(
                    curr.getAttributeValue("name"),
                    curr.getAttributeValue("desc"),
                    b, d, ali);
            cB = curr.getAttributeValue("visibility");
            if (cB != null && cB.equals("false")) {
                a.setVisible(false);
            }
            cB = curr.getAttributeValue("usesIO");
            //uses must be as: [message one]|[object]|[scene effect];
            for (startNDX = 0; cB != null && cB.indexOf(';', startNDX) > 0;) {
                String[] val = new String[2];
                val[0] = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                String key = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                val[1] = cB.substring(startNDX, cB.indexOf(";", startNDX));
                startNDX = cB.indexOf(";", startNDX) + 2;
                a.addUseIO(key, val);
            }
            cB = curr.getAttributeValue("usesDO");
            //uses must be as: [message one]|[verb]|[scene effect];
            for (startNDX = 0; cB != null && cB.indexOf(';', startNDX) > 0;) {
                String[] val = new String[2];
                val[0] = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                String key = cB.substring(startNDX, cB.indexOf("|", startNDX));
                startNDX = cB.indexOf("|", startNDX) + 1;
                val[1] = cB.substring(startNDX, cB.indexOf(";", startNDX));
                startNDX = cB.indexOf(";", startNDX) + 2;
                a.addUseDO(key, val);
            }
            String pa = p.getAttributeValue("name");
            for (Location l : myWorld) {
                if (l.getName().equals(pa)) {
                    l.addLocalTarget(a);
                }
            }
        }
        Elements child = curr.getChildElements();
        for (int i = 0; i < child.size(); i++) {
            buildGame(child.get(i), curr);
        }
    }
}