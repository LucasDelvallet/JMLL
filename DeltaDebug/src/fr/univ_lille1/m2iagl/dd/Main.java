package fr.univ_lille1.m2iagl.dd;

import java.io.File;
import java.io.PrintWriter;

import fr.univ_lille1.m2iagl.challenge1.Challenge1;
import spoon.Launcher;

public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		 DDebuggerImpl ddebugger = new DDebuggerImpl();
		 Challenge1 challenge1 = new Challenge1();

		 //CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge1);
		 //cEC.print();
		
		ddebugger.writeChallenge(challenge1);
		 
		CauseEffectChainSingleton.getInstance().resetCauseEffectChain();

		final String[] argsS = { "--precompile", "-i",
				"src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java", "-o", "target/spooned/",
				"-p", "fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor" + File.pathSeparator
						+ "fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor" };

		final Launcher launcher = new Launcher();
		launcher.setArgs(argsS);
		launcher.run();

		org.eclipse.jdt.internal.compiler.batch.Main.compile(
				org.eclipse.jdt.internal.compiler.batch.Main.tokenize("-1.6 target/spooned/"),
				new PrintWriter(System.out), new PrintWriter(System.err), null);

		CauseEffectChainSingleton.getInstance().getCauseEffectChain().print();
	}

}
