package com.dkatalis.parkinglot.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import com.dkatalis.parkinglot.entity.Parking;

public class ParkingRepository extends Repository<Parking> {

	private LinkedHashMap<Integer, Parking> data;

	public ParkingRepository() {
		data = new LinkedHashMap<Integer, Parking>();
	}

	private int getEmptySlot() {
		Optional<Entry<Integer, Parking>> tmp = data.entrySet().stream().filter(p -> p.getValue().equals(null))
				.findFirst();
		if (tmp != null) {
			return tmp.get().getKey();
		}
		return -1;
	}
	
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
	public void add(Parking item) {
		Optional<Entry<Integer, Parking>> tmp = data.entrySet().stream().filter(p -> p.getValue().getRegistrationNo().equals(item.getRegistrationNo())).findFirst();
		// add if empty
		if (tmp.equals(null)) {
			int emptySlot = getEmptySlot();
			item.setSlotNo(emptySlot);
			data.put(emptySlot, item);	
		}
	}

	@Override
	public void deleteByKey(Integer key) {
		Optional<Entry<Integer, Parking>> tmp = data.entrySet().stream().filter(p -> p.getValue().getRegistrationNo().equals(key)).findFirst();
		// delete if exists
		if (tmp.equals(null)) {
			data.remove(tmp.get().getKey());
		}	
	}

	@Override
	public List<Parking> getAll() {
		return (List<Parking>) data.values();
	}

	
}
