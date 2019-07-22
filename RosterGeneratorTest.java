import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RosterGeneratorTest {

	/**
	 * This test tests that the method returns true for 
	 * gross salary under 35k.
	 */
	@Test
	void testIsRosterUnderSalaryCap1() {
		Player one = new Player(1000);
		Player two = new Player(1000);
		Player three = new Player(1000);
		Player four = new Player(1000);
		Player five = new Player(1000);
		Player six = new Player(1000);
		Player seven = new Player(1000);
		Player eight = new Player(1000);
		Player nine = new Player(1000);
		
		
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
		
		Salary s1 = new Salary();
		
		boolean expected = true;
		assertEquals(expected, s1.isRosterUnderSalaryCap(players));
		
		
	}
	/**
	 * This test tests that gross salary returns false
	 * when over 35k.
	 */
	@Test
	void testIsRosterUnderSalaryCap2() {
		Player one = new Player(100000);
		Player two = new Player(1000);
		Player three = new Player(1000);
		Player four = new Player(1000);
		Player five = new Player(1000);
		Player six = new Player(1000);
		Player seven = new Player(1000);
		Player eight = new Player(1000);
		Player nine = new Player(1000);
		
		
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
		
		Salary s1 = new Salary();
		
		boolean expected = false;
		assertEquals(expected, s1.isRosterUnderSalaryCap(players));
		
		
	}

}
