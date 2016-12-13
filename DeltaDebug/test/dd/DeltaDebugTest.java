package dd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
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
	
	@Test
	public void difference_test(){
		ChainElementImpl celement1 = new ChainElementImpl("1", "variable1", "description1");
		ChainElementImpl celement2 = new ChainElementImpl("2", "variable2", "description2");
		ChainElementImpl celement3 = new ChainElementImpl("3", "variable3", "description3");
		ChainElementImpl celement4 = new ChainElementImpl("4", "variable4", "description4");
		
		
		CauseEffectChainImpl ce1 = new CauseEffectChainImpl();
		ce1.addElement(celement1);
		ce1.addElement(celement2);
		ce1.addElement(celement3);	
		
		CauseEffectChainImpl ce2 = new CauseEffectChainImpl();
		ce2.addElement(celement1);
		ce2.addElement(celement2);
		ce2.addElement(celement4);
		
		List<ChainElement> cDiff = DeltaDebug.difference(ce1.getChain(), ce2.getChain());
		
		assertEquals(1, cDiff.size());
		assertEquals(celement4, cDiff.get(0));
	}

}
