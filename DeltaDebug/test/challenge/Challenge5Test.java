package challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.JMLLChallenge4;
import fr.univ_lille1.m2iagl.challenge.JMLLChallenge5;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import fr.univ_lille1.m2iagl.dd.DDebuggerImpl;

public class Challenge5Test extends ChallengeTest {

	@Before
	public void setUp() throws Exception {
		challenge = new JMLLChallenge5();
	}

	@Test
	public void challengeShouldSuccessWith() {
		try {
			challenge.challenge(100);
		} catch (Exception | AssertionError e) {
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	public void challengeShouldFailWith() {
		try {
			challenge.challenge(2147483647);
			fail("Exception should be thrown");
		} catch (Exception | AssertionError e) {
			// Exception should be thrown
		}
	}
	
	@Test
	public void challengeTest() {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
		
		assertEquals(2, cEC.getChain().size());
		
		assertEquals("Declaration", cEC.getChain().get(0).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(0)).getIteration());
		assertEquals("4", ((ChainElementImpl)cEC.getChain().get(0)).getLine());
		assertEquals(2147483647, ((ChainElementImpl)cEC.getChain().get(0)).getValue());
		assertEquals("value", ((ChainElementImpl)cEC.getChain().get(0)).getVariable());

		assertEquals("If condition", cEC.getChain().get(1).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(1)).getIteration());
		assertEquals("6", ((ChainElementImpl)cEC.getChain().get(1)).getLine());
		assertEquals(false, ((ChainElementImpl)cEC.getChain().get(1)).getValue());
		assertEquals("debug(((input % 2) == 0), \"If condition\")", ((ChainElementImpl)cEC.getChain().get(1)).getVariable());

		// TODO Multiple tests
	}
}
