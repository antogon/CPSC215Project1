package cpsc215project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import nu.xom.*;

public class GameReader
{
	private File myGame;
	private Document myDocument;
	private Builder myBuilder;

	public GameReader(String filePath)
	{
		try
		{
			myGame = new File(filePath);
			myBuilder = new Builder();
			myDocument = myBuilder.build(myGame);
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
	
	public String getString()
	{	
		return myDocument.toString() + myDocument.getRootElement().toString();
	}
}
