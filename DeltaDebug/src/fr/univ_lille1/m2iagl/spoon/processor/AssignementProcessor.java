package fr.univ_lille1.m2iagl.spoon.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChainImpl;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import spoon.Launcher;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtOperatorAssignment;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.declaration.CtMethodImpl;

public class AssignementProcessor {

	public static CtMethod transform(CtMethod c) {
		for (Object e : c.getElements(new TypeFilter(CtAssignment.class))) {
			CtElement temp = process(e);
			((CtElement)e).replace(temp);
		}
		return c;
	}

	public static CtElement process(Object e) {
		CtAssignment op = (CtAssignment) e;

		if(op instanceof CtOperatorAssignment){
			return processOperatorAssignement((CtOperatorAssignment)e);
		}else{
			return processAssignement(op);
		}
	}
	
	private static CtElement processAssignement(CtAssignment op) {
		
		Launcher spoon = new Launcher();
		Factory factory = spoon.createFactory();

		int line = op.getPosition().getLine() - 3;
		CauseEffectChainImpl cSuccess = CauseEffectChainSingleton.getInstance().getSuccessCauseEffectChain();
		for (ChainElement c : cSuccess.getChain()) {
			if (Integer.parseInt(c.getLine()) == line && c.getDescription().equals("Assignement")) {
				op.setAssignment(factory.Core().createLiteral().setValue(((ChainElementImpl)c).getValue()));
			}
		}

		List<CtExpression<?>> argsL = new ArrayList<CtExpression<?>>();
		argsL.add(op.getAssignment());
		argsL.add(factory.Core().createLiteral().setValue("Assignement"));
		CtInvocation a = factory.Core().createInvocation().setArguments(argsL);

		Collection<CtExecutableReference<?>> allExecutables = op.getParent(CtExecutable.class).getParent(CtClass.class)
				.getAllMethods();
		int index = -1;
		List<CtMethodImpl> listctEx = new ArrayList(allExecutables);
		for (CtMethodImpl ctEx : listctEx) {
			index++;
			if (ctEx.getSimpleName().equals("debug")) {
				a.setExecutable(listctEx.get(index).getReference());
				op.setAssignment(a);
			}
		}

		ChainElementImpl ce = new ChainElementImpl(String.valueOf(line), op.getAssigned().toString(), "Assignement");
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);

		return op;
	}
	
	private static CtElement processOperatorAssignement(CtOperatorAssignment op) {
		Launcher spoon = new Launcher();
		Factory factory = spoon.createFactory();

		int line = op.getPosition().getLine() - 3;
		CauseEffectChainImpl cSuccess = CauseEffectChainSingleton.getInstance().getSuccessCauseEffectChain();
		for (ChainElement c : cSuccess.getChain()) {
			if (Integer.parseInt(c.getLine()) == line && c.getDescription().equals("Operator Assignement")) {
				CtAssignment ctA = factory.Core().createAssignment().setAssigned(op.getAssigned());
				ctA.setAssignment(factory.Core().createLiteral().setValue(((ChainElementImpl)c).getValue()));
				
				op.replace((CtStatement)ctA);
			}
		}
		
		Collection<CtExecutableReference<?>> allExecutables = op.getParent(CtExecutable.class).getParent(CtClass.class)
				.getAllMethods();
		
		List<CtExpression<?>> argsL = new ArrayList<CtExpression<?>>();
		argsL.add(op.clone());
		argsL.add(factory.Core().createLiteral().setValue("Operator Assignement"));
		CtInvocation a = factory.Core().createInvocation();

		
		int index = -1;
		List<CtMethodImpl> listctEx = new ArrayList(allExecutables);
		for (CtMethodImpl ctEx : listctEx) {
			index++;
			if (ctEx.getSimpleName().equals("debug")) {
				a.setExecutable(listctEx.get(index).getReference());
				a.setArguments(argsL);
			}
		}

		ChainElementImpl ce = new ChainElementImpl(String.valueOf(line), op.getAssigned().toString(), "Operator Assignement");
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);
		
		
		return a;
	}
}
