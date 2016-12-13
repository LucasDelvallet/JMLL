package fr.univ_lille1.m2iagl.spoon.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtVariableReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.declaration.CtMethodImpl;
import spoon.support.reflect.reference.CtVariableReferenceImpl;


public class VariableProcessor {
	
	public static CtMethod transform(CtMethod c) {
		for (Object e : c.getElements(new TypeFilter(CtLocalVariable.class))) {
			e = process(e);
		}
		return c;
	}
	
	public static Object process(Object e) {
		CtLocalVariable op = (CtLocalVariable) e;

		Launcher spoon = new Launcher();
		Factory factory = spoon.createFactory();
		List<CtExpression<?>> argsL = new ArrayList<CtExpression<?>>();
		argsL.add(op.getDefaultExpression());
		CtInvocation a = factory.Core().createInvocation().setArguments(argsL);
		Collection<CtExecutableReference<?>> allExecutables = op.getParent(CtExecutable.class)
				.getParent(CtClass.class).getAllMethods();
		int index = -1;
		List<CtMethodImpl> listctEx = new ArrayList(allExecutables);
		for(CtMethodImpl ctEx : listctEx){
			index++;
			if(ctEx.getSimpleName().equals("debug")){
				a.setExecutable(listctEx.get(index).getReference());
				op.setDefaultExpression(a);
			}
		}
		
		ChainElementImpl ce = new ChainElementImpl(String.valueOf(op.getPosition().getLine() - 3),
				op.getSimpleName().toString(), "");
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);
		return op;
	}
}
