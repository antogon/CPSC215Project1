package cpsc215project1;

import java.util.Set;
import edu.clemson.cs.hamptos.adventure.*;

public class Location implements AdventureLocation {

    private Set<AdventureTarget> myLocalTargets;
    private String myName;
    private String myDescription;

    public Location(String name, String desc) {
        myName = name;
        myDescription = desc;
    }

    public void addLocalTarget(AdventureTarget t) {
    }

    public boolean containsLocalTarget(AdventureTarget t) {
        throw new UnsupportedOperationException(
                "Operation not implemented");
    }

    public void doCommand(
            AdventureCommand c,
            AdventureEngine e,
            AdventureWindow w) {
    }

    public String getDescription() {
        return myDescription;
    }

    public Set<AdventureTarget> getLocalTargets() {
        throw new UnsupportedOperationException(
                "Operation not implemented");
    }

    public void removeLocalTarget(AdventureTarget t) {
    }
}
