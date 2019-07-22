import java.util.ArrayList;
import java.util.Iterator;

/**
 * Holds 9 MLB players with 1 pitcher, 1 center/1st base, 1 2nd base, 1 3rd
 * base, 1 short stop, 3 out fielders, and 1 utility
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class Roster implements Iterable<Player> {

	// Variables
	private ArrayList<Player> players;

	// Constructor
	/**
	 * Constructor for Roster
	 * 
	 * @param players The list of 9 players.
	 */
	public Roster(ArrayList<Player> players) {
		// TODO Check that we have 9 players
		// TODO Check that we have the right types of players
		this.players = players;
	}

	@Override
	/**
	 * Implement iterator so we can use for each loops on rosters
	 */
	public Iterator<Player> iterator() {
		Iterator<Player> playerIterator = players.iterator();
		return playerIterator;
	}

	// Methods

	// TODO add getter

	// TODO Add methods to return a specific position?

}
