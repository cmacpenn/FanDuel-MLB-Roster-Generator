/**
 * Main executable for the FanDuel suggestion program.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class FanDuelRunner {

	public static void main(String[] args) {
		// Open the GUI
		SwingUtilities.invokeLater(new FanDuelGUI());
		
		// Load the database
		// Does this need to go inside the GUI somehow?
		String url = "jdbc:h2:mem:";
		File rawBatterGameData = new File("database/database_batting_game_logs_after_2015.csv");
		File rawPitcherGameData = new File("database/database_pitching_game_logs_after_2015.csv");
		File rawPlayerData = new File("database/database_players.csv");
		try {
			MLBDatabase mlbDb = new MLBDatabase(url, rawBatterGameData, rawPitcherGameData, rawPlayerData);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		// TODO Convert input spreadsheet into a roster of players
		
		// TODO Retrieve historical player information from the database
		
		// TODO Calculate best roster suggestion(s)
		
		// TODO Display roster results to user

	}

}
