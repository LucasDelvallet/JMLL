package fr.univ_lille1.m2iagl.dd;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;
import fr.univ_lille1.m2iagl.challenge.Challenge;
import fr.univ_lille1.m2iagl.spoon.processor.ChallengeProcessor;

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
	

}
