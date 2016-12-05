package fr.univ_lille1.m2iagl.dd;

public class CauseEffectChainSingleton {
	private static CauseEffectChainSingleton instance;
	
	private CauseEffectChainImpl causeEffectChain;
	
	private CauseEffectChainSingleton() {}
	
	public static CauseEffectChainSingleton getInstance() {
		if(instance == null) {
			instance = new CauseEffectChainSingleton();
		}
		
		return instance;
	}
	
	public CauseEffectChainImpl getCauseEffectChain() {
		if(causeEffectChain == null) {
			causeEffectChain = new CauseEffectChainImpl();
		}
		
		return causeEffectChain;
	}

}
