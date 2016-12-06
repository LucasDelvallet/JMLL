package fr.univ_lille1.m2iagl.dd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

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
			return null; // Y'a pas d'input qui fait Fail ou Success
		}

		writeChallenge(c);
		
		return DeltaDebug.ddmin(inputFail, inputSucess, c);
	}

	public void writeChallenge(Challenge<T> c){
		try {
			List<String> lines = new ArrayList<String>();
			lines.add("package fr.univ_lille1.m2iagl.spoon.templatechallenge;");
			lines.add("public class TemplateChallenge<T> implements ITemplateChallenge<T>{");
			lines.add(c.getJavaProgram());
			lines.add("}");

			Files.write(Paths.get("src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java"), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
