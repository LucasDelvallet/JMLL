package fr.univ_lille1.m2iagl.spoon.processor;

import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtVariableReference;
import spoon.support.reflect.reference.CtVariableReferenceImpl;


public class VariableProcessor extends AbstractProcessor<CtVariable<Integer>> {
	
	@Override
	public boolean isToBeProcessed(CtVariable<Integer> candidate) {
		return candidate instanceof CtVariable;
	}

	@Override
	public void process(CtVariable<Integer> candidate) {
	//	if (!(candidate instanceof CtVariableAccess<Integer>)) {
	//		return;
	//	}
		
		CtVariable<Integer> op = (CtVariable<Integer>)candidate;
		
		//Launcher spoon = new Launcher();
		//Factory factory = spoon.createFactory();
		//CtExpression a = factory.Core().createLiteral().setValue(42);
		//op.setAssignment(a);	
		
		ChainElementImpl ce = new ChainElementImpl(String.valueOf(op.getPosition().getLine()), op.getSimpleName().toString(), "Declared with value : " + op.getDefaultExpression());
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);
		//System.out.println("Line : " + op.getPosition().getLine() + " | " + op.getSimpleName() + " = " + op.getDefaultExpression());
	}
}
