package fr.univ_lille1.m2iagl.dd;

import java.util.List;

public class DDebuggerImpl<T> implements DDebugger<T> {

	@Override
	public CauseEffectChain debug(Challenge<T> c) {
		
		List<T> inputs = c.getInputs();
				
		DeltaDebug.ddmin(inputs, c);
		
		for(int i = 0; i < inputs.size(); i++){
			System.out.println("Input " + (i+1) + " valeur: " + inputs.get(i));
			System.out.println("Résultat : " +  c.oracle(inputs.get(i)) + "\r\n");
			
		}
		
		
		return null;
	}

}
