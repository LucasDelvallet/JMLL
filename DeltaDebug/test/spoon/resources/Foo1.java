package spoon.resources;

public class Foo1 implements IFoo { 
	  public int m() {
		  int i = 3;
		  i = 3 + i;
		  return i;
	  }
	  
	  public void debug() {
		  //Nothing
	  }
	  
	  public int n() {
		  return 0;
	  }
}