package cpsc215project1;

/**
 * The main driver for the game, from which all awesomeness spawns.
 * @author ApertureScience
 */
import edu.clemson.cs.hamptos.adventure.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Main {

/**
 * The main, which asks the player for a gamefile to load (giving the
 * option for a default savegame.)
 */
public static void main(String[] args) {
	boolean errorEncountered = false;
	File xmlFile = null;
    try {
		// ask whether to laod default or custom gamefile
		Object[] possibleValues = { "Open Default Game", "Load Custom GameFile", "Close"};
		Object selectedValue = JOptionPane.showInputDialog(null, "Choose which game to load.", "Load Game",
			JOptionPane.INFORMATION_MESSAGE,
			null, possibleValues, possibleValues[0]);
		// default
		if(selectedValue == possibleValues[0]){
			xmlFile = new File("src/cpsc215project1/savegame");
		}
		// custom
		else if(selectedValue == possibleValues[1]){
			JFileChooser chooser = new JFileChooser();
				JFrame frame = new JFrame("applicationFrame");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				chooser.setDialogTitle("Choose an XML game file to play!");
				int returnVal = chooser.showOpenDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
					xmlFile = chooser.getSelectedFile();

			}
			else{
				frame.dispose();
			}
		}
		// start loading game here
		if(xmlFile != null){
			GameReader game = new GameReader(xmlFile);
			AdventureLocation init = game.getInitialLocation();
			MyParser parse = new MyParser();
			AdventureWindow userInterface = new AdventureWindow(init, parse);
		}
	}catch(FileNotFoundException fnfe){
		// do stuff
	}catch (NullPointerException npe) {
		System.err.println("There was an error with the file path.");
	} catch (IllegalArgumentException iae) {
		System.err.println("There was an error with the file path.");
	}
}
}

