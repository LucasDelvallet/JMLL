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
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.declaration.CtMethodImpl;

public class UnaryOperatorProcessor {

	public static CtMethod transform(CtMethod c) {
		for (Object e : c.getElements(new TypeFilter(CtUnaryOperator.class))) {
			CtElement temp = process(e);
			((CtElement)e).replace(temp);
		}
		return c;
	}

	public static CtElement process(Object e) {
		CtUnaryOperator op = (CtUnaryOperator) e;

		Launcher spoon = new Launcher();
		Factory factory = spoon.createFactory();

		int line = op.getPosition().getLine() - 3;
		/*CauseEffectChainImpl cDiff = CauseEffectChainSingleton.getInstance().getDiffCauseEffectChain();
		for (ChainElement c : cDiff.getChain()) {
			if (Integer.parseInt(c.getLine()) == line) {
				op.setAssignment(factory.Core().createLiteral().setValue(((ChainElementImpl)c).getValue()));
			}
		}*/
		Collection<CtExecutableReference<?>> allExecutables = op.getParent(CtExecutable.class).getParent(CtClass.class)
				.getAllMethods();
		
		List<CtExpression<?>> argsL = new ArrayList<CtExpression<?>>();
		argsL.add(op.clone());
		argsL.add(factory.Core().createLiteral().setValue("Unary operation"));
		CtInvocation a = factory.Core().createInvocation(); //.setArguments(argsL);

		
		int index = -1;
		List<CtMethodImpl> listctEx = new ArrayList(allExecutables);
		for (CtMethodImpl ctEx : listctEx) {
			index++;
			if (ctEx.getSimpleName().equals("debug")) {
				a.setExecutable(listctEx.get(index).getReference());
				a.setArguments(argsL);
			}
		}

		ChainElementImpl ce = new ChainElementImpl(String.valueOf(line), op.getOperand().toString(), "Unary operation");
		CauseEffectChainSingleton.getInstance().getCauseEffectChain().addElement(ce);

		return a;
	}
}
