package fr.univ_lille1.m2iagl.dd;

import java.util.ArrayList;

public class DDebuggerImpl2<I> implements DDebugger<I> {

	@Override
	public CauseEffectChain debug(Challenge<I> c) {
		// 1 - Get Right/Wrong Inputs to CauseEffectChain
		ArrayList<I> rightInputs = new ArrayList<>();
		ArrayList<I> wrongInputs = new ArrayList<>();
		
		for(I input : c.getInputs()) {
			if(c.oracle(input)) {
				rightInputs.add(input);
			} else {
				wrongInputs.add(input);
			}
		}
		
		// 2 - TODO
		return null;
	}

}
