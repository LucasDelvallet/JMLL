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
	public void shouldHaveADifference(){
		ChainElementImpl celement1 = new ChainElementImpl("1", "variable1", "description1");
		celement1.setValue("1");
		ChainElementImpl celement2 = new ChainElementImpl("2", "variable2", "description2");
		celement2.setValue(33);
		ChainElementImpl celement3 = new ChainElementImpl("4", "variable4", "description4");
		celement3.setValue("coucou");
		ChainElementImpl celement4 = new ChainElementImpl("4", "variable4", "description4");
		celement4.setValue(42);
		
		
		CauseEffectChainImpl successCauseEffect = new CauseEffectChainImpl();
		successCauseEffect.addElement(celement1);
		successCauseEffect.addElement(celement2);
		successCauseEffect.addElement(celement3);	
		
		CauseEffectChainImpl failCauseEffect = new CauseEffectChainImpl();
		failCauseEffect.addElement(celement1);
		failCauseEffect.addElement(celement2);
		failCauseEffect.addElement(celement4);
		
		List<ChainElement> cDiff = DeltaDebug.difference(successCauseEffect.getChain(), failCauseEffect.getChain());
		
		assertEquals(1, cDiff.size());
		assertEquals(celement4, cDiff.get(0));
	}
	
	@Test
	public void NullValuesShouldNotBeDifferents(){
		ChainElementImpl successChainElement = new ChainElementImpl("val", "variable", "description");
		ChainElementImpl failChainElement = new ChainElementImpl("val", "variable", "description");	
		
		CauseEffectChainImpl successCauseEffectChain = new CauseEffectChainImpl();
		successCauseEffectChain.addElement(successChainElement);
		
		CauseEffectChainImpl failCauseEffectChaon = new CauseEffectChainImpl();
		failCauseEffectChaon.addElement(failChainElement);
		List<ChainElement> cDiff = DeltaDebug.difference(successCauseEffectChain.getChain(), failCauseEffectChaon.getChain());
		
		assertEquals(0, cDiff.size());
	}
	
	@Test
	public void differenceBetweenNullAndValueShouldBeDetected(){
		ChainElementImpl successChainElement = new ChainElementImpl("val", "variable", "description");
		successChainElement.setValue(new Integer(1));
		ChainElementImpl failChainElement = new ChainElementImpl("val", "variable", "description");
		failChainElement.setValue(null);
		
		CauseEffectChainImpl ce1 = new CauseEffectChainImpl();
		ce1.addElement(successChainElement);
		
		CauseEffectChainImpl ce2 = new CauseEffectChainImpl();
		ce2.addElement(failChainElement);
		List<ChainElement> cDiff = DeltaDebug.difference(ce1.getChain(), ce2.getChain());
		
		assertEquals(1, cDiff.size());
		assertEquals(failChainElement, cDiff.get(0));
	}
	
	/**
	 * This is a bug which appear when the last failChainElement is not call, so it Iterator 
	 * Index is at -1, but the success is set to 0. But in this case the difference should 
	 * not exclude this failElement
	 */
	@Test
	public void whenAChainElementIsNotCalledIterationShouldNotBeConsidered(){
		ChainElementImpl successChainElement = new ChainElementImpl("1", "variable", "description");
		successChainElement.setIteration(0);
		ChainElementImpl failChainElement = new ChainElementImpl("1", "variable", "description");
		
		CauseEffectChainImpl ce1 = new CauseEffectChainImpl();
		ce1.addElement(successChainElement);
		
		CauseEffectChainImpl ce2 = new CauseEffectChainImpl();
		ce2.addElement(failChainElement);
		List<ChainElement> cDiff = DeltaDebug.difference(ce1.getChain(), ce2.getChain());
		
		assertEquals(0, cDiff.size());
	}

}
