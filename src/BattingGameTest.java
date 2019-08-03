import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class BattingGameTest {

	@Test
	/**
	 * Check that we're calculating the fantasy points correctly
	 */
	void testFantasyPoints1() {
		BattingGame bg = new BattingGame(new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
		double expectedFP = 48.7;
		double actualFP = bg.getFantasyPoints();
		assertEquals(expectedFP, actualFP);
	}
	
	@Test
	/**
	 * Check that we're calculating the fantasy points correctly
	 */
	void testFantasyPoints2() {
		BattingGame bg = new BattingGame(new Date(), 0, 1, 0, 1, 0, 1, 0, 1, 0);
		double expectedFP = 27.2;
		double actualFP = bg.getFantasyPoints();
		assertEquals(expectedFP, actualFP);
	}
	
	@Test
	/**
	 * Check that we're calculating the fantasy points correctly
	 */
	void testFantasyPoints3() {
		BattingGame bg = new BattingGame(new Date(), 0, 0, 0, 0, 0, 0, 0, 0, 0);
		double expectedFP = 0;
		double actualFP = bg.getFantasyPoints();
		assertEquals(expectedFP, actualFP);
	}

}
