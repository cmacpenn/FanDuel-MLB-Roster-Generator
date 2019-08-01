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

	// Methods
	/**
	 * Sums the average fantasy points for each player.
	 * 
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
	 * 
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
		String prettyRoster = "";
		if (players.size() == 0) {
			prettyRoster = "Error: Roster is empty. Check input.";
		} else {
			prettyRoster = "FanDuel Roster Results\n\n Estimated fantasy points: " + String.format("%.1f", fantasyPoints);
			for (Player p : players) {
				prettyRoster += "\n" + count + ". " + p.getName() + " " + p.getPosition() + " $" + String.format("%,d",p.getSalary());
				count++;
			}
		}

		return prettyRoster;
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

}
