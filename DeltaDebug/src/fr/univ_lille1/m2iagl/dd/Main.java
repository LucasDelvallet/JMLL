package fr.univ_lille1.m2iagl.dd;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.m2iagl.challenge.*;

public class Main {

	public static Integer debug(Integer input){
		return input;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		try {
			DDebuggerImpl ddebugger = new DDebuggerImpl();
			
			Challenge challenge = new Challenge1();			
			
			CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
			cEC.print();
			System.out.println("Then, it crashed... But, you can find the bug, so it's not so bad.");

			FileWriter fwOb = new FileWriter(DeltaDebug.CHALLENGE_FILE, false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
