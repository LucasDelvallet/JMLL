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
		inputs.add(10); // Success
		inputs.add(99999999); // Success
		inputs.add(12); // Success
		inputs.add(-51); // Success
		inputs.add(18); // Success
		inputs.add(42); // Success
		inputs.add(3); // Fail
		inputs.add(50); // Success
		inputs.add(500); // Success
		inputs.add(9001); // Success
		return inputs;
	}

	@Override
	public Object doIt(Integer input) {
		int i = 5;
		int j = 5;
		int k = 5;
		input += 7;
		i += j + 3;
		i *= k;
		j *= 2;
		input = input - j;
		int l = input * 2;
		int result = j / l;
		return result;
	}

	@Override
	public void challenge(Integer input) {
		int i = 5;
		int j = 5;
		int k = 5;
		input += 7;
		i += j + 3;
		i *= k;
		j *= 2;
		input = input - j;
		int l = input * 2;
		int result = j / l;
	}
}
