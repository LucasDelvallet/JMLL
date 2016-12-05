package fr.univ_lille1.m2iagl.spoon;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.ChainElement;
import spoon.Launcher;
import spoon.processing.ProcessingManager;
import spoon.reflect.factory.Factory;
import spoon.support.QueueProcessingManager;

public class Main {

	public static void main(String[] args) {
		

		//VariableProcessor variableProcessor = new VariableProcessor();

		
		final String[] argsS = {
				"--precompile",
				"-i", "src/fr/univ_lille1/m2iagl/challenge1/Oracle1.java",
				"-o", "target/spooned/",
				"-p", "fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor"+File.pathSeparator+"fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor"
		};

		final Launcher launcher = new Launcher();
		launcher.setArgs(argsS);
		launcher.run();

		CauseEffectChainSingleton.getInstance().getCauseEffectChain().print();

		
		//org.eclipse.jdt.internal.compiler.batch.Main.compile(org.eclipse.jdt.internal.compiler.batch.Main.tokenize("-1.6 target/spooned/"), new PrintWriter(System.out), new PrintWriter(System.err), null);
	}

}