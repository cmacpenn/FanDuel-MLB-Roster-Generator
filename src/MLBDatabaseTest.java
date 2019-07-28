import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class MLBDatabaseTest {

	@Test
	void testDBCreate() {
		String url = "jdbc:h2:mem:";
		File rawBatterGameData = new File("database/database_batting_game_logs_after_2015.csv");
		File rawPitcherGameData = new File("database/database_pitching_game_logs_after_2015.csv");
		File rawPlayerData = new File("database/database_players.csv");
		try {
			// Create the database
			MLBDatabase db = new MLBDatabase(url, rawBatterGameData, rawPitcherGameData, rawPlayerData);

			// Check that we've created our three tables
			Connection con = db.getConnection();
			Statement stm = con.createStatement();
			stm.executeQuery("Select * from players");
			stm.executeQuery("Select * from batter_game_log");
			stm.executeQuery("Select * from pitcher_game_log");
		} catch (SQLException e) {
			fail("Failed to create database with the correct tables. Exception: " + e.toString());
		} catch (IOException e) {
			fail("Problem with data file. Exception: " + e.toString());
		}
	}

	@Test
	void testImportData() {
		String url = "jdbc:h2:mem:";
		File rawBatterGameData = new File("database/database_batting_game_logs_after_2015.csv");
		File rawPitcherGameData = new File("database/database_pitching_game_logs_after_2015.csv");
		File rawPlayerData = new File("database/database_players.csv");

		try {
			// Create the database
			MLBDatabase db = new MLBDatabase(url, rawBatterGameData, rawPitcherGameData, rawPlayerData);
			
			// Check that we successfully imported all of the data
			Connection con = db.getConnection();
			Statement stm = con.createStatement();
			
			// Check that we imported all players
			ResultSet players_rs = stm.executeQuery("select count(*) from players");
			players_rs.next();
			int playersActual = players_rs.getInt(1);
			int playersExpected = 1309;
			assertEquals(playersExpected, playersActual);
			
			// Check that we imported all batter records
			ResultSet batters_rs = stm.executeQuery("select count(*) from batter_game_log");
			batters_rs.next();
			int battersActual = batters_rs.getInt(1);
			int battersExpected = 248790;
			assertEquals(battersExpected, battersActual);
			
			
			// Check that we imported all pitcher records
			ResultSet pitchers_rs = stm.executeQuery("select count(*) from pitcher_game_log");
			pitchers_rs.next();
			int pitchersActual = pitchers_rs.getInt(1);
			int pitchersExpected = 68792;
			assertEquals(pitchersExpected, pitchersActual);
			
		} catch (SQLException e) {
			fail("Failed to import table data. Exception: " + e.toString());
		} catch (IOException e) {
			fail("Problem with data file. Exception: " + e.toString());
		}
	}
}
