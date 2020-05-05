package com.dkatalis.parkinglot.parkingrepository;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.repository.ParkingRepository;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GetAllTest extends TestCase {

	public GetAllTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new GetAllTest("getAll"));
		return suite;
	}

	public void getAll() {
		try {
			ParkingRepository repo = new ParkingRepository(1);
			repo.add(new ParkingEntity("1"));
			List<ParkingEntity> list = repo.getAll();
			assertTrue(list.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
