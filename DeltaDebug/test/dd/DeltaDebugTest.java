package dd;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.m2iagl.dd.DeltaDebug;

public class DeltaDebugTest {
	private static final String CHALLENGE_FILE = "test/dd/data/FakeTemplateChallenge.java";
	private static final String CHALLENGE_NAME = "FakeTemplateChallenge";

	@Test
	public void successInputShouldReturnTrue() {
		int successNumber = 42;
		if(!DeltaDebug.generateCauseEffectChain(successNumber, CHALLENGE_FILE, CHALLENGE_NAME)) {
			fail();
		}
	}
	
	@Test
	public void failInputShouldReturnFalse() {
		int failNumber = 2;
		if(DeltaDebug.generateCauseEffectChain(failNumber, CHALLENGE_FILE, CHALLENGE_NAME)) {
			fail();
		}
	}

}
