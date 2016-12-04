package divideByZero;

import static org.junit.Assert.fail;

import org.junit.Test;

import data.challenges.divideByZero.DivideByZero;

public class DivideByZeroProgramTest {

	@Test
	public void testSuccessInputShouldNotFail() {
		try {
			DivideByZero.main(new String[] {"2", "1"});
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testWrongInputShouldFail() {
		boolean crashed = false;
		try {
			DivideByZero.main(new String[] {"2", "0"});
		} catch (Exception e) {
			crashed = true;
		}
		
		if(!crashed) {
			fail();
		}
	}

}
