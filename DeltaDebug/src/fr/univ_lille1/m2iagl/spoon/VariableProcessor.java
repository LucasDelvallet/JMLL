package fr.univ_lille1.m2iagl.spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtVariableReference;


public class VariableProcessor extends AbstractProcessor<CtVariableAccess<Integer>> {
	
	@Override
	public boolean isToBeProcessed(CtVariableAccess<Integer> candidate) {
		return candidate instanceof CtVariableAccess;
	}

	@Override
	public void process(CtVariableAccess<Integer> candidate) {
	//	if (!(candidate instanceof CtVariableAccess<Integer>)) {
	//		return;
	//	}
		
		CtVariableAccess<Integer> op = (CtVariableAccess<Integer>)candidate;
		CtVariableReference<Integer> a  = op.getVariable();
		System.out.println(op + " Line : " + op.getPosition().getLine());
	}
}
