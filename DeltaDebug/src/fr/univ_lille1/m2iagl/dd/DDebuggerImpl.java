package fr.univ_lille1.m2iagl.dd;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.EvalError;
import bsh.Interpreter;

public class DDebuggerImpl<T> implements DDebugger<T> {

	@Override
	public CauseEffectChain debug(Challenge<T> c) {
		T inputFail = null;
		T inputSucess = null;

		for (T input : c.getInputs()) {
			try{
				c.challenge(input);
				inputSucess = input;
			}catch(Exception e){
				inputFail = input;
			}
		}
		if (inputFail == null || inputSucess == null) {
			return null; // Y'a pas d'input qui fait Fail ou Success
		}

		writeChallenge(c);
		
		return DeltaDebug.ddmin(inputFail, inputSucess, c);
	}

	public void writeChallenge(Challenge<T> c){
		try {
			List<String> lines = new ArrayList<String>();
			lines.add("package fr.univ_lille1.m2iagl.spoon.templatechallenge;");
			lines.add("import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;");
			lines.add("public class TemplateChallenge implements fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge<"+c.getInputFormat().getSimpleName()+">{");
			lines.add("@Override");
			lines.add(c.getJavaProgram());
			lines.add("");
			lines.add("public "+c.getInputFormat().getSimpleName()+" debug("+c.getInputFormat().getSimpleName()+" input){");
			lines.add("int line = Thread.currentThread().getStackTrace()[2].getLineNumber();");
		    lines.add("java.util.List<fr.univ_lille1.m2iagl.dd.ChainElement> cs = fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain();");
			lines.add("for(int i = 0; i < cs.size(); i++){");
			lines.add("if(Integer.parseInt(cs.get(i).getLine()) == line){");
			lines.add("fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementDescription(i, input.toString());");
			lines.add("}");
			lines.add("}");
		    lines.add("return input;");
			lines.add("}");
			lines.add("}");

			Files.write(Paths.get("src/fr/univ_lille1/m2iagl/spoon/templatechallenge/TemplateChallenge.java"), lines, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
