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
	// Make sure we can load everything correctly
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
	
	@Test
	// Make sure we throw the right error if the file doesn't exist
	void testInvalidFile() {
		File imaginaryFile = new File("fileDoesntExist.csv");
		try {
			InputPlayers testInput = new InputPlayers(imaginaryFile, db);
			fail("Test should have failed before this");
		} catch (FileNotFoundException e) {
			// Should pass in this case
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		} catch (FileFormatException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	// Make sure we fail if we don't have the right columns
	void testColumns() {
		File wrongColumns = new File("testInput/WrongColumns.csv");
		try {
			InputPlayers testInput = new InputPlayers(wrongColumns, db);
			fail("Test should have failed before this");
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		} catch (FileFormatException e) {
			// Should pass in this case
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	// Make sure we fail if we have an invalid position
	void testPosition() {
		File badPosition = new File("testInput/BadPosition.csv");
		try {
			InputPlayers testInput = new InputPlayers(badPosition, db);
			fail("Test should have failed before this");
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			// Should pass in this case
		} catch (FileFormatException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	// Make sure we fail if we have an invalid salary
	void testSalary() {
		File badSalary = new File("testInput/BadSalary.csv");
		try {
			InputPlayers testInput = new InputPlayers(badSalary, db);
			fail("Test should have failed before this");
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			// Should pass in this case
		} catch (FileFormatException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

}
