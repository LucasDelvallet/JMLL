package challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge;
import fr.univ_lille1.m2iagl.challenge.Challenge2;
import fr.univ_lille1.m2iagl.challenge.Challenge3;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import fr.univ_lille1.m2iagl.dd.DDebuggerImpl;
import fr.univ_lille1.m2iagl.dd.DeltaDebug;

public class Challenge3Test extends ChallengeTest {
	
	@Before
	public void setUp() throws Exception {
		challenge = new Challenge3();
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
		
		assertEquals(4, cEC.getChain().size());
		
		assertEquals("Assignement", cEC.getChain().get(0).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(0)).getIteration());
		assertEquals("7", ((ChainElementImpl)cEC.getChain().get(0)).getLine());
		assertEquals("F", ((ChainElementImpl)cEC.getChain().get(0)).getValue());
		assertEquals("retour", ((ChainElementImpl)cEC.getChain().get(0)).getVariable());

		assertEquals("Assignement", cEC.getChain().get(3).getDescription());
		assertEquals(4, ((ChainElementImpl)cEC.getChain().get(3)).getIteration());
		assertEquals("7", ((ChainElementImpl)cEC.getChain().get(3)).getLine());
		assertEquals("Fail", ((ChainElementImpl)cEC.getChain().get(3)).getValue());
		assertEquals("retour", ((ChainElementImpl)cEC.getChain().get(3)).getVariable());

		// TODO Multiple tests
	}
}
