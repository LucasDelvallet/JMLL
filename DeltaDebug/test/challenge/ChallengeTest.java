package challenge;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univ_lille1.m2iagl.challenge.Challenge;
import fr.univ_lille1.m2iagl.dd.CauseEffectChainSingleton;
import fr.univ_lille1.m2iagl.dd.DeltaDebug;

public class ChallengeTest {
	protected Challenge challenge;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CauseEffectChainSingleton.reset();
		FileWriter fwOb = new FileWriter(DeltaDebug.CHALLENGE_FILE, false);
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();
		pwOb.close();
		fwOb.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		CauseEffectChainSingleton.reset();
		FileWriter fwOb = new FileWriter(DeltaDebug.CHALLENGE_FILE, false);
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();
		pwOb.close();
		fwOb.close();
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

}
