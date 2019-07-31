
/**
 * Main GUI user interface for uploading the day's player data,
 * getting a visual roster recommedation, and uploading new data.
 * @author Colin McLaughlin & Kenton Van
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class FanDuelGUI {

	// Variables
	private JFrame frame; // Main frame
	private JLabel display; // The main text at the top of the screen.
	private JTextArea rosterDisplay; // Field that will display the suggested roster.
	private JButton uploadPlayerFileButton; // Button that will allow the user to upload the FanDuel player csv file.
	private JButton generateRosterButton; // Button that will run the algorithm to identify a roster.
	private final JFileChooser fc = new JFileChooser(); // Dialog box to choose the player input file.
	private File playerFile; // The file with FanDuel player information.
	
	// Constructor
	/**
	 * Constructor for FanDuelGUI
	 * 
	 * @param url                The link to the database.
	 * @param rawBatterGameData  The csv file with the raw batter game data.
	 * @param rawPitcherGameData The csv file with the raw pitcher game data.
	 * @param rawPlayerData      The csv file with the raw player data.
	 */
	public FanDuelGUI(String url, File rawBatterGameData, File rawPitcherGameData, File rawPlayerData) {
		createGUI();
		try {
			rosterDisplay.setText("Please wait while the database loads.");
			MLBDatabase mlbDB = new MLBDatabase(url, rawBatterGameData, rawPitcherGameData, rawPlayerData);
			rosterDisplay.setText("The database has finished loading.  You may now upload a player file.");
			uploadPlayerFileButton.setEnabled(true);
		} catch (SQLException | IOException e) {
			rosterDisplay.setText(
					"The program has encountered an error loading the database.\nPlease close the program, make sure you have the correct database files, and relaunch the program.\nError: "
							+ e.getMessage());
		}
	}

	// Methods
	/**
	 * Lays out the GUI
	 */
	public void createGUI() {
		// Frame
		frame = new JFrame("FanFuel Roster Recs");
		frame.setPreferredSize(new Dimension(600, 300));
		frame.setLayout(new GridLayout(3, 1));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Text
		display = new JLabel("Welcome to the FanDuel Fantasy Baseball Recommender");
		rosterDisplay = new JTextArea("");
		rosterDisplay.setEnabled(false);
		rosterDisplay.setLineWrap(true);
		rosterDisplay.setWrapStyleWord(true);

		// Buttons
		uploadPlayerFileButton = new JButton("Upload Player File");
		uploadPlayerFileButton.setEnabled(false); // This is disabled until the database has been loaded
		uploadPlayerFileButton.addActionListener(new UploadPlayerListener());
		generateRosterButton = new JButton("Generate Roster");
		generateRosterButton.setEnabled(false); // This is disabled until the user has loaded a list of players
		generateRosterButton.addActionListener(new GenerateRosterListener());

		// Panel
		JPanel panel = new JPanel(new GridLayout(1, 3));
		panel.add(uploadPlayerFileButton);
		panel.add(generateRosterButton);

		// Add to frame
		frame.getContentPane().add(display);
		frame.getContentPane().add(rosterDisplay);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		// createComponents(frame.getContentPane());

		// Display
		frame.pack();
		frame.setVisible(true);
	}

	// Listener Classes
	class UploadPlayerListener implements ActionListener {

		@Override
		/**
		 * Opens a dialog box so that the user can upload a file of players
		 */
		public void actionPerformed(ActionEvent e) {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            playerFile = fc.getSelectedFile();
	            generateRosterButton.setEnabled(true);
	            rosterDisplay.setText("You have chosen to upload the following file: " + playerFile.getName() + ".\n\nPlease click the 'Generate Roster' button to create a FanDuel linup, or 'Upload Player File' to select a different file.");
			}
		}

	}

	class GenerateRosterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
