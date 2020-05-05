package com.dkatalis.parkinglot.service;

import java.util.List;

import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.entity.ParkingServiceDTO;
import com.dkatalis.parkinglot.repository.ParkingRepository;

public class ParkingService {

	private ParkingRepository repo;

	public ParkingService() {
		repo = new ParkingRepository();
	}

	public int createParkingLot(int capacity) {
		repo.resize(capacity);
		return capacity;
	}

	public ParkingServiceDTO park(String registrationNo) {
		ParkingServiceDTO dto = new ParkingServiceDTO();
		dto.addNewMessage("registrationNo", registrationNo);
		try {
			ParkingEntity parkingEntity = repo.add(registrationNo);
			dto.addNewMessage("slotNo", parkingEntity.getSlotNo());
		} catch (Exception e) {
			dto.addNewMessage("errorMsg", new StringBuilder(e.getMessage()));
		}
		return dto;
	}

	public ParkingServiceDTO remove(String registrationNo, int hours) {
		ParkingServiceDTO dto = new ParkingServiceDTO();
		try {
			ParkingEntity parkingEntity = repo.deleteByRegistrationNo(registrationNo);
			dto.addNewMessage("slotNo", parkingEntity.getSlotNo());
			dto.addNewMessage("charge", calculateCharge(hours));
		} catch (Exception e) {
			dto.addNewMessage("errorMsg", new StringBuilder(e.getMessage()));
		}
		return dto;
	}

	public List<ParkingEntity> getStatus() {
		return repo.getAll();
	}

	private int calculateCharge(int hours) {
		int charge = 0;
		if (hours > 0 && hours <= 2 ) {
			charge = 10;
		} else if (hours > 2) {
			charge = 10;
			charge += (hours - 2) * 10;
		}
		return charge;
	}
}
