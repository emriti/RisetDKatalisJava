package com.dkatalis.parkinglot.entity;

public class ParkingEntity {
	private int slotNo;
	private String registrationNo;

	public ParkingEntity() {
		
	}
	
	public ParkingEntity(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	
	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
}
