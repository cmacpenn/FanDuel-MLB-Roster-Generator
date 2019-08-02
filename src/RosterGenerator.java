import java.util.*;
import java.util.Random;

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
	private ArrayList<Player> pitchers;
	private ArrayList<Player> catcherOrFirsts;
	private ArrayList<Player> seconds;
	private ArrayList<Player> thirds;
	private ArrayList<Player> shorts;
	private ArrayList<Player> outfields;

	// Constructor
	/**
	 * Constructor for RosterGenerator
	 * 
	 * @param players An array of players to choose from
	 */
	public RosterGenerator(ArrayList<Player> players) {
		this.players = players;
		this.pitchers = this.generatePositionArray("PITCHER");
		this.catcherOrFirsts = this.generatePositionArray("CATCHERORFIRSTBASE");
		this.seconds = this.generatePositionArray("SECONDBASE");
		this.thirds = this.generatePositionArray("THIRDBASE");
		this.shorts = this.generatePositionArray("SHORTSTOP");
		this.outfields = this.generatePositionArray("OUTFIELD");
	}

	// Methods
	/**
	 * Calculates a roster that will meet the FanDuel MLB game constraints while
	 * maximizing the number of fantasy points it is likely to generate.
	 * 
	 * @return The suggested FanDuel roster that will maximize fantasy points.
	 */
	public Roster getRosterSuggestion() {
		if (players.size() == 0) {
			ArrayList<Player> noPlayer = new ArrayList<Player>();
			Roster emptyRoster = new Roster(noPlayer);
			return emptyRoster;
		} else {
			ArrayList<Roster> manyRosters = this.generateMultipleRandomRosters(10000);
			Roster recommendedRoster = this.rankRosters(manyRosters);
			return recommendedRoster;
		}
	}

	/**
	 * Generates n random rosters that meet the following criteria:
	 * 
	 * 1. Must have 1 each of pitcher, C/1B, 2B, 3B, SS, and WILD. Must have 3 OF.
	 * 
	 * 2. Total salary for the roster must not exceed SALARYCAP
	 * 
	 * @param n The number of random rosters to generate
	 * @return
	 */
	public ArrayList<Roster> generateMultipleRandomRosters(int n) {
		ArrayList<Roster> rosters = new ArrayList<Roster>();
		int count = 0;
		while (count < n) {
			Roster r = this.chooseRandomRoster();
			if (this.isRosterUnderSalaryCap(r)) {
				rosters.add(r);
				count++;
			}
		}
		return rosters;
	}

	/**
	 * Uses the list of players in the object to randomly select a player for each
	 * position and create a roster.
	 * 
	 * @return A roster with a random selection of players.
	 */
	public Roster chooseRandomRoster() {
		ArrayList<Player> team = new ArrayList<Player>();
		ArrayList<Player> teamNoWild = new ArrayList<Player>();
		if (this.checkPlayers(players, pitchers, seconds, thirds, shorts, catcherOrFirsts, outfields)) {
			Player pitcher = this.getRandomPlayer(this.pitchers);
			Player catcherOrFirst = this.getRandomPlayer(this.catcherOrFirsts);
			Player second = this.getRandomPlayer(this.seconds);
			Player third = this.getRandomPlayer(this.thirds);
			Player shortstop = this.getRandomPlayer(this.shorts);
			Player[] outfielders = this.getRandomPlayers(this.outfields);
			Player outfield1 = outfielders[0];
			Player outfield2 = outfielders[1];
			Player outfield3 = outfielders[2];
			teamNoWild.add(outfield3);
			teamNoWild.add(outfield2);
			teamNoWild.add(outfield1);
			teamNoWild.add(shortstop);
			teamNoWild.add(third);
			teamNoWild.add(second);
			teamNoWild.add(catcherOrFirst);
			Player wild = this.getRandomWild(teamNoWild);
			team = teamNoWild;
			team.add(pitcher);
			team.add(wild);

			Roster roster = new Roster(team);
			return roster;
		}
		Roster notEnoughPlayers = new Roster(team);
		System.out.println("Error: Not enough players");
		return notEnoughPlayers;

	}

	/**
	 * Checks whether the proposed roster is under the salary cap.
	 * 
	 * @param roster The roster of players
	 * @return True, if the roster has a combined salary under the SALARYCAP. False
	 *         otherwise.
	 */
	private boolean isRosterUnderSalaryCap(Roster roster) {
		int totalSalary = 0;
		for (Player player : roster) {
			totalSalary += player.getSalary();
		}
		if (totalSalary <= SALARYCAP) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Given an array of rosters, returns the one that is likely to generate the
	 * most fantasy points.
	 * 
	 * @param rosters The array of rosters to choose from
	 * @return The roster with the highest number of predicted fantasy points
	 */
	public Roster rankRosters(ArrayList<Roster> rosters) {
		ArrayList<Roster> sortedRoster = rosters;
		Collections.sort(sortedRoster);
		return sortedRoster.get(sortedRoster.size() - 1);
	}

	/**
	 * Get a random player
	 * 
	 * @param list
	 * @return
	 */
	private Player getRandomPlayer(ArrayList<Player> list) {
		Collections.shuffle(list);
		return list.get(0);
	}

	/**
	 * Return 3 outfielders that are not duplicates
	 * 
	 * @param list
	 * @return
	 */
	private Player[] getRandomPlayers(ArrayList<Player> list) {
		List<Player> outfields = new ArrayList<Player>();
		for (Player i : list) {
			outfields.add(i);
		}
		Collections.shuffle(list);
		Player[] nonDuplicate = new Player[3];
		for (int j = 0; j < 3; j++) {
			nonDuplicate[j] = outfields.get(j);
		}
		return nonDuplicate;
	}

	/**
	 * Return a random player of ones not already used.
	 * 
	 * @param list
	 * @return
	 */
	private Player getRandomWild(ArrayList<Player> list) {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		allPlayers = (ArrayList<Player>) players.clone();
		Iterator<Player> iterator = allPlayers.iterator();
		String pitch = "PITCHER";
		while (iterator.hasNext()) {
			if (pitch.contentEquals(iterator.next().getPosition().name())) {
				iterator.remove();
			}

		}
		allPlayers.removeAll(list);

		Player wild = this.getRandomPlayer(allPlayers);
		return wild;
	}

	/**
	 * Return an array of specific positions
	 * 
	 * @param position
	 * @return
	 */
	private ArrayList<Player> generatePositionArray(String position) {
		ArrayList<Player> generate = new ArrayList<Player>();
		for (Player player : this.players) {
			if (position.contentEquals(player.getPosition().name())) {
				generate.add(player);

			}
		}
		return generate;
	}

	/**
	 * Check that the array of players have enough for one team
	 * 
	 * @param p
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @return
	 */

	private boolean checkPlayers(ArrayList<Player> p, ArrayList<Player> p1,
			ArrayList<Player> p2, ArrayList<Player> p3, ArrayList<Player> p4,
			ArrayList<Player> p5, ArrayList<Player> p6) {
		int wildSize = p2.size() + p3.size() + p4.size() +
					p5.size() + p6.size();
		if (p.size() >= 9 && p1.size() >= 1 && p2.size() >= 1 
				&& p3.size() >= 1 && p4.size() >=1 && 
				p5.size() >= 1 && p6.size() >= 3 && wildSize >= 8) {
			return true;
		} else {
			return false;
		}
	}

}
