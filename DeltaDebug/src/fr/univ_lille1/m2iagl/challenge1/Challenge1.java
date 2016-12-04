package fr.univ_lille1.m2iagl.challenge1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.Challenge;
import bsh.EvalError;
import bsh.Interpreter;

public class Challenge1 implements Challenge<Integer> {

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
		return Oracle1.division(input);
	} 

	@Override
	public String getJavaProgram() {
		String program = String.join("\n"
				 ,"public static boolean division(int i){"
		         ,	"int j = 5;"
		         ,	"i = i + 2;"
		         ,	"i = i - j;"
		         ,	"i = i * 2;"
		         ,	"try{"
		         ,		"int result = j / i;"
		         ,		"return true;"
		         ,	"}catch(ArithmeticException e){"
		         ,		"return false;"
		         ,	"}"
		         ,"}"
		);
		
		return program;
	}

}
