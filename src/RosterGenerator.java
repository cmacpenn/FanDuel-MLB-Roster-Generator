import java.util.ArrayList;

/**
 * Given a set of players, generates a suggested roster for FanDuel competitions
 * that will maximize the number of fantasy points.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class RosterGenerator {

	// Variables
	private ArrayList<Player> players; // The list of MLB players to choose from
	private static final int SALARYCAP = 35000; // The salary cap for a team, in dollars

	// Constructor
	/**
	 * Constructor for RosterGenerator
	 * 
	 * @param players An array of players to choose from
	 */
	public RosterGenerator(ArrayList<Player> players) {
		this.players = players;
	}

	// Methods
	/**
	 * Calculates a roster that will meet the FanDuel MLB game constraints while
	 * maximizing the number of fantasy points it is likely to generate.
	 * 
	 * @return The suggested FanDuel roster that will maximize fantasy points.
	 */
	public Roster getRosterSuggestion() {
		// TODO Generate a list of random rosters - generateMultipleRandomRosters()
		// TODO Use an algorithm to assign the number of fantasy points likely to be
		// earned. Rank the rosters and return the one with the hightest fantasy points
		// - rankRosters()
	}

	/**
	 * Generates n random rosters that meet the following criteria:
	 * 
	 * 1. Must have 1 each of pitcher, C/1B, 2B, 3B, SS, and UTIL. Must have 3 OF.
	 * 
	 * 2. Total salary for the roster must not exceed SALARYCAP
	 * 
	 * @param n The number of random rosters to generate
	 * @return
	 */
	private ArrayList<Roster> generateMultipleRandomRosters(int n) {
		// TODO Create the roster with the correct positions - chooseRandomRoster()
		// TODO Verify that the roster is under the salary cap -
		// isRosterUnderSalaryCap()
	}

	/**
	 * Uses the list of players in the object to randomly select a player for each
	 * position and create a roster.
	 * 
	 * @return A roster with a random selection of players.
	 */
	private Roster chooseRandomRoster() {

	}

	/**
	 * Checks whether the proposed roster is under the salary cap.
	 * 
	 * @param roster The roster of players
	 * @return True, if the roster has a combined salary under the SALARYCAP. False
	 *         otherwise.
	 */
	//TODO Revert to private non-static when tests are done.  Temporary fix for testing deliverable.
	public static boolean isRosterUnderSalaryCap(Roster roster) {
		int totalSalary = 0;
		for (Player player : roster){
			totalSalary += player.getSalary();
		}
		if (totalSalary <= SALARYCAP){
			return true;
		} else {
			return false;
		}
		// TODO
	}

	/**
	 * Given an array of rosters, returns the one that is likely to generate the
	 * most fantasy points.
	 * 
	 * @param rosters The array of rosters to choose from
	 * @return The roster with the highest number of predicted fantasy points
	 */
	private Roster rankRosters(ArrayList<Roster> rosters) {
		// TODO Calculate fantasy points - calculateLikelyFantasyPoints()
		// TODO Rank rosters
	}

	/**
	 * An algorithm to predict the number of fantasy points a team is likely to
	 * earn.
	 * 
	 * @param roster The roster of players to evaluate.
	 * @return The number of forecasted fantasy points the roster will earn.
	 */
	private double calculateLikelyFantasyPoints(Roster roster) {
		// TODO Implement algorithm
	}

}
//test edit
