package com.dkatalis.parkinglot.repository;

import java.util.LinkedHashMap;
import java.util.List;

import com.dkatalis.parkinglot.entity.Parking;

public abstract class Repository<T> {
	
	@SuppressWarnings("unused")
	private LinkedHashMap<Integer, T> data;
	
	public Repository() {
		data = new LinkedHashMap<Integer, T>();
	}
	
	public void resize(int capacity) {
	}
	
	public T add(T item) throws Exception {
		return item;
	}
	
	public T delete(T item) throws Exception {
		return item;
	}
	
	public void deleteByKey(Integer key) {
		
	}
	
	public List<T> getAll() {
		return null;
	}


}
