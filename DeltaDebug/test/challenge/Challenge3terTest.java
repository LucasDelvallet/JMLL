package challenge;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge3ter;

public class Challenge3terTest extends ChallengeTest {

	@Before
	public void setUp() throws Exception {
		challenge = new Challenge3ter();
	}

	@Test
	public void shouldCrashWhenInputIsBelowFiveCharacters() {
		try {
			challenge.challenge("1234");
			fail("Should throw an exception");
		} catch (Exception e) {
			// Should be reached
		}
	}
	
	@Test
	public void shouldNotCrashWhenInputIsAboveOrEqualFiveCharacters() {
		try {
			challenge.challenge("12345");
			challenge.challenge("123456");
		} catch (Exception e) {
			fail("Should not throw an exception");
		}
	}
	// TODO test debug
}
