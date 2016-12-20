package challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge3ter;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import fr.univ_lille1.m2iagl.dd.DDebuggerImpl;

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
	
	@Test
	public void challengeTest() {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
		cEC.sort();
		
		assertEquals(8, cEC.getChain().size());
		
		assertEquals("Assignement", cEC.getChain().get(0).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(0)).getIteration());
		assertEquals("8", ((ChainElementImpl)cEC.getChain().get(0)).getLine());
		assertEquals("F", ((ChainElementImpl)cEC.getChain().get(0)).getValue());
		assertEquals("retour", ((ChainElementImpl)cEC.getChain().get(0)).getVariable());

		assertEquals("Assignement", cEC.getChain().get(7).getDescription());
		assertEquals(8, ((ChainElementImpl)cEC.getChain().get(7)).getIteration());
		assertEquals("8", ((ChainElementImpl)cEC.getChain().get(7)).getLine());
		assertEquals("FFaaiill", ((ChainElementImpl)cEC.getChain().get(7)).getValue());
		assertEquals("retour", ((ChainElementImpl)cEC.getChain().get(7)).getVariable());

		// TODO Multiple tests
	}
}
