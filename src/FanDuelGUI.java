
/**
 * Main GUI user interface for uploading the day's player data,
 * getting a visual roster recommedation, and uploading new data.
 * @author Colin McLaughlin & Kenton Van
 *
 */

import java.awt.*;
import javax.swing.*;

public class FanDuelGUI implements Runnable {
	
	    private JFrame frame;
	    private JButton uploadData;
	    private JButton uploadPlayerFile;
	    private JTextField rosterDisplay;
	    private JTextField display;

	    public JTextField getrosterDisplay() {
	        return rosterDisplay;
	    }

	    public JTextField getdisplay() {
	        return display;
	    }

	    public JButton getuploadData() {
	        return uploadData;
	    }

	    

	    public String toString(JTextField JTextField) {
	        return JTextField.getText();
	    }

	    @Override
	    public void run() {
	        this.frame = new JFrame("FanFuel Roster Recs");
	        this.frame.setPreferredSize(new Dimension(600, 300));
	        this.frame.setLayout(new GridLayout(3, 1));

	        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        createComponents(this.frame.getContentPane());

	        this.frame.pack();
	        this.frame.setVisible(true);
	    }

	    private void createComponents(Container container) {
	        display = new JTextField("Welcome to the FanDuel Fantasy Baseball Recommender");
	        display.setEnabled(false);

	        rosterDisplay = new JTextField("");
	        rosterDisplay.setEnabled(false);

	        container.add(display);
	        container.add(rosterDisplay);
	        container.add(createPanel(display, rosterDisplay), BorderLayout.SOUTH);
	    }
		//TO DO makem Listener class for buttons
	    private JPanel createPanel(JTextField display, JTextField rosterDisplay) {
	        JPanel panel = new JPanel(new GridLayout(1, 3));

	        uploadData = new JButton("Upload Data");
	        

	        uploadPlayerFile = new JButton("Upload File");
	        

	        JButton generateRoster = new JButton("Generate");
	        

	        panel.add(uploadPlayerFile);
	        panel.add(generateRoster);
	        panel.add(uploadData);

	        return panel;
	    }

	    public JFrame getFrame() {
	        return frame;
	    }


	
}
