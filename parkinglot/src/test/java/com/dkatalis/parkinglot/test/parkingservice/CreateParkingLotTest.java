package com.dkatalis.parkinglot.test.parkingservice;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.service.ParkingService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CreateParkingLotTest extends TestCase
{
	public CreateParkingLotTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new CreateParkingLotTest("checkDataSize"));
		return suite;
	}
	
	public void checkDataSize() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(2);
		List<ParkingEntity> list = svc.getStatus();
		assertTrue(list.size() == 2);
	}
}