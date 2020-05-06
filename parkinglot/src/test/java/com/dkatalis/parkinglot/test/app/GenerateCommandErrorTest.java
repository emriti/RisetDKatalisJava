package com.dkatalis.parkinglot.test.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.dkatalis.parkinglot.app.App;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GenerateCommandErrorTest extends TestCase {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	public GenerateCommandErrorTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new GenerateCommandErrorTest("testGenerateCommand"));
		return suite;
	}
	
	@Override
	protected void tearDown() throws Exception {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Override
	protected void setUp() throws Exception {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	public void testGenerateCommand() {
		App.runningApp("abcd");
		String output = outContent.toString();
		assertTrue(output.equals("Command not found!\r\nSee help\n\r\n"));
	}
	
}
