package fr.univ_lille1.m2iagl.dd;

import static javafx.scene.input.KeyCode.T;

/**
 * Defines a challenge asked to the others
 */
public interface Challenge<I> {
	Class<? extends I> getInputFormat();
	I getInput1();
	I getInput2();
	boolean oracle(I input);
	CauseEffectChain computeTrace(I x, I y);
}
