package fr.univ_lille1.m2iagl.dd;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple Java implementation of Andreas Zeller's Delta Debugging algorithm
 * 
 * Reference: https://www.st.cs.uni-saarland.de/whyprogramsfail/code/dd/DD.java
 * 
 * @author Ben Holland
 */
public class DeltaDebug {

	/**
	 * Given an input that causes a failing/error condition, this implementation of a divide and conquer
	 * algorithm splits the input data into smaller chunks and checks if the smaller input reproduces 
	 * the failing/error condition with a smaller input.

	 * @param inputs The pre-chunked test input to reduce
	 * @param harness A test harness implementation that returns true (pass) or false (fail/error) for a given input
	 * 
	 * @return A minimized input that reproduces the failing/error condition
	 */
	public static <T> CauseEffectChain ddmin(CauseEffectChain causeEffectChain, T inputFail, Challenge<T> c) {
		
		List<ChainElement> chainElements = causeEffectChain.getChain();
		
		int n = 2;
		while(chainElements.size() >= 2) {
			List<List<ChainElement>> subsets = split(chainElements, n);
			boolean complementFailing = false;
			
			for(List<ChainElement> subset : subsets){
				List<ChainElement> complement = difference(chainElements, subset);
				//Ici on donne à nourir l'oracle avec les inputs de base qui fail.
				//Mais il faut changer le fonctionnement de celui ci
				//avec les valeurs qu'ont changé, elles sont dans le complement
				if (!c.oracle(inputFail)) {
					chainElements = complement;
					n = Math.max(n - 1, 2);
					complementFailing = true;
					break;
				}
				n = n + 0;
			}

			if (!complementFailing) {
				if (n == chainElements.size()) {
					break;
				}
			
				n = Math.min(n * 2, chainElements.size());
			}
		}

		return new CauseEffectChainImpl(chainElements);
	}

	/**
	 * Returns the difference of set a and set b
	 * @param a
	 * @param b
	 * @return
	 */
	private static <T> List<T> difference(List<T> a, List<T> b) {
		List<T> result = new LinkedList<T>();
		result.addAll(a);
		result.removeAll(b);
		return result;
	}

	/**
	 * Splits the input set s into n subsets
	 * @param s
	 * @param n
	 * @return
	 */
	private static <T> List<List<T>> split(List<T> s, int n) {
		List<List<T>> subsets = new LinkedList<List<T>>();
		int position = 0;
		for (int i = 0; i < n; i++) {
			List<T> subset = s.subList(position, position + (s.size() - position) / (n - i));
			subsets.add(subset);
			position += subset.size();
		}
		return subsets;
	}

}
