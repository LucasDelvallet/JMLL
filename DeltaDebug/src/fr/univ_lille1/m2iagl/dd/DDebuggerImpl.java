package fr.univ_lille1.m2iagl.dd;

import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

public class DDebuggerImpl<T> implements DDebugger<T> {

	@Override
	public CauseEffectChain debug(Challenge<T> c) {
		CauseEffectChain cEC = getCauseEffectChain(c);
		T inputFail = null;

		// Ici je trouve un input qui fait fail.
		for (T input : c.getInputs()) {
			// En testant l'oracle, je devrait récuperer la CauseEffectChain
			// pour pouvoir manipuler les variables. Spoon est notre sauveur.
			if (c.oracle(input)) {
				inputFail = input;
				cEC = null; // Pas null normalement
			}
		}
		if (inputFail == null) {
			return null; // Y'a pas d'input qui fait Fail.
		}

		// Ici on retourne CauseEffectChain qui trouve le bug, on doit juste
		// l'afficher en suite.
		// Elle a été trouvée avec la méthode ddmin
		return DeltaDebug.ddmin(cEC, inputFail, c);
	}

	public CauseEffectChain getCauseEffectChain(Challenge<T> c) {

		// Tout ça ne doit pas être dans l'oracle
		// Ici c'est juste pour vérifier si ça fail ou non.
		// Nous, on doit utiliser le programme présent dans
		// getJavaProgram pour permettre de le manipuler
		// a notre guise.
		try {
			Interpreter interpreter = new Interpreter();

			// Evaluate statements and expressions
			String t = c.getJavaProgram();
			interpreter.eval(t);

			interpreter.eval("boolean t = division(5)");
			System.out.println(interpreter.get("t")); // Print true.

			interpreter.eval("boolean t = division(3)"); // Print false.
			System.out.println(interpreter.get("t"));

		} catch (EvalError e) {
		}
		return null;
	}

}
