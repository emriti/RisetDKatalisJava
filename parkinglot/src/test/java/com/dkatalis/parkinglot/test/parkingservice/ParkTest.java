package com.dkatalis.parkinglot.test.parkingservice;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.entity.ParkingServiceDTO;
import com.dkatalis.parkinglot.service.ParkingService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParkTest extends TestCase {
	public ParkTest(String testName) {
			super(testName);
		}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ParkTest("successfullyParked"));
		suite.addTest(new ParkTest("parkingLotFull"));
		suite.addTest(new ParkTest("dataExist"));
		return suite;
	}

	public void successfullyParked() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(1);
		svc.park("1");
		List<ParkingEntity> list = svc.getStatus();
		assertTrue(list.get(0).getSlotNo() == 1 && list.get(0).getRegistrationNo() == "1");
	}
	
	public void parkingLotFull() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(1);
		svc.park("1");
		ParkingServiceDTO dto = svc.park("2");
		assertTrue(dto.getMessages().get("errorMsg").toString().equals("Sorry, parking lot is full"));
	}
	
	public void dataExist() {
		ParkingService svc = new ParkingService();
		svc.createParkingLot(1);
		svc.park("1");
		ParkingServiceDTO dto = svc.park("1");
		assertTrue(dto.getMessages().get("errorMsg").toString().equals("Data has already exists!"));
	}
}