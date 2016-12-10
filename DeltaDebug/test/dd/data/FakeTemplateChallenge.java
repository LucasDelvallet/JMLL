package dd.data;

import fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge;

public class FakeTemplateChallenge implements ITemplateChallenge<Integer> {

	@Override
	public void challenge(Integer input) {
		if(input == 42) {
			return;
		} else {
			throw new RuntimeException();
		}
	}

}
