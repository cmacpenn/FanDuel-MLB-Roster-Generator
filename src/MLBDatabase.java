import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
	private Connection connection; //

	// Constructor
	/**
	 * Constructor for the MLBDatabase
	 * 
	 * @param url                The link to the database.
	 * @param rawBatterGameData  The csv file with the raw batter game data.
	 * @param rawPitcherGameData The csv file with the raw pitcher game data.
	 * @param rawPlayerData      The csv file with the raw player data.
	 * @throws SQLException
	 * @throws IOException
	 */
	public MLBDatabase(String url, File rawBatterGameData, File rawPitcherGameData, File rawPlayerData)
			throws SQLException, IOException {

		// Initialize the in-memory database
		this.url = url;
		this.connection = DriverManager.getConnection(url);

		// Create tables
		String createPlayerTableStatement = "CREATE TABLE PLAYERS (FANDUELID INT NOT NULL PRIMARY KEY, NAME VARCHAR(100) NOT NULL)";
		String createBatterGameTableStatement = "CREATE TABLE BATTER_GAME_LOG (BATTER_PLAYER_GAME_ID BIGINT AUTO_INCREMENT PRIMARY KEY, FANDUELID INT NOT NULL, GAME_DATE DATE NOT NULL, SINGLES INT NOT NULL, DOUBLES INT NOT NULL, TRIPLES INT NOT NULL, HOME_RUNS INT NOT NULL, RUNS INT NOT NULL, RUNS_BATTED_IN INT NOT NULL, WALKS INT NOT NULL, HIT_BY_PITCH INT NOT NULL, STOLEN_BASE INT NOT NULL)";
		String createPitcherGameTableStatement = "CREATE TABLE PITCHER_GAME_LOG (PITCHER_PLAYER_GAME_ID BIGINT AUTO_INCREMENT PRIMARY KEY, FANDUELID INT NOT NULL, GAME_DATE DATE NOT NULL, WIN BOOLEAN NOT NULL, QUALITY_START BOOLEAN NOT NULL, STRIKE_OUTS INT NOT NULL, EARNED_RUNS INT NOT NULL, INNINGS_PITCHED DECIMAL(5.2) NOT NULL)";
		Statement stm = connection.createStatement();
		stm.execute(createPlayerTableStatement);
		stm.execute(createBatterGameTableStatement);
		stm.execute(createPitcherGameTableStatement);

		// Load data from the raw csv files
		String playerImportStatement = "INSERT INTO PLAYERS(FANDUELID, NAME) SELECT FANDUELID, NAME FROM T";
		String batterImportStatement = "INSERT INTO BATTER_GAME_LOG (FANDUELID, GAME_DATE, SINGLES, DOUBLES, TRIPLES, HOME_RUNS, RUNS, RUNS_BATTED_IN, WALKS, HIT_BY_PITCH, STOLEN_BASE) SELECT ID, PARSEDATETIME(GAMEDATE, 'yyyy-MM-dd'), X1B, X2B, X3B, HR, R, RBI, BB, HBP, SB FROM T";
		String pitcherImportStatement = "INSERT INTO PITCHER_GAME_LOG (FANDUELID, GAME_DATE, WIN, QUALITY_START, STRIKE_OUTS, EARNED_RUNS, INNINGS_PITCHED) SELECT FANDUELID, PARSEDATETIME(GAMEDATE, 'yyyy-MM-dd'), W, QS, SO, ER, IP FROM T";
		
		loadDataIntoDatabase(rawPlayerData, playerImportStatement);
		loadDataIntoDatabase(rawBatterGameData, batterImportStatement);
		loadDataIntoDatabase(rawPitcherGameData, pitcherImportStatement);
	}

	// Methods
	/**
	 * Reads in the csv data and populates the database.
	 * 
	 * @param rawPlayerData The csv file with the raw game data.
	 * @param importStatement The SQL string to insert the data from a temporary table created by csvread
	 * @throws SQLException
	 * @throws IOException
	 */
	private void loadDataIntoDatabase(File rawPlayerData, String importStatement) throws SQLException, IOException {
		String createTempStatement = "CREATE TEMPORARY TABLE T AS SELECT * FROM CSVREAD('" + rawPlayerData.getAbsolutePath() + "')";
		String dropTempStatement = "DROP TABLE T";
		Statement stm = connection.createStatement();
		stm.execute(createTempStatement);	
		stm.execute(importStatement);
		stm.execute(dropTempStatement);
	}

	/**
	 * Returns the MLB game history from the database for a given player.
	 * 
	 * @param id The FanDuel ID of the player whose MLB game history we want to retrieve.
	 * @return The player's game history.
	 * @throws SQLException 
	 */
	public PlayerHistory pullPlayerHistory(int id) throws SQLException {
		// Pull all games from games table
		Statement stm = connection.createStatement();
		String batterQuery = "SELECT * FROM BATTER_GAME_LOG WHERE FANDUELID = " + id;
		String pitcherQuery = "SELECT * FROM PITCHER_GAME_LOG WHERE FANDUELID = " + id;
		ResultSet batterResults = stm.executeQuery(batterQuery);
		ResultSet pitcherResults = stm.executeQuery(pitcherQuery);
		
		// Convert results into PlayerHistory object and return
		while(batterResults.next()) {
! So, problem here.  Create Game as an interface, and then do PitcherGame and BatterGame
! This will implement a higher concept, and otherwise we have the problem of double headers,
! where we have multiple games on the same day - how to match pitcher/batter stats?
		}
		while(pitcherResults.next()) {
			
		}
	}

	/**
	 * @return The connection to the database.
	 */
	public Connection getConnection() {
		return connection;
	}

}
