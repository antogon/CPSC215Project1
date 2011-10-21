package cpsc215project1;

import java.util.Set;
import java.util.HashSet;
import edu.clemson.cs.hamptos.adventure.*;

public class Location implements AdventureLocation {

    private Set<AdventureTarget> myLocalTargets;
    private String myName;
    private String myDescription;

    public Location(String name, String desc) {
        myName = name;
        myDescription = desc;
		myLocalTargets = new HashSet<AdventureTarget>();
    }

    public void addLocalTarget(AdventureTarget t) {
		myLocalTargets.add(t);
    }

    public boolean containsLocalTarget(AdventureTarget t) {
		return myLocalTargets.contains(t);
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
		return myLocalTargets;
    }

    public void removeLocalTarget(AdventureTarget t) {
		myLocalTargets.remove(t);
    }

	public String getName()
	{
		return myName;
	}
}
