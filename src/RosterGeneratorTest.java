import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RosterGeneratorTest {

	static MLBDatabase db;
	static InputPlayers players;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Load the database
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

		// Load the players
		File inputFile = new File("input/FanDuel-MLB-2019-07-22-37097-players-list.csv");
		try {
			players = new InputPlayers(inputFile, db);
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
	/**
	 * Make sure we return an empty roster if no players are given
	 */
	void testEmptyRoster() {
		ArrayList<Player> noPlayers = new ArrayList<Player>();
		RosterGenerator rg = new RosterGenerator(noPlayers);
		int expectedSize = 0;
		int actualSize = 0;
		Roster suggestedRoster = rg.getRosterSuggestion();
		for (Player p : suggestedRoster) {
			actualSize++;
		}
		assertEquals(expectedSize, actualSize);
	}

	@Test
	/**
	 * Make sure that our Roster has 9 players
	 */
	void testRosterSize() {
		RosterGenerator rg = new RosterGenerator(players.getPlayers());
		int expectedSize = 9;
		int actualSize = 0;
		Roster suggestedRoster = rg.getRosterSuggestion();
		for (Player p : suggestedRoster) {
			actualSize++;
		}
		assertEquals(expectedSize, actualSize);
	}

	@Test
	/**
	 * Make sure that the suggested roster is under the salary cap
	 */
	void testRosterSalary() {
		RosterGenerator rg = new RosterGenerator(players.getPlayers());
		int maxSalary = 35000;
		int actualSalary = 0;
		Roster suggestedRoster = rg.getRosterSuggestion();
		for (Player p : suggestedRoster) {
			actualSalary += p.getSalary();
		}
		assert (maxSalary >= actualSalary);
	}

	@Test
	/**
	 * Make sure that the suggested roster has the right player positions
	 */
	void testRosterPositions() {
		RosterGenerator rg = new RosterGenerator(players.getPlayers());
		int expectedPitchers = 1;
		int minOF = 3;
		int min1B = 1;
		int min2B = 1;
		int min3B = 1;
		int minSS = 1;

		int actualPitchers = 0;
		int actualOF = 0;
		int actual1B = 0;
		int actual2B = 0;
		int actual3B = 0;
		int actualSS = 0;

		Roster suggestedRoster = rg.getRosterSuggestion();
		for (Player p : suggestedRoster) {
			switch (p.getPosition()) {
			case PITCHER:
				actualPitchers += 1;
				break;
			case CATCHERORFIRSTBASE:
				actual1B += 1;
				break;
			case SECONDBASE:
				actual2B += 1;
			case THIRDBASE:
				actual3B += 1;
			case SHORTSTOP:
				actualSS +=1;
			case OUTFIELD:
				actualOF += 1;
				break;
			}
		}
		assertEquals(expectedPitchers, actualPitchers);
		assert(actualOF >= minOF);
		assert(actual1B >= min1B);
		assert(actual2B >= min2B);
		assert(actual3B >= min3B);
		assert(actualSS >= minSS);
	}

}
