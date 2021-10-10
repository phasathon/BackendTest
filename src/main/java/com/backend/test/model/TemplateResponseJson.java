/**
 * 
 */
package com.backend.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author GHB-BIG
 *
 */
public class TemplateResponseJson {
	
	@JsonProperty("api_status")
	private int apiStatus;
	@JsonProperty("api_message")
	private String apiMessage;
	@JsonProperty("data")
	private Object data;
	public int getApiStatus() {
		return apiStatus;
	}
	public void setApiStatus(int apiStatus) {
		this.apiStatus = apiStatus;
	}
	public String getApiMessage() {
		return apiMessage;
	}
	public void setApiMessage(String apiMessage) {
		this.apiMessage = apiMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "TemplateResponseJson [apiStatus=" + apiStatus + ", apiMessage=" + apiMessage + ", data=" + data + "]";
	}
	
	
}
