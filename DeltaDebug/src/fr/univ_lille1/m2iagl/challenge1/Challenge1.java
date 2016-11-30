package fr.univ_lille1.m2iagl.challenge1;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.Challenge;

public class Challenge1 implements Challenge<Integer>{

	@Override
	public Class<? extends Integer> getInputFormat() {
		// TODO Auto-generated method stub
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
	public boolean oracle(Integer input) {
	
		//Ici on devrait charger le .class.
		//Pour le moment, on fait une instance normale.
		return Oracle1.division(input);
	}

	@Override
	public String getJavaProgram() {
		
		return null;
	}

}
