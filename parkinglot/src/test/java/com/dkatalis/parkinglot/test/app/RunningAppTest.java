package com.dkatalis.parkinglot.test.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.dkatalis.parkinglot.app.App;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RunningAppTest extends TestCase {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	public RunningAppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new RunningAppTest("runningAppInputEmptyString"));
		suite.addTest(new RunningAppTest("createParkingLotErrorInputNonNumber"));
		suite.addTest(new RunningAppTest("successfullyCreatedParkingLot"));
		suite.addTest(new RunningAppTest("generateStatusDataNA"));
		suite.addTest(new RunningAppTest("successfullyParked"));
		suite.addTest(new RunningAppTest("failedToParkDataExists"));
		suite.addTest(new RunningAppTest("failedToParkParkingFull"));
		suite.addTest(new RunningAppTest("generateStatusDataExists"));
		suite.addTest(new RunningAppTest("leaveErrorInputNonNumber"));
		suite.addTest(new RunningAppTest("leaveErrorDataNotFound"));
		suite.addTest(new RunningAppTest("successfullyLeaved"));
		
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

	public void runningAppInputEmptyString() {
		App.runningApp(" ");
		String output = outContent.toString();
		assertTrue(output.equals("Command not found!\r\nSee help\n\r\n"));
	}
	
	public void createParkingLotErrorInputNonNumber() {
		String command = "create_parking_lot";
		App.runningApp(command + " abc");
		String output = outContent.toString();
		assertTrue(output.equals(String.format("Parameters error!\r\nSee %s --help\n\r\n", command)));
	}

	public void successfullyCreatedParkingLot() {
		App.runningApp("create_parking_lot 1");
		String output = outContent.toString();
		assertTrue(output.equals("Created parking lot with 1 slots\r\n"));
	}
	
	public void generateStatusDataNA() {
		App.runningApp("status");
		String output = outContent.toString();
		assertTrue(output.equals("Slot No.\tRegistration No\r\n"));
	}
	
	public void successfullyParked() {
		String command = "park 1";
		App.runningApp(command);
		String output = outContent.toString();
		assertTrue(output.equals("Allocated slot number: 1\r\n"));
	}
	
	public void failedToParkDataExists() {
		App.runningApp("park 1");
		String output = outContent.toString();
		assertTrue(output.equals("Data has already exists!\r\n"));
	}
	
	public void failedToParkParkingFull() {
		App.runningApp("park 2");
		String output = outContent.toString();
		assertTrue(output.equals("Sorry, parking lot is full\r\n"));
	}
	
	public void generateStatusDataExists() {
		App.runningApp("status");
		String output = outContent.toString();
		assertTrue(output.equals("Slot No.\tRegistration No\r\n1\t\t1\r\n"));
	}

	public void leaveErrorInputNonNumber() {
		String command = "leave";
		App.runningApp(command + " 1 abc");
		String output = outContent.toString();
		assertTrue(output.equals(String.format("Parameters error!\r\nSee %s --help\n\r\n", command)));
	}

	public void leaveErrorDataNotFound() {
		App.runningApp("leave 2 1");
		String output = outContent.toString();
		assertTrue(output.equals("Registration number 2 not found\r\n"));
	}
	
	public void successfullyLeaved() {
		App.runningApp("leave 1 1");
		String output = outContent.toString();
		assertTrue(output.equals("Registration number 1 with Slot Number 1 is free with Charge 10\r\n"));
	}
	
}