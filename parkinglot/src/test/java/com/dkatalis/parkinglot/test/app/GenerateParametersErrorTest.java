package com.dkatalis.parkinglot.test.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.dkatalis.parkinglot.app.App;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GenerateParametersErrorTest extends TestCase {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	public GenerateParametersErrorTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new GenerateParametersErrorTest("testGenerateCommandCreateParkingLot"));
		suite.addTest(new GenerateParametersErrorTest("testGenerateCommandPark"));
		suite.addTest(new GenerateParametersErrorTest("testGenerateCommandLeave"));
		suite.addTest(new GenerateParametersErrorTest("testGenerateCommandStatus"));
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

	public void testGenerateCommandCreateParkingLot() {
		String command = "create_parking_lot";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals(String.format("Parameters error!\r\nSee %s --help\n\r\n", command)));
	}

	public void testGenerateCommandPark() {
		String command = "park";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals(String.format("Parameters error!\r\nSee %s --help\n\r\n", command)));
	}
	
	public void testGenerateCommandLeave() {
		String command = "leave";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals(String.format("Parameters error!\r\nSee %s --help\n\r\n", command)));
	}
	
	public void testGenerateCommandStatus() {
		String command = "status";
		App.runningApp(command + " 1");
		String output = outContent.toString();
		assertTrue(output.equals(String.format("Parameters error!\r\nSee %s --help\n\r\n", command)));
	}
	
}