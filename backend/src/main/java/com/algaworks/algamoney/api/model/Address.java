package com.algaworks.algamoney.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String address;
    private String number;
    private String adjunct;
    private String district;
    private String zipCode;
    private String city;
    private String state;
	
    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAdjunct() {
		return adjunct;
	}
	public void setAdjunct(String adjunct) {
		this.adjunct = adjunct;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
    
}
