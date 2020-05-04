package com.dkatalis.parkinglot;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParkingRepositoryTest extends TestCase  {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ParkingRepositoryTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ParkingRepositoryTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
