package fr.univ_lille1.m2iagl.dd;

import fr.univ_lille1.m2iagl.challenge1.Challenge1;

public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		Challenge1 challenge1 = new Challenge1();
		
		CauseEffectChain cEC = ddebugger.debug(challenge1);
		//Ici faudrait print la cEC, c'est la trace qui affiche l'origine du bug.
	}

}
