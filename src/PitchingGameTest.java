import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class PitchingGameTest {

	@Test
	/**
	 * Check that we're calculating the fantasy points correctly
	 */
	void testFantasyPoints1() {
		PitchingGame pg = new PitchingGame(new Date(), true, true, 1, 1, 1);
		double expectedFP = 13.0;
		double actualFP = pg.getFantasyPoints();
		assertEquals(expectedFP, actualFP);
	}
	
	@Test
	/**
	 * Check that we're calculating the fantasy points correctly
	 */
	void testFantasyPoints2() {
		PitchingGame pg = new PitchingGame(new Date(), false, false, 0, 0, 0);
		double expectedFP = 0.0;
		double actualFP = pg.getFantasyPoints();
		assertEquals(expectedFP, actualFP);
	}
	
	@Test
	/**
	 * Check that we're calculating the fantasy points correctly
	 */
	void testFantasyPoints3() {
		PitchingGame pg = new PitchingGame(new Date(), true, false, 10, 1, 4);
		double expectedFP = -9.0;
		double actualFP = pg.getFantasyPoints();
		assertEquals(expectedFP, actualFP);
	}
}
