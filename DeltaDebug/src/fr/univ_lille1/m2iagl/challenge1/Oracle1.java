package fr.univ_lille1.m2iagl.challenge1;

public class Oracle1 {
	@SuppressWarnings("unused")
	public static boolean division(int i){
		int j = 5;
		i = i + 2;
		i = i - j;
		i = i * 2;
		
		try{
			int result = j / i;
			return true;
		}catch(ArithmeticException e){
			return false;
		}
	}
}
