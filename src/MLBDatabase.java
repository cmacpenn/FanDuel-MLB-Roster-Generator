import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for interacting with the H2 MLB database. The database will have two
 * tables: game, which will hold the results by game and player, and player,
 * which is a master table of all of the players.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class MLBDatabase {

	// Variables
	private String url; // "jdbc:h2:mem:" for an in-memory database

	// Constructor
	/**
	 * Constructor for the MLBDatabase
	 * 
	 * @param url           The link to the database.
	 * @param rawGameData   The csv file with the raw game data.
	 * @param rawPlayerData The csv file with the raw player data.
	 */
	public MLBDatabase(String url, File rawGameData, File rawPlayerData) {
		// TODO Initialize the in-memory database and create tables.
		// TODO Load data from the raw csv files
	}

	// Methods
	/**
	 * Reads in the csv player data and populates the database.
	 * 
	 * @param rawPlayerData The csv file with the raw game data.
	 */
	private void loadPlayerDataIntoDatabase(File rawPlayerData) {
		// TODO Implement. Will probably need to pass the connection as a parameter.
	}

	/**
	 * Reads in the csv game data and populates the database.
	 * 
	 * @param rawGameData The csv file with the raw game data.
	 */
	private void loadGameDataIntoDatabase(File rawGameData) {
		// TODO Implement. Will probably need to pass the connection as a parameter.
	}

	/**
	 * Returns the MLB game history from the database for a given player.
	 * 
	 * @param player The player whose MLB game history we want to retrieve.
	 * @return The player's game history.
	 */
	public PlayerHistory getPlayerHistory(Player player) {
		// TODO Find match on player table
		// TODO Pull all games from games table
		// TODO Convert results into PlayerHistory object and return
	}

// Helper code from the tutorial at http://zetcode.com/java/h2database/.  Will be modified to fit this program.
	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * try (var con = DriverManager.getConnection(url); var stm =
	 * con.createStatement(); var rs = stm.executeQuery("SELECT 1+1")) {
	 * 
	 * if (rs.next()) {
	 * 
	 * System.out.println(rs.getInt(1)); }
	 * 
	 * } catch (SQLException ex) {
	 * 
	 * var lgr = Logger.getLogger(MLBDatabase.class.getName());
	 * lgr.log(Level.SEVERE, ex.getMessage(), ex); } }
	 */

}
