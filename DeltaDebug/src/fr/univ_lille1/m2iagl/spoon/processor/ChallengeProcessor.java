package fr.univ_lille1.m2iagl.spoon.processor;


import fr.univ_lille1.m2iagl.challenge.Challenge;
import spoon.Launcher;
import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.ArrayList;
import java.util.List;

public class ChallengeProcessor {
	
	public static <T> List<String> getChallengeAsString(Challenge<T> c) {
		List<String> lines = new ArrayList<String>();
		lines.add("package fr.univ_lille1.m2iagl.spoon.templatechallenge;");
		lines.add("import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;");
		lines.add("public class TemplateChallenge implements fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge<"+c.getInputFormat().getSimpleName()+">{");
		lines.add("@Override");
		
		lines.add("public void challenge("+c.getInputFormat().getSimpleName()+" input)");
		lines.add(process(c.getClass().getSimpleName()));

		lines.add("");
		lines.add("public <T> T debug(T input, String type){");
		lines.add("int line = Thread.currentThread().getStackTrace()[2].getLineNumber();");
	    lines.add("java.util.List<fr.univ_lille1.m2iagl.dd.ChainElement> cs = fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain();");
		lines.add("fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementValue(line, input, type);");
	    lines.add("return input;");
		lines.add("}");
		lines.add("}");
		
		return lines;
	}
	
	public static String process(String name) {
		 Launcher l = new Launcher();
		  l.addInputResource("src/fr/univ_lille1/m2iagl/challenge/"+name+".java");
		  l.buildModel();
		  
		  CtClass foo = (CtClass) l.getFactory().Package().getRootPackage().getElements(new NameFilter(name)).get(0);
		  
		  
		  for(Object o : foo.getElements(new TypeFilter(CtMethod.class))){
			  CtMethod c = (CtMethod) o ;
			  if(c.getSimpleName().equals("challenge")){
				  
				 CtBlock cb = c.getBody();
				 return cb.toString();
			  }
			  
		  }
		 
		  return null;

	}
	
}
