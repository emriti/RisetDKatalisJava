package com.dkatalis.parkinglot.repository;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class Repository<T> {
	
	@SuppressWarnings("unused")
	private LinkedHashMap<Integer, T> data;
	
	protected Repository() {
		data = new LinkedHashMap<Integer, T>();
	}
	
	protected void resize(int capacity) {
	}
	
	protected T add(T item) throws Exception {
		return item;
	}
	
	protected T delete(T item) throws Exception {
		return item;
	}
	
	protected List<T> getAll() {
		return null;
	}


}
