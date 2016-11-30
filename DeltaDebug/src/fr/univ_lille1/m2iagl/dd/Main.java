package fr.univ_lille1.m2iagl.dd;

import fr.univ_lille1.m2iagl.challenge1.Challenge1;

public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		DDebuggerImpl ddebugger = new DDebuggerImpl();
		Challenge1 challenge1 = new Challenge1();
		ddebugger.debug(challenge1);
	}

}
