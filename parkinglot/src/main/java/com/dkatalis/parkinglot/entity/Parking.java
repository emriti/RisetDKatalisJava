package com.dkatalis.parkinglot.entity;

public class Parking {
	private int slotNo;
	private String registrationNo;

	public Parking() {
		
	}
	
	public Parking(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	
	public Parking(int slotNo, String registrationNo) {
		this.slotNo = slotNo;
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
