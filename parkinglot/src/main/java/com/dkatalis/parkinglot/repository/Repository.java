package com.dkatalis.parkinglot.repository;

import java.util.List;

public interface Repository<T> {
	void initialize(int capacity);
	void add(T item);
	void delete(T item);
	List<T> getAll();
}
