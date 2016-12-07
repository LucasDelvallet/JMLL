package fr.univ_lille1.m2iagl.dd;

public class ChainElementImpl implements ChainElement{

	private String line;
	private String variable;
	private String description;
	
	public ChainElementImpl(String line, String variable, String description){
		this.line = line;
		this.variable = variable;
		this.description = description;
	}

	@Override
	public String getLine() {
		return line;
	}

	@Override
	public String getVariable() {
		return variable;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public void setLine(String line) {
		this.line = line;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
