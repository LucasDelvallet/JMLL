package fr.univ_lille1.m2iagl.challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengePerf2 implements Challenge<Integer> {

	@Override
	public Class<? extends Integer> getInputFormat() {
		return Integer.class;
	}

	@Override
	public List<Integer> getInputs() {
		List<Integer> inputs = new ArrayList<Integer>();
		inputs.add(42); // Success
		inputs.add(98); // Fail
		return inputs;
	}

	@Override
	public Object doIt(Integer input) {
		int a = input;
		for(int c = 1; c<99; c++) {
			a--;
		}
		int res = 1/a;
		return res;
	}

	@Override
	public void challenge(Integer input) {
		int a = input;
		for(int c = 1; c<99; c++) {
			a--;
		}
		int res = 1/a;
	}

}
