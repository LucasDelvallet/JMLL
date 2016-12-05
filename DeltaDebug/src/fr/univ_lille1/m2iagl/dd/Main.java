package fr.univ_lille1.m2iagl.dd;

import java.util.List;

import fr.univ_lille1.m2iagl.challenge1.Challenge1;
import fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor;
import junit.framework.Assert;
import spoon.reflect.declaration.CtClass;

public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		 DDebuggerImpl ddebugger = new DDebuggerImpl();
		 Challenge1 challenge1 = new Challenge1();

		 CauseEffectChain cEC = ddebugger.debug(challenge1);
		// Ici faudrait print la cEC, c'est la trace qui affiche l'origine du bug.

	}

}
