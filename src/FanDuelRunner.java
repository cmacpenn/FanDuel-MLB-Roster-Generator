/**
 * Main executable for the FanDuel suggestion program.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
import javax.swing.SwingUtilities;

public class FanDuelRunner {

	public static void main(String[] args) {
		// TODO Check if the database has been initialized.  Create if not.
		
		// TODO Open the GUI
		SwingUtilities.invokeLater(new FanDuelGUI());
		// TODO Convert input spreadsheet into a roster of players
		
		// TODO Retrieve historical player information from the database
		
		// TODO Calculate best roster suggestion(s)
		
		// TODO Display roster results to user

	}

}
