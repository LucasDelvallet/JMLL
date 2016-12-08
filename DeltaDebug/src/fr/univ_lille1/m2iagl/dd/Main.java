package fr.univ_lille1.m2iagl.dd;

import fr.univ_lille1.m2iagl.challenge.*;


public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		try {

			DDebuggerImpl ddebugger = new DDebuggerImpl();
			Challenge2 challenge = new Challenge2();
			
			CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
			cEC.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
