package com.dkatalis.parkinglot.repository;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class Repository<T> {
	
	private LinkedHashMap<Integer, T> data;
	
	public Repository() {
		data = new LinkedHashMap<Integer, T>();
	}
	
	public void resize(int capacity) {
	}
	
	public void add(T item) {
	}
	
	public void delete(T item) {
	}
	
	public void deleteByKey(Integer key) {
		
	}
	
	public List<T> getAll() {
		return null;
	}
}
