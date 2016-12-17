package fr.univ_lille1.m2iagl.spoon.templatechallenge;
public class TemplateChallenge implements fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge<Integer>{
@Override
public void challenge(Integer input){
Integer j = 5;
input = input + 2;
input = input - j;
input = input * 2;
Integer result = j / input;
}

public <T> T debug(T input){
int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
java.util.List<fr.univ_lille1.m2iagl.dd.ChainElement> cs = fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain();
fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementValue(line, input);
return input;
}
}
