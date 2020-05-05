package com.dkatalis.parkinglot;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParkingServiceTest extends TestCase {

	public ParkingServiceTest(String testName) {
		super(testName);
	}


	public static Test suite() {
		return new TestSuite(ParkingServiceTest.class);
	}

	public class createParkingLotFact extends TestCase {
		public createParkingLotFact(String testName) {
			super(testName);
		}

		public Test suite() {
			return new TestSuite(createParkingLotFact.class);
		}
		
		public void testApp() {
			assertTrue(true);
		}
	}


}
