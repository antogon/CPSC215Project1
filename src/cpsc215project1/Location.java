package cpsc215project1;

import java.util.Set;
import java.util.HashSet;
import edu.clemson.cs.hamptos.adventure.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Location implements AdventureLocation {

    private Set<AdventureTarget> myLocalTargets;
    private String myName;
    private String myDescription;
    private String myUpdatedDescription;

    private ArrayList<String> vList = new ArrayList<String>
            (Arrays.asList("die", "answer","pickup"));
    
    public Location(String name, String desc) {
        myName = name;
        myUpdatedDescription = desc;
        myDescription = desc;
	myLocalTargets = new HashSet<AdventureTarget>();
    }

    public ArrayList<String> getVList(){
        return vList;
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
            AdventureWindow w) throws DoNotUnderstandException {   
        if(c.getVerb().equals("look")){
            new LookStrategy().doCommand(c,e,w);
        }
        if(c.getVerb().equals("inventory")){
            new InventoryStrategy().doCommand(c,e,w);
        }
    }

    public String getDescription() {
        return myUpdatedDescription;
    }
    
    public void updateDescription(String iDiscription) {
        myUpdatedDescription = myDescription + "\n" + iDiscription;
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
