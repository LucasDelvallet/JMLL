package fr.univ_lille1.m2iagl.challenge1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.Challenge;
import bsh.EvalError;
import bsh.Interpreter;

public class Challenge1 implements Challenge<Integer> {

	@Override
	public Class<? extends Integer> getInputFormat() {
		return Integer.class;
	}

	@Override
	public List<Integer> getInputs() {
		List<Integer> inputs = new ArrayList<Integer>();
		inputs.add(5); // Success
		inputs.add(3); // Fail
		return inputs;
	}
	
	@Override
	public Object doIt(Integer input) {
		int j = 5;
		input = input + 2;
		input = input - j;
		input = input * 2;
		
		int result = j / input;
		return result;
	}

	@Override
	public void challenge(Integer input) {
		int j = 5;
		input = input + 2;
		input = input - j;
		input = input * 2;
		int result = j / input;
	} 

	@Override
	public String getJavaProgram() {
		String program = String.join("\n"
				 ,"public void challenge(Integer input){"
		         ,	"Integer j = 5;"
		         ,	"input = input + 2;"
		         ,	"input = input - j;"
		         ,	"input = input * 2;"
		         ,	"Integer result = j / input;"
		         ,"}"
		);
		
		return program;
	}



}
