package challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.ChallengePerf1;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.DDebuggerImpl;

public class ChallengePerf1Test extends ChallengeTest {

	@Before
	public void setUp() throws Exception {
		challenge = new ChallengePerf1();
	}
	
	@Test
	public void testDebugging() {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
	}

}
