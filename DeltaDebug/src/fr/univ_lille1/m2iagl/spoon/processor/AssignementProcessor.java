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
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.declaration.CtMethodImpl;

public class AssignementProcessor {

	public static CtMethod transform(CtMethod c) {
		for (Object e : c.getElements(new TypeFilter(CtAssignment.class))) {
			e = process(e);
		}
		return c;
	}

	public static Object process(Object e) {
		CtAssignment op = (CtAssignment) e;

		Launcher spoon = new Launcher();
		Factory factory = spoon.createFactory();

		int line = op.getPosition().getLine() - 3;
		CauseEffectChainImpl cDiff = CauseEffectChainSingleton.getInstance().getDiffCauseEffectChain();
		for (ChainElement c : cDiff.getChain()) {
			if (Integer.parseInt(c.getLine()) == line) {
				op.setAssignment(factory.Core().createLiteral().setValue(((ChainElementImpl)c).getValue()));
			}
		}

		List<CtExpression<?>> argsL = new ArrayList<CtExpression<?>>();
		argsL.add(op.getAssignment());
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
}
