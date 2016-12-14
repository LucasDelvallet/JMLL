package fr.univ_lille1.m2iagl.challenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

public class Challenge4 implements Challenge<Integer> {

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
		input = input + 1;
		if(input == 4){
			input = null;
		}
		input += j;
		
		return input;
	}

	@Override
	public void challenge(Integer input) {
		int j = 5;
		input = input + 1;
		if(input == 4){
			input = null;
		}
		input += j;
	}
}
