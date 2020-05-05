package com.dkatalis.parkinglot.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.dkatalis.parkinglot.entity.ParkingEntity;

public class ParkingRepository extends Repository<ParkingEntity> {

	private LinkedHashMap<Integer, ParkingEntity> data;

	public ParkingRepository() {
		data = new LinkedHashMap<Integer, ParkingEntity>();
	}

	public ParkingRepository(int capacity) {
		data = new LinkedHashMap<Integer, ParkingEntity>();
		resize(capacity);
	}

	@Override
	public void resize(int capacity) {
		if (!data.isEmpty() && capacity < data.size()) {
			for (int i = capacity + 1; i <= data.size(); i++) {
				data.remove(i);
			}
		} else if (data.isEmpty()) {
			for (int i = 1; i <= capacity; i++) {
				data.put(i, null);
			}
		}
	}

	@Override
	public ParkingEntity add(ParkingEntity item) throws Exception {
		// check if data exists
		int id = getIdByRegistrationNo(item.getRegistrationNo());
		// add if empty
		if (id == -1) {
			int emptySlot = getEmptySlot();
			if (emptySlot > 0) {
				item.setSlotNo(emptySlot);
				data.put(emptySlot, item);
				return item;
			} else {
				throw new Exception("Sorry, parking lot is full");	
			}
		} else {
			throw new Exception("Data has already exists!");
		}
	}

	public ParkingEntity deleteByRegistrationNo(String registrationNo) throws Exception {
		int id = getIdByRegistrationNo(registrationNo);
		// delete if exists
		if (id > 0) {
			ParkingEntity item = data.get(id);
			data.remove(id);
			return item;
		} else {
			throw new Exception("Data not found!");
		}
	}

	@Override
	public List<ParkingEntity> getAll() {
		return data.entrySet().stream().map(m -> m.getValue()).collect(Collectors.toList());
	}

	private int getEmptySlot() {
		for (Entry<Integer, ParkingEntity> set : data.entrySet()) {
			if (set.getValue() == null) {
				return set.getKey();
			}
		}
		return -1;
	}

	private int getIdByRegistrationNo(String registrationNo) {
		for (Entry<Integer, ParkingEntity> set : data.entrySet()) {
			if (set.getValue() != null) {
				if (set.getValue().getRegistrationNo().equals(registrationNo)) {
					return set.getKey();
				}
			}
		}
		return -1;
	}
}
