package fr.univ_lille1.m2iagl.dd;

import java.io.FileWriter;
import java.io.PrintWriter;

import fr.univ_lille1.m2iagl.challenge.*;

public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		clearFile();
		
		DDebuggerImpl ddebugger = new DDebuggerImpl();

		Challenge challenge = new JMLLChallenge1();
		
		CauseEffectChainImpl cEC = (CauseEffectChainImpl) ddebugger.debug(challenge);
		cEC.print();
		System.out.println("Then, it crashed... But, you can find the bug, so it's not so bad.");
		
		clearFile();
	}

	public static void clearFile() {
		try {
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
