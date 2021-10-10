package com.backend.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenRequest {
	
	@JsonProperty("key")
	private String key;
	@JsonProperty("salt")
	private String salt;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "AuthenRequest [key=" + key + ", salt=" + salt + "]";
	}
	
}
