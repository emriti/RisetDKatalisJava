package com.dkatalis.parkinglot.test.parkingservice;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.service.ParkingService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GetStatusTest extends TestCase
{
	public GetStatusTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new GetStatusTest("checkDataSize"));
		return suite;
	}
	
	public void checkDataSize() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(1);
		svc.park("1");
		List<ParkingEntity> list = svc.getStatus();
		assertTrue(list.size() == 1);
	}
}
