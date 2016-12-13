package fr.univ_lille1.m2iagl.challenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

public class Challenge3 implements Challenge<String> {

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
		int i = 5;
		input = input.toLowerCase();
		String retour = "";
		for (int j = 0; j < i; j++) {
			retour = retour + input.substring(j, j + 1);
		}
		return retour;
	}

	@Override
	public void challenge(String input) {
		int i = 5;
		input = input.toLowerCase();
		String retour = "";
		for (int j = 0; j < i; j++) {
			retour = retour + input.substring(j, j + 1);
		}
	}

	@Override
	public String getJavaProgram() {
		String program = String.join("\n"
				 ,"public void challenge(String input){"
				 ,"int i = 5;"
				 ,"input = input.toLowerCase();"
				 ,"String retour = \"\";"
				 ,"for (int j = 0; j < i; j++) {"
				 ,"retour = retour + input.substring(j, j + 1);"
				 ,"}"
				 
		         ,"}"
		);
		
		return program;
	}

}
