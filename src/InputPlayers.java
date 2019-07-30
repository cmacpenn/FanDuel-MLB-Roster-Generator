import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the creation of a list of Players from the input FanDuel data
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class InputPlayers {

	// Variables
	private File input; // The input file from FanDuel
	private ArrayList<Player> players; // The list of eligible players.

	// Constructor
	/**
	 * Constructor for InputPlayers
	 * 
	 * @param input The input file downloaded from FanDuel
	 * @throws FileFormatException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 */
	InputPlayers(File input, MLBDatabase database)
			throws FileNotFoundException, FileFormatException, SQLException, IllegalArgumentException {
		this.input = input;

		// Import data and parse into Player objects
		ArrayList<String[]> rawData = readDataFile();
		ArrayList<Player> allPlayers = parseData(rawData, database);

		// Only keep players who have played at least one game
		// This will also weed out players who aren't in the database, or who haven't
		// played any games since 2015
		this.players = new ArrayList<Player>();
		for (Player player : allPlayers) {
			if (player.getHistory().getGames().size() > 0) {

			}
		}
	}

	// Methods
	/**
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Reads the input csv file and converts it to a string array
	 * 
	 * @return An array list with the data in string format
	 * @throws FileNotFoundException
	 */
	private ArrayList<String[]> readDataFile() throws FileNotFoundException, FileFormatException {

		// Check that the input is a .csv file
		if (!isCSVFile()) {
			throw new FileFormatException(input, "Input must be a CSV file.");
		}

		// Read the file
		Scanner in = new Scanner(input);
		ArrayList<String[]> inputArray = new ArrayList<String[]>();
		while (in.hasNextLine()) {
			inputArray.add(in.nextLine().split(","));
		}

		// Check that we have the required columns
		if (!hasRequiredColumns(inputArray.get(0))) {
			in.close();
			throw new FileFormatException(input, "Input must contain the Id, Position, Nickname, and Salary columns");
		}

		in.close();
		return inputArray;
	}

	/**
	 * Checks to see if the input file is a CSV file
	 * 
	 * @return True, if the file extension is CSV. False otherwise.
	 */
	private boolean isCSVFile() {
		String fileName = input.getName();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		return extension.equalsIgnoreCase("csv");
	}

	/**
	 * Checks if the array contains the necessary columns, Id, position, Nickname,
	 * and Salary
	 * 
	 * @param columns The string array containing the column names
	 * @return True, if the array contains the necessary columns.
	 */
	private boolean hasRequiredColumns(String[] columns) {
		boolean hasId = false;
		boolean hasPosition = false;
		boolean hasNickname = false;
		boolean hasSalary = false;

		for (String column : columns) {
			if (column.equalsIgnoreCase("id")) {
				hasId = true;
			}
			if (column.equalsIgnoreCase("position")) {
				hasPosition = true;
			}
			if (column.equalsIgnoreCase("nickname")) {
				hasNickname = true;
			}
			if (column.equalsIgnoreCase("salary")) {
				hasSalary = true;
			}
		}
		return hasId & hasPosition & hasNickname & hasSalary;
	}

	/**
	 * Converts the raw FanDuel player list into a list of Player objects.
	 * 
	 * @param rawData The list of string arrays that holds the FanDuel player list.
	 * @return A list of players.
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 */
	private ArrayList<Player> parseData(ArrayList<String[]> rawData, MLBDatabase database)
			throws SQLException, IllegalArgumentException {
		ArrayList<Player> players = new ArrayList<Player>();

		// Get the indices of the columns we need to create a player
		int idIndex = getColumnIndex("id", rawData.get(0));
		int positionIndex = getColumnIndex("position", rawData.get(0));
		int nameIndex = getColumnIndex("nickname", rawData.get(0));
		int salaryIndex = getColumnIndex("salary", rawData.get(0));

		// Create player object
		// Note: Starting at i=1 because we're skipping the column headers
		for (int i = 1; i < rawData.size(); i++) {
			int id = cleanId(rawData.get(i)[idIndex]);
			String name = rawData.get(i)[nameIndex];
			int salary = Integer.parseInt(rawData.get(i)[salaryIndex]);
			PlayerPosition position = positionStringToEnum(rawData.get(i)[positionIndex]);
			PlayerHistory history = database.pullPlayerHistory(id, position);
			players.add(new Player(id, name, salary, position, history));
		}

		return players;

	}

	/**
	 * Returns the index of the given column.
	 * 
	 * @param columnName The column being searched for.
	 * @param columns    The array of column names.
	 * @return The index of the column in question. -1 if it can't be found.
	 */
	private int getColumnIndex(String columnName, String[] columns) {
		int index = -1;

		for (int i = 0; i < columns.length; i++) {
			if (columns[i].equalsIgnoreCase(columnName)) {
				index = i;
				break;
			}

		}

		return index;
	}

	/**
	 * FanDuel IDs are prefaced by a file number and a hypen. Removing those.
	 * 
	 * @param id The full string FanDuel ID.
	 * @return Just the ID, without the preceding file number or hyphen.
	 */
	private int cleanId(String id) {
		int hyphenIndex = id.indexOf("-");
		return Integer.parseInt(id.substring(hyphenIndex + 1));
	}

	/**
	 * Converts a string position to the appropriate enum.
	 * 
	 * @param position The string position abbreviation.
	 * @return The corresponding enum value.
	 */
	private PlayerPosition positionStringToEnum(String position) throws IllegalArgumentException {
		switch (position) {
		case "P":
			return PlayerPosition.PITCHER;
		case "C":
			return PlayerPosition.CATCHERORFIRSTBASE;
		case "1B":
			return PlayerPosition.CATCHERORFIRSTBASE;
		case "2B":
			return PlayerPosition.SECONDBASE;
		case "3B":
			return PlayerPosition.THIRDBASE;
		case "SS":
			return PlayerPosition.SHORTSTOP;
		case "OF":
			return PlayerPosition.OUTFIELD;
		default:
			throw new IllegalArgumentException(
					"Invalid position data in input file. " + position + "is not a valid position.");
		}

	}
}
