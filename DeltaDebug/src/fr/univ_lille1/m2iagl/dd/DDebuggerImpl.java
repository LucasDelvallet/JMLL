package fr.univ_lille1.m2iagl.dd;

public class DDebuggerImpl<T> implements DDebugger<T> {

	@Override
	public CauseEffectChain debug(Challenge<T> c) {
		CauseEffectChain cEC = null;
		T inputFail = null;
		
		//Ici je trouve un input qui fait fail.
		for(T input : c.getInputs()){
			//En testant l'oracle, je devrait récuperer la CauseEffectChain 
			//pour pouvoir manipuler les variables. Spoon est notre sauveur.
			if(c.oracle(input)){ 
				inputFail = input;
				cEC = null; //Pas null normalement
			}
		}
		if(inputFail == null){
			return null; // Y'a pas d'input qui fait Fail.
		}
		
		// Ici on retourne CauseEffectChain qui trouve le bug, on doit juste l'afficher en suite.
		//Elle a été trouvée avec la méthode ddmin
		return DeltaDebug.ddmin(cEC, inputFail ,c); 
	}

}
