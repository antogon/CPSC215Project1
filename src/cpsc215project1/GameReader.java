package cpsc215project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import nu.xom.*;
import edu.clemson.cs.hamptos.adventure.*;

public class GameReader
{
	private File myGame;
	private Document myDocument;
	private Builder myBuilder;
	private Element myCurrentElement;
	private AdventureLocation myLocation;
	private ArrayList<Location> myWorld;

	public GameReader(String filePath)
	{
		try
		{
			myGame = new File(filePath);
			myBuilder = new Builder();
			myDocument = myBuilder.build(myGame);
			myCurrentElement = myDocument.getRootElement();
			myWorld = new ArrayList<Location>();
			buildGame(myCurrentElement, null);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file requested was not found.");
		}
                catch(ParsingException e)
                {
                        System.err.println("The file was not parsed correctly.");
                }
                catch(IOException e)
                {
                        System.err.println("There was an input/output exception.");
                }
	}

	public AdventureLocation getInitialLocation()
	{
		return myLocation;
	}

	private void buildGame(Element curr, Element p)
	{
		String eleName = curr.getLocalName();
		if(eleName.equals("Location"))
		{
			ArrayList<String> b = new ArrayList<String>();
			Location a = new Location(curr.getAttributeValue("name"), curr.getAttributeValue("desc"));
			if(curr.getAttributeValue("isInit")!=null)
			{
				myLocation = a;
			}
			myWorld.add(a);
		}
		if(eleName.equals("Item"))
		{
			ArrayList<String> b = new ArrayList<String>();
			String cB = curr.getAttributeValue("canBe");
			for(int startNDX = 0; cB.indexOf(',', startNDX)>0; startNDX=startNDX)
			{
				b.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
				startNDX=cB.indexOf(',', startNDX)+2;
			}
			HashMap<String, String> d = new HashMap<String, String>();
			cB = curr.getAttributeValue("canDo");
			for(int startNDX = 0; cB.indexOf(',', startNDX)>0; startNDX=startNDX)
			{
				d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX)+2,cB.indexOf(',',startNDX)));
				startNDX=cB.indexOf(',', startNDX)+2;
			}
			int startNDX=cB.lastIndexOf(',')+2;
			d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX)+2,cB.length()));
			Item a = new Item(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), b, d);
			String pa = p.getAttributeValue("name");
			for(Location l : myWorld)
			{
				if(l.getName().equals(pa))
				{
					l.addLocalTarget(a);
				}
			}
		}
		if(eleName.equals("Person"))
		{
			ArrayList<String> b = new ArrayList<String>();
			String cB = curr.getAttributeValue("canBe");
			for(int startNDX = 0; cB.indexOf(',', startNDX)>0; startNDX=startNDX)
			{
				b.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
				startNDX=cB.indexOf(',', startNDX)+2;
			}
			HashMap<String, String> d = new HashMap<String, String>();
			cB = curr.getAttributeValue("canDo");
			for(int startNDX = 0; cB.indexOf(',', startNDX)>0; startNDX=startNDX)
			{
				d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX)+2,cB.indexOf(',',startNDX)));
				startNDX=cB.indexOf(',', startNDX)+2;
			}
			int startNDX=cB.lastIndexOf(',')+2;
			d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX)+2,cB.length()));
			Person a = new Person(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), b, d);
			String pa = p.getAttributeValue("name");
			for(Location l : myWorld)
			{
				if(l.getName().equals(pa))
				{
					l.addLocalTarget(a);
				}
			}
		}
		if(eleName.equals("Portal"))
		{
			ArrayList<String> b = new ArrayList<String>();
			String cB = curr.getAttributeValue("canBe");
			for(int startNDX = 0; cB.indexOf(',', startNDX)>0; startNDX=startNDX)
			{
				b.add(cB.substring(startNDX, cB.indexOf(',', startNDX)));
				startNDX=cB.indexOf(',', startNDX)+2;
			}
			HashMap<String, String> d = new HashMap<String, String>();
			cB = curr.getAttributeValue("canDo");
			for(int startNDX = 0; cB.indexOf(',', startNDX)>0; startNDX=startNDX)
			{
				d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX)+2,cB.indexOf(',',startNDX)));
				startNDX=cB.indexOf(',', startNDX)+2;
			}
			int startNDX=cB.lastIndexOf(',')+2;
			d.put(cB.substring(startNDX, cB.indexOf('=', startNDX)), cB.substring(cB.indexOf('=', startNDX)+2,cB.length()));
			Portal a = new Portal(curr.getAttributeValue("name"), curr.getAttributeValue("desc"), b, d);
			String pa = p.getAttributeValue("name");
			for(Location l : myWorld)
			{
				if(l.getName().equals(pa))
				{
					l.addLocalTarget(a);
				}
			}
		}
		Elements child = curr.getChildElements();
		for(int i = 0; i<child.size(); i++)
		{
			buildGame(child.get(i), curr);
		}
	}
}
