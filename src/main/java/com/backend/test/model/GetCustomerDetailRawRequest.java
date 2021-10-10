package com.backend.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCustomerDetailRawRequest {
	@JsonProperty("data")
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetCustomerDetailRawRequest [data=" + data + "]";
	}
	
}
