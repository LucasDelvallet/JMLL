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
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtVariableReference;
import spoon.support.reflect.reference.CtVariableReferenceImpl;


public class AssignementProcessor extends AbstractProcessor<CtAssignment<Integer, Integer>> {
	
	@Override
	public boolean isToBeProcessed(CtAssignment<Integer, Integer> candidate) {
		return candidate instanceof CtAssignment;
	}

	@Override
	public void process(CtAssignment<Integer, Integer> candidate) {
		CtAssignment<Integer, Integer> op = (CtAssignment<Integer, Integer>)candidate;
		
		//TODO: On me demande de modifier la valeur
		//if()
		//{
			Launcher spoon = new Launcher();
			Factory factory = spoon.createFactory();
			CtExpression a = factory.Core().createLiteral().setValue(42);
			op.setAssignment(a);
		//}
		
		
		ChainElementImpl ce = new ChainElementImpl(String.valueOf(op.getPosition().getLine()), op.getAssigned().toString(), "Assigned to : " + op.getAssignment());
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);
	}
}
