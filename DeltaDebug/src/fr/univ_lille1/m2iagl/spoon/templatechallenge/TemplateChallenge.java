package fr.univ_lille1.m2iagl.spoon.templatechallenge;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
public class TemplateChallenge implements ITemplateChallenge<Integer>{
@Override
public void challenge(Integer i){
Integer j = 5;
i = i + 2;
i = i - j;
i = i * 2;
Integer result = j / i;
}

public Integer debug(Integer input){
int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementDescription(line, input.toString());
System.out.println("UN PRINT");
return input;
}
}
