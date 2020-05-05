package com.dkatalis.parkinglot.parkingrepository;

import java.util.List;

import com.dkatalis.parkinglot.entity.Parking;
import com.dkatalis.parkinglot.repository.ParkingRepository;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ResizeTest extends TestCase {
	
	public ResizeTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite= new TestSuite();
		suite.addTest(new ResizeTest("checkDataSizeAfterInitialization"));
		suite.addTest(new ResizeTest("checkDataSizeAfterResize"));
		return suite;
	}
	
	public void checkDataSizeAfterInitialization() {
		try {
			ParkingRepository repo = new ParkingRepository(1);
			List<Parking> list = repo.getAll();
			assertTrue(list.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkDataSizeAfterResize() {
		try {
			ParkingRepository repo = new ParkingRepository(3);
			repo.resize(2);
			List<Parking> list = repo.getAll();
			assertTrue(list.size() == 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
