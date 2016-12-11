package fr.univ_lille1.m2iagl.dd;

import java.util.ArrayList;
import java.util.List;

public class ChainElementImpl implements ChainElement {

	private String line;
	private String variable;
	private String description;
	private int iteration;
	private Object value;

	public ChainElementImpl(String line, String variable, String description) {
		this.line = line;
		this.variable = variable;
		this.description = description;
		iteration = -1;
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
	
	public int getIteration() {
		return iteration;
	}
	
	public Object getValue() {
		return value;
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
	
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
