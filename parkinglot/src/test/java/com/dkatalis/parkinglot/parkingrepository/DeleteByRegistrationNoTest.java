package com.dkatalis.parkinglot.parkingrepository;

import com.dkatalis.parkinglot.entity.Parking;
import com.dkatalis.parkinglot.repository.ParkingRepository;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DeleteByRegistrationNoTest extends TestCase {
	
	public DeleteByRegistrationNoTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new DeleteByRegistrationNoTest("dataNotFound"));
		suite.addTest(new DeleteByRegistrationNoTest("dataDeleted"));
		return suite;
	}

	public void dataNotFound() {
		String msg = "";
		try {
			ParkingRepository repo = new ParkingRepository(1);
			repo.deleteByRegistrationNo("2");
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertTrue(msg.equals("Data not found!"));
	}
	
	public void dataDeleted() {
		try {
			ParkingRepository repo = new ParkingRepository(1);
			repo.add(new Parking("1"));
			Parking savedData = repo.delete(new Parking("1"));
			assertTrue(savedData.getRegistrationNo().equals("1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
