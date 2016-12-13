package fr.univ_lille1.m2iagl.challenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

public class Challenge2 implements Challenge<Integer> {

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
		  int k = input * 2;
		  
		  int result = j / k;
		  return result;
	}

	@Override
	public void challenge(Integer input) {
		  int j = 5;
		  input = input + 2; 
		  input = input - j;
		  int k = input * 2;
		  int result = j / k;
	} 
}
