import java.io.File;
import java.io.FileNotFoundException;
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
	File input;

	// Constructor
	/**
	 * Constructor for InputPlayers
	 * 
	 * @param input The input file downloaded from FanDuel
	 * @throws FileFormatException
	 * @throws FileNotFoundException
	 */
	InputPlayers(File input) throws FileNotFoundException, FileFormatException {
		this.input = input;
		ArrayList<String[]> rawData = parseDataFile();
		
		// TODO: Convert data into player objects
		// TODO: Make sure that the IDs are valid - strip the leading stuff
		// TODO: Only keep players who have played at least one game 
	}

	// Methods
	/**
	 * Reads the input csv file and converts it to a string array
	 * 
	 * @return An array list with the data in string format
	 * @throws FileNotFoundException
	 */
	private ArrayList<String[]> parseDataFile() throws FileNotFoundException, FileFormatException {

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
		
		for(String column : columns) {
			if(column.equalsIgnoreCase("id")) {
				hasId = true;
			}
			if(column.equalsIgnoreCase("position")) {
				hasPosition = true;
			}
			if(column.equalsIgnoreCase("nickname")) {
				hasNickname = true;
			}
			if(column.equalsIgnoreCase("salary")) {
				hasSalary = true;
			}
		}
		return hasId & hasPosition & hasNickname & hasSalary;
	}
}
