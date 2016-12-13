package dd;

import fr.univ_lille1.m2iagl.dd.ChainElementImpl;
import static org.junit.Assert.*;

import org.junit.Test;

public class ChainElementImplTest {
	@Test
	public void getLine_Test(){
		String line = "1";
		ChainElementImpl ceI = new ChainElementImpl(line, "variable", "description");
		
		assertEquals(line, ceI.getLine());
	}
	@Test
	public void getVariable_Test(){
		String variable = "variable";
		ChainElementImpl ceI = new ChainElementImpl("1", variable, "description");
		
		assertEquals(variable, ceI.getVariable());
	}
	
	@Test
	public void getDescription_Test(){
		String description = "description";
		ChainElementImpl ceI = new ChainElementImpl("1", "variable", description);
		
		assertEquals(description, ceI.getDescription());
	}

	@Test
	public void get_set_Iteration_Test(){
		ChainElementImpl ceI = new ChainElementImpl("1", "variable", "description");
		
		assertEquals(-1, ceI.getIteration());
		
		int iteration = 5;
		ceI.setIteration(5);
		
		assertEquals(iteration, ceI.getIteration());
	}

	@Test
	public void get_set_Value_Test(){
		ChainElementImpl ceI = new ChainElementImpl("1", "variable", "description");
		
		assertEquals(null, ceI.getValue());
		
		String value = "value";
		ceI.setValue(value);
		
		assertEquals(value, ceI.getValue());
	}

	@Test
	public void setLine_Test(){
		String line = "1";
		ChainElementImpl ceI = new ChainElementImpl(line, "variable", "description");
		
		assertEquals(line, ceI.getLine());
		
		String line2 = "5";
		ceI.setLine(line2);
		assertEquals(line2, ceI.getLine());
	}

	@Test
	public void setVariable_Test(){
		String variable = "variable";
		ChainElementImpl ceI = new ChainElementImpl("1", variable, "description");
		
		assertEquals(variable, ceI.getVariable());
		
		String variable2 = "variable2";
		ceI.setVariable(variable2);
		assertEquals(variable2, ceI.getVariable());
	}

	@Test
	public void setDescription_Test(){
		String description = "description";
		ChainElementImpl ceI = new ChainElementImpl("1", "variable", description);
		
		assertEquals(description, ceI.getDescription());
		
		String description2 = "description2";
		ceI.setDescription(description2);
		assertEquals(description2, ceI.getDescription());
	}
	
	
	
}
