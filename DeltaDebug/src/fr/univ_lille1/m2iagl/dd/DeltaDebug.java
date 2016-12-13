package fr.univ_lille1.m2iagl.dd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mdkt.compiler.InMemoryJavaCompiler;

import fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor;
import fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor;
import fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge;
import spoon.Launcher;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class DeltaDebug {
	private static final String CHALLENGE_FILE = "src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java";
	private static final String CHALLENGE_NAME = "TemplateChallenge";

	public static <T> boolean generateCauseEffectChain(T input, String challengeFile, String challengeName) {
		CauseEffectChainSingleton.getInstance().resetCauseEffectChain();

		// Create a TemplateChallenge launcher
		Launcher l = new Launcher();
		l.setArgs(new String[] {"-i", challengeFile});
		l.buildModel();

		CtClass foo = (CtClass) l.getFactory().Package().getRootPackage()
				.getElements(new NameFilter(challengeName)).get(0);

		CtMethod cChallenge = (CtMethod) foo.getElements(new TypeFilter(CtMethod.class)).get(0);

		// Apply transformations
		cChallenge = AssignementProcessor.transform(cChallenge);
		cChallenge = VariableProcessor.transform(cChallenge);

		// Create a new instance
		Class tCClass = null;
		ITemplateChallenge tC = null;
		try {
			tCClass = InMemoryJavaCompiler.compile(foo.getQualifiedName(),
					"package " + foo.getPackage().getQualifiedName() + ";" + foo.toString());
			tC = (ITemplateChallenge) tCClass.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Run to check success
		boolean hasSucceded = true;
		try {
			tC.challenge(input);
		} catch (Exception e1) {
			hasSucceded = false;
		}

		return hasSucceded;
	}

	public static <T> CauseEffectChain ddmin(T inputFail, T inputSuccess, Challenge<T> c) {
		CauseEffectChainImpl cECFail = new CauseEffectChainImpl();
		CauseEffectChainImpl cECSuccess = new CauseEffectChainImpl();

		generateCauseEffectChain(inputFail, CHALLENGE_FILE, CHALLENGE_NAME);
		cECFail.setChain(CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain());
		generateCauseEffectChain(inputSuccess, CHALLENGE_FILE, CHALLENGE_NAME);
		cECSuccess.setChain(CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain());
		List<ChainElement> cEsReturn = null;

		System.out.println("==== Chaine d'execution Input Success ====");
		((CauseEffectChainImpl) cECSuccess).print();

		System.out.println("\r\n==== Chaine d'execution Input Fail ====");
		((CauseEffectChainImpl) cECFail).print();

		System.out.println("\r\n==== Chaine de débogage ====");

		int n = 2;

		cEsReturn = difference(cECFail.getChain(), cECSuccess.getChain());
		while (cEsReturn.size() >= 2) {
			int start = 0;
			int subset_lenght = cEsReturn.size() / n;
			boolean some_complement_is_failing = false;

			while (start + subset_lenght < cEsReturn.size()) {
				List<ChainElement> complement = new ArrayList<ChainElement>();
				complement.addAll(cEsReturn.subList(0, start));
				complement.addAll(cEsReturn.subList(start + subset_lenght, cEsReturn.size()));

				CauseEffectChainSingleton.getInstance().getDiffCauseEffectChain().clearChainElements();
				CauseEffectChainImpl cDiff = CauseEffectChainSingleton.getInstance().getDiffCauseEffectChain();
				cDiff.setChain(complement);

				if (generateCauseEffectChain(inputFail, CHALLENGE_FILE, CHALLENGE_NAME)) {
					cEsReturn.clear();
					cEsReturn.addAll(complement);
					n = Math.max(n - 1, 2);
					some_complement_is_failing = true;
					break;
				}

				start += subset_lenght;
			}

			if (!some_complement_is_failing) {
				if (n == cEsReturn.size()) {
					break;
				}

				n = Math.min(n * 2, cEsReturn.size());
			}
		}
		
		for(int i = 0; i < cECFail.getChain().size(); i++){
			for(int j = 0; j < cEsReturn.size(); j++){
				ChainElementImpl ceF = (ChainElementImpl) cECFail.getChain().get(i);
				ChainElementImpl ceR = (ChainElementImpl) cEsReturn.get(j);
				if(ceF.getLine().equals(ceR.getLine()) && ceF.getVariable().equals(ceR.getVariable()) && ceF.getIteration() == ceR.getIteration()){
					ceR.setValue(ceF.getValue());
					cEsReturn.set(j, ceR);
				}
			}
		}
		
		
		if(((ChainElementImpl)cEsReturn.get(cEsReturn.size()-1)).getIteration() == 0){
			((ChainElementImpl)cEsReturn.get(cEsReturn.size()-1)).setValue("null");
		}
		
		
		return new CauseEffectChainImpl(cEsReturn);

	}

	public static List<ChainElement> difference(List<ChainElement> a, List<ChainElement> b) {
		List<ChainElement> result = new LinkedList<ChainElement>();
		
		List<Integer> li = new ArrayList<Integer>();

		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				ChainElementImpl aa = (ChainElementImpl)a.get(i);
				ChainElementImpl bb = (ChainElementImpl)b.get(j);

				if ((aa.getLine().equals(bb.getLine()) 
						&& aa.getVariable().equals(bb.getVariable()) 
						&& aa.getIteration() == bb.getIteration()
						&& aa.getValue() == null && bb.getValue() == null
						|| (aa.getValue() != null && bb.getValue() != null
						   && !aa.getValue().equals(bb.getValue()))
						)) {
					li.add(j);
					//result.add(aa);
					break;
				}
			}
		}
		
		for(int i = 0; i < a.size(); i++){
			if(!li.contains(i)){
				result.add(b.get(i));
			}
		}
		
		

		return result;
	}

}
