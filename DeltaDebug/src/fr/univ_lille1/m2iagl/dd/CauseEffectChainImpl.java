package fr.univ_lille1.m2iagl.dd;

import java.util.List;

public class CauseEffectChainImpl implements CauseEffectChain {

	private List<ChainElement> chainElements;
	
	public CauseEffectChainImpl(List<ChainElement> chainElements){
		this.chainElements = chainElements;
	}
	
	
	@Override
	public List<ChainElement> getChain() {
		return chainElements;
	}

}
