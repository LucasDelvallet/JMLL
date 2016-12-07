package fr.univ_lille1.m2iagl.dd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CauseEffectChainImpl implements CauseEffectChain {

	private List<ChainElement> chainElements;
	
	public CauseEffectChainImpl(){
		this.chainElements = new ArrayList<>();
	}
	
	public CauseEffectChainImpl(List<ChainElement> elements){
		this.chainElements = elements;
	}
	
	public void addElement(ChainElement element) {
		chainElements.add(element);
	}
	
	public void setChainElementDescription(int index, String description){
		((ChainElementImpl)(chainElements.get(index))).setDescription(description);
	}
	
	@Override
	public List<ChainElement> getChain() {
		return chainElements;
	}
	
	public void print(){
		Collections.sort(chainElements, new Comparator<ChainElement>() {
			public int compare(ChainElement o1, ChainElement o2) {
				return Integer.parseInt(o1.getLine()) - Integer.parseInt(o2.getLine()) ;
			};
		});
		
		for(ChainElement ce : chainElements){
			System.out.println("Line : " + ce.getLine() + " | Variable name : " + ce.getVariable() + " | " + ce.getDescription());
		}
	}
	
	public void clearChainElements(){
		chainElements.clear();
	}

}
