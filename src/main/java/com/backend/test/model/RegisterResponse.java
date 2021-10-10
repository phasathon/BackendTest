package com.backend.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("customer_type")
	private String customerType;
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	@Override
	public String toString() {
		return "RegisterResponse [reference=" + reference + ", customerType=" + customerType + "]";
	}
	
}
