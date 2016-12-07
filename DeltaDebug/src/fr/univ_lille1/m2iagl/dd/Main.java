package fr.univ_lille1.m2iagl.dd;

import org.mdkt.compiler.InMemoryJavaCompiler;

import fr.univ_lille1.m2iagl.challenge1.Challenge1;
import fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor;
import fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor;
import fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge;
import spoon.Launcher;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		try {

			DDebuggerImpl ddebugger = new DDebuggerImpl();
			Challenge1 challenge1 = new Challenge1();
			
			CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge1);
			cEC.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
