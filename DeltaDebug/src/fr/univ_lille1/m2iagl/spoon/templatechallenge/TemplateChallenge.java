package fr.univ_lille1.m2iagl.spoon.templatechallenge;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
public class TemplateChallenge implements fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge<String>{
@Override
public void challenge(String input){
int i = 5;
input = input.toLowerCase();
String retour = "";
for (int j = 0; j < i; j++) {
retour = retour + input.substring(j, j + 1);
}
}

public <T> T debug(T input){
int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
java.util.List<fr.univ_lille1.m2iagl.dd.ChainElement> cs = fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain();
fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementValue(line, input);
return input;
}
}
