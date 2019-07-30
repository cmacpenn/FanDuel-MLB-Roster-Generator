import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InputPlayersTest {
	
	static MLBDatabase db;
	static File inputFile = new File("input/FanDuel-MLB-2019-07-22-37097-players-list.csv");
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String url = "jdbc:h2:mem:";
		File rawBatterGameData = new File("database/database_batting_game_logs_after_2015.csv");
		File rawPitcherGameData = new File("database/database_pitching_game_logs_after_2015.csv");
		File rawPlayerData = new File("database/database_players.csv");
		try {
			db = new MLBDatabase(url, rawBatterGameData, rawPitcherGameData, rawPlayerData);
		} catch (SQLException e) {
			fail("Failed to create database with the correct tables. Exception: " + e.toString());
		} catch (IOException e) {
			fail("Problem with data file. Exception: " + e.toString());
		}
	}

	@Test
	void testInputPlayers() {
		try {
			InputPlayers testInput = new InputPlayers(inputFile, db);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		} catch (FileFormatException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

}
