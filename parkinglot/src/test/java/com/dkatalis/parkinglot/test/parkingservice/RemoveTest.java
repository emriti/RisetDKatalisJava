package com.dkatalis.parkinglot.test.parkingservice;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.entity.ParkingServiceDTO;
import com.dkatalis.parkinglot.service.ParkingService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RemoveTest extends TestCase {
	public RemoveTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new RemoveTest("successfullyUnParked"));
		suite.addTest(new RemoveTest("dataNotFound"));
		return suite;
	}

	public void successfullyUnParked() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(1);
		svc.park("1");
		svc.remove("1", 1);
		List<ParkingEntity> list = svc.getStatus();
		assertTrue(list.size() == 0);
	}

	public void dataNotFound() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(1);
		ParkingServiceDTO dto = svc.remove("1", 1);
		assertTrue(dto.getMessages().get("errorMsg").toString().equals("Data not found!"));
	}
}
