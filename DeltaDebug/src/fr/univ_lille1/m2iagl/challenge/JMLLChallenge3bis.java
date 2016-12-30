package fr.univ_lille1.m2iagl.challenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

public class JMLLChallenge3bis implements Challenge<String> {

	@Override
	public Class<? extends String> getInputFormat() {
		return String.class;
	}

	@Override
	public List<String> getInputs() {
		List<String> inputs = new ArrayList<String>();
		inputs.add("Bonjour, je suis un programme et je fonctionne correctement :-)"); // Success
		inputs.add("Fail"); // Fail
		return inputs;
	}

	@Override
	public Object doIt(String input) {
		int i = 0;
		
		String retour = "";
		do{
			retour = retour + input.substring(i, i+1);
			i++;
		}while(i != 5);
		return retour;
	}

	@Override
	public void challenge(String input) {
		int i = 0;
		
		String retour = "";
		do{
			retour = retour + input.substring(i, i+1);
			i++;
		}while(i != 5);
	}

}
