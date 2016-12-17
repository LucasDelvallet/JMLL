package fr.univ_lille1.m2iagl.dd;

import fr.univ_lille1.m2iagl.challenge.Challenge;
import fr.univ_lille1.m2iagl.spoon.processor.ChallengeProcessor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DDebuggerImpl<T> implements DDebugger<T> {

	@Override
	public CauseEffectChain debug(Challenge<T> c) {
		T inputFail = null;
		T inputSucess = null;

		for (T input : c.getInputs()) {
			try{
				c.challenge(input);
				inputSucess = input;
			}catch(Exception e){
				inputFail = input;
			}
		}
		if (inputFail == null || inputSucess == null) {
			System.out.println("No failing or successing input. Sorry, i will crash now.");
			return null;
		}

		writeChallenge(c);
		displayChallenge(c);
		
		return DeltaDebug.ddmin(inputFail, inputSucess, c);
	}

	public void writeChallenge(Challenge<T> c){
		try {
			List<String> challenge = ChallengeProcessor.getChallengeAsString(c);
			Files.write(Paths.get("src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java"), challenge , Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayChallenge(Challenge<T> c)
	{
		int i = 0;
		boolean displayLine = false;

		System.out.println("-- The challenge --");
		System.out.println("-------------------");
		System.out.println(++i + "\t" + "@Override");
		System.out.println(++i + "\t" + "public void challenge("+c.getInputFormat().getSimpleName()+" input)");

		for (String line : ChallengeProcessor.process(c.getClass().getSimpleName()).split("\n")) {
			System.out.print(++i + "\t" + line);
		}
		System.out.println("\n\n-------------------\n\n");
	}
	

}
