package challenge;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge;
import fr.univ_lille1.m2iagl.challenge.Challenge1;
import fr.univ_lille1.m2iagl.challenge.Challenge2;
import fr.univ_lille1.m2iagl.challenge.Challenge3;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import fr.univ_lille1.m2iagl.dd.DDebuggerImpl;
import fr.univ_lille1.m2iagl.dd.DeltaDebug;

public class Challenge1Test extends ChallengeTest {
	
	@Before
	public void setUp() throws Exception {
		challenge = new Challenge1();
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
	public void challengeShouldBeDebbugedAndHaveFourElements() {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
		cEC.sort();
		
		assertEquals(3, cEC.getChain().size());
		
		assertEquals("Assignement", cEC.getChain().get(0).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(0)).getIteration());
		assertEquals("5", ((ChainElementImpl)cEC.getChain().get(0)).getLine());
		assertEquals(5, ((ChainElementImpl)cEC.getChain().get(0)).getValue());
		assertEquals("input", ((ChainElementImpl)cEC.getChain().get(0)).getVariable());
		
		assertEquals("Assignement", cEC.getChain().get(1).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(1)).getIteration());
		assertEquals("6", ((ChainElementImpl)cEC.getChain().get(1)).getLine());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(1)).getValue());
		assertEquals("input", ((ChainElementImpl)cEC.getChain().get(1)).getVariable());

		assertEquals("Assignement", cEC.getChain().get(2).getDescription());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(2)).getIteration());
		assertEquals("8", ((ChainElementImpl)cEC.getChain().get(2)).getLine());
		assertEquals(0, ((ChainElementImpl)cEC.getChain().get(2)).getValue());
		assertEquals("input", ((ChainElementImpl)cEC.getChain().get(2)).getVariable());
	}
	
}
