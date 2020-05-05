package com.dkatalis.parkinglot.app;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.repository.ParkingRepository;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			ParkingRepository repo = new ParkingRepository(6);
			repo.add(new ParkingEntity("KA-01-HH-1234"));
			repo.add(new ParkingEntity("KA-01-HH-9999"));
			repo.add(new ParkingEntity("KA-01-BB-0001"));
			repo.add(new ParkingEntity("KA-01-HH-7777"));
			repo.add(new ParkingEntity("KA-01-HH-2701"));
			repo.add(new ParkingEntity("KA-01-HH-3141"));
			repo.deleteByRegistrationNo("KA-01-HH-7777");
			List<ParkingEntity> tmp = repo.getAll();
			for (ParkingEntity parking : tmp) {
				System.out.println(String.format("%s %s", parking.getSlotNo(), parking.getRegistrationNo()));
			}
//			repo.add(new Parking("KA-01-P-333"));
//			repo.add(new Parking("DL-12-AA-9999"));
//			repo.deleteByRegistrationNo("KA-01-HH-1234");
//			repo.deleteByRegistrationNo("KA-01-BB-0001");
//			repo.deleteByRegistrationNo("DL-12-AA-9999");
//			repo.add(new Parking("KA-09-HH-0987"));
//			repo.add(new Parking("CA-09-IO-1111"));
//			repo.add(new Parking("KA-09-HH-0123"));
//			tmp = repo.getAll();
//			for (Parking parking : tmp) {
//				System.out.println(String.format("v1=%s v2=%s", parking.getSlotNo(), parking.getRegistrationNo()));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
