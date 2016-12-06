package data.challenges.divideByZero;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.Challenge;
/*
public class DivideByZeroChallenge implements Challenge<DivideByZeroInput> {

	@Override
	public Class<? extends DivideByZeroInput> getInputFormat() {
		return DivideByZeroInput.class;
	}

	@Override
	public List<DivideByZeroInput> getInputs() {
		ArrayList<DivideByZeroInput> inputs = new ArrayList<>();
		inputs.add(new DivideByZeroInput(4, 5)); // OK
		inputs.add(new DivideByZeroInput(4, 0)); // Fail
		return inputs;
	}

	@Override
	public boolean oracle(DivideByZeroInput input) {
		try {
			DivideByZero.main(new String[] {Integer.toString(input.getDividend()), Integer.toString(input.getDivider())});
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public String getJavaProgram() {
		StringBuilder program = new StringBuilder();
		program.append("package data.challenges.divideByZero;" + "\n");
		program.append("\n");
		program.append("public class DivideByZero {" + "\n");
		program.append("public static void main(String[] args) {" + "\n");
		program.append("int a = Integer.parseInt(args[0]);" + "\n");
		program.append("int b = Integer.parseInt(args[1]);" + "\n");
		program.append("System.out.println(a, b);" + "\n");
		program.append("}" + "\n");
		program.append("}" + "\n");
		return program.toString();
	}
	
	

}*/
