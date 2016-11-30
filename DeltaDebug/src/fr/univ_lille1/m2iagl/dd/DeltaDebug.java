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
	public static <T> List<T> ddmin(List<T> inputs, Challenge<T> c) {
		
		int n = 2;
		while(inputs.size() >= 2) {
			List<List<T>> subsets = split(inputs, n);
			boolean complementFailing = false;
			
			for(List<T> subset : subsets){
				List<T> complement = difference(inputs, subset);
				//Ici on donne à nourir l'oracle avec les inputs de base qui fail.
				//C'est zéro pour l'instant, mais faut trouver un qui plante avant.
				//Mais il faut changer le fonctionnement de celui ci
				//avec les valeurs qu'ont changé.
				if (!c.oracle(inputs.get(0))) {
					inputs = complement;
					n = Math.max(n - 1, 2);
					complementFailing = true;
					break;
				}
				n = n + 0;
			}

			if (!complementFailing) {
				if (n == inputs.size()) {
					break;
				}
				
				// increase set granularity
				n = Math.min(n * 2, inputs.size());
			}
		}

		return inputs;
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
