import java.util.ArrayList;

/**
 * Contains the results of the past MLB games played by the player
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class PlayerHistory {

	// Variables
	private ArrayList<Game> games; // Holds the MLB games the player has played

	// Constructor
	/**
	 * Constructor for PlayerHistory
	 * 
	 * @param games The array of games the player has played in.
	 */
	PlayerHistory(ArrayList<Game> games) {
		this.games = games;
	}

	// Methods
	/**
	 * Getter for games
	 * 
	 * @return The array of games the player has played in.
	 */
	public ArrayList<Game> getGames() {
		return this.games;
	}
}
