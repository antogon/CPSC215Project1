package cpsc215project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import nu.xom.*;
import edu.clemson.cs.hamptos.adventure.*;

public class GameReader {

    private File myGame;
    private Document myDocument;
    private Builder myBuilder;
    private Element myCurrentElement;
    private AdventureLocation myLocation;
    private ArrayList<Location> myWorld;

    /*
     * <p>Contructs a new GameReader that reads the game passed in.</p>
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

    /*
     * <p>Gets the initial location after parsing the XML file.
     * This method must not be called before a call to buildGame is made.</p>
     *
     * @return Initial location of game.
     */
    public AdventureLocation getInitialLocation() {
        return myLocation;
    }

    /*
     * <p>Parses the file given in the constructor as a game.</p>
     *
     * @param curr The element given as the current element for recursion.
     * @param p The parent of the current element.
     */
    private void buildGame(Element curr, Element p) {
        String eleName = curr.getLocalName();
        if (eleName.equals("Location")) {
            ArrayList<String> b = new ArrayList<String>();
            Location a = new Location(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), myWorld);
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
                d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX) + 2, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            startNDX = cB.lastIndexOf(',') + 2;
            d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX) + 2, cB.length()));
            cB = curr.getAttributeValue("aliases");
            ArrayList<String> ali = new ArrayList<String>();
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                ali.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                ali.add(cB);
            }
            Item a = new Item(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), b, d, ali);
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
                d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX) + 2, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            startNDX = cB.lastIndexOf(',') + 2;
            d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX) + 2, cB.length()));
            cB = curr.getAttributeValue("aliases");
            ArrayList<String> ali = new ArrayList<String>();
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                ali.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                ali.add(cB);
            }
            Person a = new Person(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), b, d, ali);
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
                d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX) + 2, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            startNDX = cB.lastIndexOf(',') + 2;
            d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX) + 2, cB.length()));
            cB = curr.getAttributeValue("aliases");
            ArrayList<String> ali = new ArrayList<String>();
            for (startNDX = 0; cB != null && cB.indexOf(',', startNDX) > 0;) {
                ali.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
                startNDX = cB.indexOf(',', startNDX) + 2;
            }
            if (startNDX == 0) {
                ali.add(cB);
            }
            Portal a = new Portal(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), b, d, ali);
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
