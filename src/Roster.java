import java.util.ArrayList;
import java.util.Iterator;

/**
 * Holds 9 MLB players with 1 pitcher, 1 center/1st base, 1 2nd base, 1 3rd
 * base, 1 short stop, 3 out fielders, and 1 utility
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class Roster implements Iterable<Player>, Comparable<Roster> {

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
		this.players = players;
		fantasyPoints = this.calculateLikelyFantasyPoints();
	}

	/**
	 * Sums the average fantasy points for each player.
	 * @return The average fantasy points for the team.
	 */
	private double calculateLikelyFantasyPoints() {
		double totalFantasyPoints = 0.0;
		for (Player p : this.players) {
			totalFantasyPoints += p.getHistory().getAverageFantasyPointsPerGame();
		}
		return totalFantasyPoints;

	}

	/**
	 * Getter for fantasyPoints
	 * @return fantasyPoints
	 */
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

	@Override
	/**
	 * Pretty printing for display in the GUI.
	 */
	public String toString() {
		int count = 1;
		if (players.size() == 0) {
			return "Roster is Empty. Check Input.";
		} else {
			System.out.printf("%-10s%-30s%s%n", "   Player", "Position", "Salary");
			for (Player p : players) {
				System.out.printf("%-10s%-30s%s%n", count + ". " + p.getName(), p.getPosition(), p.getSalary());
				count++;
			}
			return "Estimated Fantasy Points: " + Double.toString(fantasyPoints);
		}
	}

	@Override
	public int compareTo(Roster roster) {
		if (this.fantasyPoints > roster.getFantasyPoints()) {
			return 1;
		} else if (this.fantasyPoints == roster.getFantasyPoints()) {
			return 0;
		} else {
			return -1;
		}

	}

	// Methods

	// TODO add getter

	// TODO Add methods to return a specific position?

}
