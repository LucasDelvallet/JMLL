package fr.univ_lille1.m2iagl.spoon;

import spoon.Launcher;
import spoon.processing.ProcessingManager;
import spoon.reflect.factory.Factory;
import spoon.support.QueueProcessingManager;

public class Main {

	public static void main(String[] args) {
		

		//VariableProcessor variableProcessor = new VariableProcessor();

		
		final String[] argsS = {
				"-i", "src/fr/univ_lille1/m2iagl/spoon/TestVariable.java",
				"-o", "target/spooned/"
		};

		final Launcher launcher = new Launcher();
		launcher.setArgs(argsS);
		launcher.run();

		final Factory factory = launcher.getFactory();
		final ProcessingManager processingManager = new QueueProcessingManager(factory);
		final VariableProcessor processor = new VariableProcessor();
		processingManager.addProcessor(processor);
		processingManager.process(factory.Class().getAll());

		//assertEquals(2, processor..size());

	}

}
