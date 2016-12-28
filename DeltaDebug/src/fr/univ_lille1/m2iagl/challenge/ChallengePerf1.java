package fr.univ_lille1.m2iagl.challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengePerf1 implements Challenge<Integer> {

	@Override
	public Class<? extends Integer> getInputFormat() {
		return Integer.class;
	}

	@Override
	public List<Integer> getInputs() {
		List<Integer> inputs = new ArrayList<Integer>();
		inputs.add(42); // Success
		inputs.add(-49); // Fail
		return inputs;
	}

	@Override
	public Object doIt(Integer input) {
		int a = input;
		int b = input;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		int c = b/a;
		return c;
	}

	@Override
	public void challenge(Integer input) {
		int a = input;
		int b = input;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		b *= 5;
		a++;
		int c = b/a;
	}

}
