package fr.univ_lille1.m2iagl.dd;

import fr.univ_lille1.m2iagl.challenge.*;


public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		try {

			DDebuggerImpl ddebugger = new DDebuggerImpl();
			Challenge3 challenge = new Challenge3();
			
			CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
			cEC.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
