

package fr.univ_lille1.m2iagl.spoon.templatechallenge;


public class TemplateChallenge implements fr.univ_lille1.m2iagl.spoon.templatechallenge.ITemplateChallenge<java.lang.Integer> {
    @java.lang.Override
    public void challenge(java.lang.Integer i) {
        java.lang.Integer j = 5;
        i = debug((i + 2));
        i = debug((i - j));
        i = debug((i * 2));
        java.lang.Integer result = j / i;
    }

    public java.lang.Integer debug(java.lang.Integer input) {
        int line = java.lang.Thread.currentThread().getStackTrace()[2].getLineNumber();
        fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton.getInstance().getCauseEffectChain().setChainElementDescription(line, input.toString());
        java.lang.System.out.println("UN PRINT");
        return input;
    }
}

