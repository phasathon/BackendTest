package com.backend.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenResponse {
	@JsonProperty("token")
	private String token;


	public AuthenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
