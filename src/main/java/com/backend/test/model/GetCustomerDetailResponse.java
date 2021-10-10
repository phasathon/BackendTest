package com.backend.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCustomerDetailResponse {
	@JsonProperty("username")
	private String username;
	@JsonProperty("address")
	private String address;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("customer_type")
	private String customerType;
	@JsonProperty("create_date")
	private String createDate;
	@JsonProperty("reference")
	private String reference;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	@Override
	public String toString() {
		return "GetCustomerDetailResponse [username=" + username + ", address=" + address + ", phone=" + phone
				+ ", customerType=" + customerType + ", createDate=" + createDate + ", reference=" + reference + "]";
	}
	
}
