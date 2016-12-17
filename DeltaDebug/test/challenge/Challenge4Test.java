package challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge4;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import fr.univ_lille1.m2iagl.dd.DDebuggerImpl;

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
	
	@Test
	public void challengeTest() {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
		cEC.sort();
		
		assertEquals(2, cEC.getChain().size());
		
		assertEquals("Assignement", cEC.getChain().get(0).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(0)).getIteration());
		assertEquals("5", ((ChainElementImpl)cEC.getChain().get(0)).getLine());
		assertEquals(4, ((ChainElementImpl)cEC.getChain().get(0)).getValue());
		assertEquals("input", ((ChainElementImpl)cEC.getChain().get(0)).getVariable());

		assertEquals("If condition", cEC.getChain().get(1).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(1)).getIteration());
		assertEquals("6", ((ChainElementImpl)cEC.getChain().get(1)).getLine());
		assertEquals(true, ((ChainElementImpl)cEC.getChain().get(1)).getValue());
		assertEquals("(input == 4)", ((ChainElementImpl)cEC.getChain().get(1)).getVariable());

		// TODO Multiple tests
	}
}
