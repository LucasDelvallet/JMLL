package fr.univ_lille1.m2iagl.dd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Challenge1 implements Challenge<List<Integer>> {

	@Override
	public Class getInputFormat() {
		return Integer.class;
	}

	@Override
	public List<Integer> getInput1() {
		List<Integer> list = new ArrayList<Integer>();
		Collections.addAll(list, 1, 2, 3, 4, 5, 6);
		return list;
	}

	@Override
	public List<Integer> getInput2() {
		List<Integer> list = new ArrayList<Integer>();
		Collections.addAll(list, 10, 9, 8, 7, 6, 5);
		return list;
	}

	@Override
	public boolean oracle(List<Integer> input) {
		if (input.contains(1) && input.contains(3)) {
			return false;
		}
		return true;
	}

	@Override
	public CauseEffectChain computeTrace(List<Integer> x, List<Integer> y) {
		return null;
	}

}
