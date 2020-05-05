package com.dkatalis.parkinglot.test.parkingservice;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.repository.ParkingRepository;
import com.dkatalis.parkinglot.service.ParkingService;
import com.dkatalis.parkinglot.test.parkingrepository.AddTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CalculateChargeTest extends TestCase {
	
	public CalculateChargeTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new CalculateChargeTest("calculateZeroHour"));
		suite.addTest(new CalculateChargeTest("calculateOneHour"));
		suite.addTest(new CalculateChargeTest("calculateTwoHours"));
		suite.addTest(new CalculateChargeTest("calculateMoreThanTwoHours"));
		return suite;
	}

	public void calculateZeroHour() {
		ParkingService svc = new ParkingService();
		assertTrue(svc.calculateCharge(0) == 0);
	}
	
	public void calculateOneHour() {
		ParkingService svc = new ParkingService();
		assertTrue(svc.calculateCharge(1) == 10);
	}
	
	public void calculateTwoHours() {
		ParkingService svc = new ParkingService();
		assertTrue(svc.calculateCharge(2) == 10);
	}
	
	public void calculateMoreThanTwoHours() {
		ParkingService svc = new ParkingService();
		assertTrue(svc.calculateCharge(3) == 20);
	}
}
