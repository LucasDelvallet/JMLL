package fr.univ_lille1.m2iagl.spoon.templatechallenge;
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
return input;
}
}
