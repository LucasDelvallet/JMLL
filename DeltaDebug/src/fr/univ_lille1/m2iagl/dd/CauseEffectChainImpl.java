package fr.univ_lille1.m2iagl.dd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CauseEffectChainImpl implements CauseEffectChain {

	private List<ChainElement> chainElements;

	public CauseEffectChainImpl() {
		this.chainElements = new ArrayList<>();
	}

	public CauseEffectChainImpl(List<ChainElement> elements) {
		this.chainElements = elements;
	}

	public void addElement(ChainElement element) {
		chainElements.add(element);
	}

	public void setChainElementDescription(int index, String description) {
		((ChainElementImpl) (chainElements.get(index))).setDescription(description);
	}

	public void setChainElementValue(int line, Object value, String type) {
		for(int i = 0; i < chainElements.size(); i++){
			ChainElementImpl ceI = (ChainElementImpl)chainElements.get(i);
			if(Integer.parseInt(ceI.getLine()) == line && ceI.getDescription().equals(type)){
				if(ceI.getIteration() == -1){
					ceI.setValue(value);
					ceI.setIteration(0);
					chainElements.set(i, ceI);
				}else{
					ChainElementImpl ceINewIteration = new ChainElementImpl(ceI.getLine(), ceI.getVariable(), ceI.getDescription());
					ceINewIteration.setValue(value);
					ceINewIteration.setIteration(getLineIterationCount(line)+1);
					chainElements.add(ceINewIteration);
					break;
				}
			}
		}
	}
	
	private int getLineIterationCount(int line){
		int count = 0;
		for(int i = 0; i < chainElements.size(); i++){
			ChainElementImpl ceI = (ChainElementImpl)chainElements.get(i);
			if(Integer.parseInt(ceI.getLine()) == line){
				count++;
			}
		}
		return count;
	}

	@Override
	public List<ChainElement> getChain() {
		return chainElements;
	}

	public void setChain(List<ChainElement> chainElements) {
		this.chainElements.clear();
		this.chainElements.addAll(chainElements);
	}

	public void print() {
		for (int i = 0; i < chainElements.size(); i++) {
			ChainElementImpl ce = (ChainElementImpl) chainElements.get(i);

			String iteration = "";
			if (ce.getIteration() > 0) {
				iteration = "  Iteration " + ce.getIteration();
			}

			System.out.println("Line : " + ce.getLine() + iteration + "     |     Variable name : " + ce.getVariable()
					+ "    |    " + ce.getDescription() + "  |  Value : " +ce.getValue());
		}
	}

	public void clearChainElements() {
		chainElements.clear();
	}

	public void sort(){
		Collections.sort(chainElements, new Comparator<ChainElement>() {
			public int compare(ChainElement o1, ChainElement o2) {
				return Integer.parseInt(o1.getLine()) - Integer.parseInt(o2.getLine());
			};
		});
	}
	
	public void removeUniteratedLine(){
		for (int i = 0; i < chainElements.size(); i++) {
			if(((ChainElementImpl)chainElements.get(i)).getIteration() == -1){
				chainElements.remove(i);
				i--;
			}
		}
	}
}
