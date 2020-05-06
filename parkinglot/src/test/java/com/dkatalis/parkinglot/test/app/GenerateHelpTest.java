package com.dkatalis.parkinglot.test.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.dkatalis.parkinglot.app.App;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GenerateHelpTest extends TestCase {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	public GenerateHelpTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new GenerateHelpTest("testGenerateCommandCreateParkingLot"));
		suite.addTest(new GenerateHelpTest("testGenerateCommandPark"));
		suite.addTest(new GenerateHelpTest("testGenerateCommandLeave"));
		suite.addTest(new GenerateHelpTest("testGenerateCommandStatus"));
		suite.addTest(new GenerateHelpTest("testGenerateCommandAll"));
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
		String command = "create_parking_lot --help";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals("create_parking_lot [capacity]" + System.getProperty("line.separator")));
	}

	public void testGenerateCommandPark() {
		String command = "park --help";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals("park [registration no]" + System.getProperty("line.separator")));
	}

	public void testGenerateCommandLeave() {
		String command = "leave --help";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals("leave [slot no] [hours]" + System.getProperty("line.separator")));
	}

	public void testGenerateCommandStatus() {
		String command = "status --help";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals("status" + System.getProperty("line.separator")));
	}

	public void testGenerateCommandAll() {
		String command = "help";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals("commands available: create_parking_lot, park, leave, status" + System.getProperty("line.separator")));
	}
	
}
