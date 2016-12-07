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


public class VariableProcessor {
	
	public static Object process(Object e) {
		CtVariable op = (CtVariable) e;

		ChainElementImpl ce = new ChainElementImpl(String.valueOf(op.getPosition().getLine() - 2),
				op.getSimpleName().toString(), " Déclaration : " + op.getDefaultExpression() + "  // Valeur : ");
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);
		return op;
	}
}
