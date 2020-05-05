package com.dkatalis.parkinglot.parkingrepository;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.repository.ParkingRepository;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AddTest extends TestCase {

	public AddTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new AddTest("checkDataExists"));
		suite.addTest(new AddTest("dataFull"));
		suite.addTest(new AddTest("dataSaved"));
		return suite;
	}

	public void checkDataExists() {
		String msg = "";
		try {
			ParkingRepository repo = new ParkingRepository(2);
			repo.add(new ParkingEntity("1"));
			repo.add(new ParkingEntity("1"));
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertTrue(msg.equals("Data has already exists!"));
	}
	
	public void dataFull() {
		String msg = "";
		try {
			ParkingRepository repo = new ParkingRepository(1);
			repo.add(new ParkingEntity("1"));
			repo.add(new ParkingEntity("2"));
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertTrue(msg.equals("Sorry, parking lot is full"));
	}
	
	public void dataSaved() {
		try {
			ParkingRepository repo = new ParkingRepository(1);
			ParkingEntity savedData = repo.add(new ParkingEntity("1"));
			assertTrue(savedData.getRegistrationNo().equals("1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
