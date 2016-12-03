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
		
		try {
			Interpreter interpreter = new Interpreter();
	
			
			//http://www.beanshell.org/javadoc/bsh/Interpreter.html
			
			 Interpreter bsh = new Interpreter();

             // Evaluate statements and expressions
             bsh.eval("foo=Math.sin(0.5)");
             bsh.eval("bar=foo*5; bar=Math.cos(bar);");
             bsh.eval("for(i=0; i<10; i++) { print(\"hello\"); }");
             // same as above using java syntax and apis only
             bsh.eval("for(int i=0; i<10; i++) { System.out.println(\"hello\"); }");


             // Use set() and get() to pass objects in and out of variables
             bsh.set( "date", new Date() );
             Date date = (Date)bsh.get( "date" );
             // This would also work:
             Date date2 = (Date)bsh.eval( "date" );

             bsh.eval("year = date.getYear()");
             Integer year = (Integer)bsh.get("year");  // primitives use wrappers
			
			
			//interpreter.eval(getJavaProgram());
			
		} catch (EvalError e) {
		}
		
		return Oracle1.division(input);
	} 

	@Override
	public String getJavaProgram() {
		String program = "";

		program += "public static boolean division(int i){";
		program += "int j = 5;";
		program += "i = i + 2;";
		program += "i = i - j;";
		program += "i = i * 2;";

		program += "try{";
		program += "int result = j / i;";
		program += "return true;";
		program += "}catch(ArithmeticException e){";
		program += "	return false;";
		program += "}";
		program += "}";
		
		return program;
	}

}
