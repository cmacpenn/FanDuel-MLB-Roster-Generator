import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RosterGeneratorTest {

	/**
	 * This test tests that the method returns true for gross salary under 35k.
	 */
	@Test
	void testIsRosterUnderSalaryCap1() {
		Player one = new Player("Bob", 1000, PlayerPosition.PITCHER, null);
		Player two = new Player("James", 1000, PlayerPosition.CENTERORFIRSTBASE, null);
		Player three = new Player("Kurt", 1000, PlayerPosition.SECONDBASE, null);
		Player four = new Player("Bill", 1000, PlayerPosition.THIRDBASE, null);
		Player five = new Player("Eric", 1000, PlayerPosition.SHORTSTOP, null);
		Player six = new Player("Sam", 1000, PlayerPosition.OUTFIELD, null);
		Player seven = new Player("James", 1000, PlayerPosition.OUTFIELD, null);
		Player eight = new Player("Richard", 1000, PlayerPosition.OUTFIELD, null);
		Player nine = new Player("Carter", 1000, PlayerPosition.UTILITY, null);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(one);
		players.add(two);
		players.add(three);
		players.add(four);
		players.add(five);
		players.add(six);
		players.add(seven);
		players.add(eight);
		players.add(nine);
		Roster roster = new Roster(players);

		boolean expected = true;
		boolean actual = RosterGenerator.isRosterUnderSalaryCap(roster);
		assertEquals(expected, actual);

	}

	/**
	 * This test tests that gross salary returns false when over 35k.
	 */
	@Test
	void testIsRosterUnderSalaryCap2() {
		Player one = new Player("Bob", 100000, PlayerPosition.PITCHER, null);
		Player two = new Player("James", 1000, PlayerPosition.CENTERORFIRSTBASE, null);
		Player three = new Player("Kurt", 1000, PlayerPosition.SECONDBASE, null);
		Player four = new Player("Bill", 1000, PlayerPosition.THIRDBASE, null);
		Player five = new Player("Eric", 1000, PlayerPosition.SHORTSTOP, null);
		Player six = new Player("Sam", 1000, PlayerPosition.OUTFIELD, null);
		Player seven = new Player("James", 1000, PlayerPosition.OUTFIELD, null);
		Player eight = new Player("Richard", 1000, PlayerPosition.OUTFIELD, null);
		Player nine = new Player("Carter", 1000, PlayerPosition.UTILITY, null);

		ArrayList<Player> players = new ArrayList<>();
		players.add(one);
		players.add(two);
		players.add(three);
		players.add(four);
		players.add(five);
		players.add(six);
		players.add(seven);
		players.add(eight);
		players.add(nine);
		Roster roster = new Roster(players);

		boolean expected = false;
		boolean actual = RosterGenerator.isRosterUnderSalaryCap(roster);
		assertEquals(expected, actual);

	}

}
