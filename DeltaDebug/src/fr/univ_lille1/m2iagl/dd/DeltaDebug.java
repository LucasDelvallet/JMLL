package fr.univ_lille1.m2iagl.dd;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mdkt.compiler.InMemoryJavaCompiler;

import fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor;
import fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor;
import fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge;
import spoon.Launcher;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class DeltaDebug {

	public static <T> boolean generateCauseEffectChain(T input, Challenge<T> challenge) {


			CauseEffectChainSingleton.getInstance().resetCauseEffectChain();

			Launcher l = new Launcher();
			l.setArgs(
					new String[] { "-i", "src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java" });
			l.addInputResource("src/test/resources/");
			l.buildModel();

			CtClass foo = (CtClass) l.getFactory().Package().getRootPackage()
					.getElements(new NameFilter("TemplateChallenge")).get(0);

			
			CtMethod cChallenge = (CtMethod) foo.getElements(new TypeFilter(CtMethod.class)).get(0);
			
			for (Object e : cChallenge.getElements(new TypeFilter(CtAssignment.class))) {
				e = AssignementProcessor.process(e);
			}

			for (Object e : cChallenge.getElements(new TypeFilter(CtVariable.class))) {
				e = VariableProcessor.process(e);
			}

			Class tCClass = null;
			ITemplateChallenge tC = null;
			try {
				tCClass = InMemoryJavaCompiler.compile(foo.getQualifiedName(),
						"package " + foo.getPackage().getQualifiedName() + ";" + foo.toString());
			
				tC = (ITemplateChallenge) tCClass.newInstance();			
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
		boolean hasSucceded = true;
		try{
			tC.challenge(input);
		}catch (Exception e1) {
			hasSucceded = false;
		}
		
		return hasSucceded;
	}

	public static <T> CauseEffectChain ddmin(T inputFail, T inputSuccess, Challenge<T> c) {

		generateCauseEffectChain(inputFail, c);
		CauseEffectChain cECFail = CauseEffectChainSingleton.getInstance().getCauseEffectChain();
		generateCauseEffectChain(inputSuccess, c);
		CauseEffectChain cECSuccess = CauseEffectChainSingleton.getInstance().getCauseEffectChain();
		List<ChainElement> cEsReturn = null;

		int n = 2;

		cEsReturn = difference(cECFail.getChain(), cECSuccess.getChain());
		while (cEsReturn.size() >= 2) {
			int start = 0;
			int subset_lenght = cEsReturn.size() / n;
			boolean some_complement_is_failing = false;

			while (start < cEsReturn.size()) {
				List<ChainElement> complement = new ArrayList<ChainElement>();
				complement.addAll(cEsReturn.subList(0, start));
				complement.addAll(cEsReturn.subList(start + subset_lenght, cEsReturn.size()));

				CauseEffectChainImpl cDiff = CauseEffectChainSingleton.getInstance().getDiffCauseEffectChain();
				cDiff.setChain(complement);

				if (generateCauseEffectChain(inputFail, c)) {
					cEsReturn = complement;
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
		return new CauseEffectChainImpl(cEsReturn);

	}

	private static List<ChainElement> difference(List<ChainElement> a, List<ChainElement> b) {
		List<ChainElement> result = new LinkedList<ChainElement>();

		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				ChainElement aa = a.get(i);
				ChainElement bb = b.get(j);

				if (aa.getLine().equals(bb.getLine()) && aa.getVariable().equals(bb.getVariable())
						&& aa.getDescription().equals(bb.getDescription())) {
					result.add(aa);
					break;
				}
			}
		}

		return result;
	}

}
