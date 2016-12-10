package fr.univ_lille1.m2iagl.spoon.templatechallenge;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
public class TemplateChallenge implements fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge<Integer>{
@Override
public void challenge(Integer input){
int j = 5;
input = input + 2;
input = input - j;
int k = input * 2;
int result = j / k;
}

public Integer debug(Integer input){
int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
java.util.List<fr.univ_lille1.m2iagl.dd.ChainElement> cs = fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().getChain();
for(int i = 0; i < cs.size(); i++){
if(Integer.parseInt(cs.get(i).getLine()) == line){
fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementDescription(i, input);
}
}
return input;
}
}
