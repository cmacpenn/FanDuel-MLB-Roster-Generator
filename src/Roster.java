import java.util.ArrayList;
import java.util.Iterator;

/**
 * Holds 9 MLB players with 1 pitcher, 1 center/1st base, 1 2nd base, 1 3rd
 * base, 1 short stop, 3 out fielders, and 1 utility
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class Roster implements Iterable<Player>, Comparable<Roster>{

	// Variables
	private ArrayList<Player> players;
	private double fantasyPoints;

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
		fantasyPoints = this.calculateLikelyFantasyPoints();
	}
	
	private double calculateLikelyFantasyPoints() {
		double totalFantasyPoints = 0.0;
		for (Player p : this.players) {
			totalFantasyPoints += p.getHistory().getAverageFantasyPointsPerGame();
		}
		return totalFantasyPoints;
		
		// TODO Implement algorithm
	}
	
	

	public double getFantasyPoints() {
		return fantasyPoints;
	}

	@Override
	/**
	 * Implement iterator so we can use for each loops on rosters
	 */
	public Iterator<Player> iterator() {
		Iterator<Player> playerIterator = players.iterator();
		return playerIterator;
	}
	
	
	//need to finish. double error
	@Override
    public int compareTo(Roster roster) {
       if(this.fantasyPoints > roster.getFantasyPoints()) {
    	   return -1;
       } else if (this.fantasyPoints == roster.getFantasyPoints()) {
    	   return 0;
       }
       else {
    	   return 1;
       }
        
    }

	// Methods

	// TODO add getter

	// TODO Add methods to return a specific position?

}
