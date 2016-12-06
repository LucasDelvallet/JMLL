package fr.univ_lille1.m2iagl.dd;

import java.io.File;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge;
import spoon.Launcher;

public class DeltaDebug {

	public static <T> CauseEffectChain getCauseEffectChain(T input, Challenge<T> c) {

		CauseEffectChainSingleton.getInstance().resetCauseEffectChain();

		final String[] argsS = { "--precompile", "-i",
				"src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java", "-o", "target/spooned/",
				"-p", "fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor" + File.pathSeparator
						+ "fr.univ_lille1.m2iagl.spoon.processor.AssignementProcessor" };

		final Launcher launcher = new Launcher();
		launcher.setArgs(argsS);
		launcher.run();

		org.eclipse.jdt.internal.compiler.batch.Main.compile(
				org.eclipse.jdt.internal.compiler.batch.Main.tokenize("-1.6 target/spooned/"),
				new PrintWriter(System.out), new PrintWriter(System.err), null);

		return CauseEffectChainSingleton.getInstance().getCauseEffectChain();
	}

	public static <T> CauseEffectChain ddmin(T inputFail, T inputSuccess, Challenge<T> c) {
		// List<ChainElement> chainElements = causeEffectChain.getChain();

		try {

			CauseEffectChain cECFail = getCauseEffectChain(inputFail, c);
			CauseEffectChain cECSuccess = getCauseEffectChain(inputSuccess, c);
			CauseEffectChainSingleton.getInstance()
			List<ChainElement> cEsReturn = null;

			int n = 2;

			// TODO: difference(x,y) doit être mieux faite.
			cEsReturn = difference(cECFail.getChain(), cECSuccess.getChain());
			while (cEsReturn.size() >= 2) {
				int start = 0;
				int subset_lenght = cEsReturn.size() / n;
				boolean some_complement_is_failing = false;

				while (start < cEsReturn.size()) {
					List<ChainElement> complement = new ArrayList<ChainElement>();
					complement.addAll(cEsReturn.subList(0, start));
					complement.addAll(cEsReturn.subList(start + subset_lenght, cEsReturn.size()));

					
					
					// TODO: Modifier le programme selon le complement

					ITemplateChallenge tC = (ITemplateChallenge) loadModifiedClass().newInstance();
					tC.challenge(inputFail);
					
					
					if () {
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the difference of set a and set b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static <T> List<T> difference(List<T> a, List<T> b) {
		List<T> result = new LinkedList<T>();
		result.addAll(a);
		result.removeAll(b);
		return result;
	}

	public static Class loadModifiedClass() throws Exception {
		URLClassLoader loader = new URLClassLoader(
				new URL[] { new URL("file://" + "target/spooned/fr/univ_lille1/m2iagl/spoon/templatechallenge/") });
		return loader.loadClass("TemplateChallenge");
	}

}
