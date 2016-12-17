package challenge;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge4;

public class Challenge4Test extends ChallengeTest {

	@Before
	public void setUp() throws Exception {
		challenge = new Challenge4();
	}

	@Test
	public void challengeShouldSuccessWithFive() {
		try {
			challenge.challenge(5);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	public void challengeShouldFailWithThree() {
		try {
			challenge.challenge(3);
			fail("Exception should be thrown");
		} catch (Exception e) {
			// Exception should be thrown
		}
	}
	
	// TODO test debug
}
