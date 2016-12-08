package fr.univ_lille1.m2iagl.spoon.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.ChainElement;
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
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtVariableReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.declaration.CtMethodImpl;
import spoon.support.reflect.reference.CtVariableReferenceImpl;


public class AssignementProcessor {
	
	public static Object process(Object e) {
		CtAssignment op = (CtAssignment) e;
		
		Launcher spoon = new Launcher();
		Factory factory = spoon.createFactory();
		
		int line = op.getPosition().getLine() - 2;
		CauseEffectChainImpl cDiff = CauseEffectChainSingleton.getInstance().getDiffCauseEffectChain();
		for(ChainElement c : cDiff.getChain()){
			if(Integer.parseInt(c.getLine()) == line){
				op.setAssignment(factory.Core().createLiteral().setValue(c.getDescription()));
			}
		}
		

		List<CtExpression<?>> argsL = new ArrayList<CtExpression<?>>();
		argsL.add(op.getAssignment());
		CtInvocation a = factory.Core().createInvocation().setArguments(argsL);

		Collection<CtExecutableReference<?>> allExecutables = op.getParent(CtExecutable.class)
				.getParent(CtClass.class).getAllMethods();
		int index = -1;
		List<CtMethodImpl> listctEx = new ArrayList(allExecutables);
		for(CtMethodImpl ctEx : listctEx){
			index++;
			if(ctEx.getSimpleName().equals("debug")){
				a.setExecutable(listctEx.get(index).getReference());
				op.setAssignment(a);
			}
		}
		
		ChainElementImpl ce = new ChainElementImpl(String.valueOf(line), op.getAssigned().toString(), "");
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);	
	
		return op;
	}
}
