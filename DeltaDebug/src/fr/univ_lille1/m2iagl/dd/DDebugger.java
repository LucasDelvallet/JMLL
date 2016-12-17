package fr.univ_lille1.m2iagl.dd;

import fr.univ_lille1.m2iagl.challenge.Challenge;

public interface DDebugger<T> {
	CauseEffectChain debug(Challenge<T> c);
}
