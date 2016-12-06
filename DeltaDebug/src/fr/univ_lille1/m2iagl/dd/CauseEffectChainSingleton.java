package fr.univ_lille1.m2iagl.dd;

public class CauseEffectChainSingleton {
	private static CauseEffectChainSingleton instance;
	
	private CauseEffectChainImpl causeEffectChain;
	private CauseEffectChainImpl successCauseEffectChain;
	private CauseEffectChainImpl failCauseEffectChain;
	private CauseEffectChainImpl diffCauseEffectChain;
	
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
	
	public void resetCauseEffectChain() {
		if(causeEffectChain != null) {
			causeEffectChain.clearChainElements();
		}
	}
	
	public CauseEffectChainImpl getSuccessCauseEffectChain() {
		if(successCauseEffectChain == null) {
			successCauseEffectChain = new CauseEffectChainImpl();
		}
		
		return successCauseEffectChain;
	}
	
	public void resetSuccessCauseEffectChain() {
		if(successCauseEffectChain != null) {
			successCauseEffectChain.clearChainElements();
		}
	}
	
	public CauseEffectChainImpl getFailCauseEffectChain() {
		if(failCauseEffectChain == null) {
			failCauseEffectChain = new CauseEffectChainImpl();
		}
		
		return failCauseEffectChain;
	}
	
	public void resetFailCauseEffectChain() {
		if(failCauseEffectChain != null) {
			failCauseEffectChain.clearChainElements();
		}
	}
	
	public CauseEffectChainImpl getDiffCauseEffectChain() {
		if(diffCauseEffectChain == null) {
			diffCauseEffectChain = new CauseEffectChainImpl();
		}
		
		return diffCauseEffectChain;
	}
	
	public void resetDiffCauseEffectChain() {
		if(diffCauseEffectChain != null) {
			diffCauseEffectChain.clearChainElements();
		}
	}

}
