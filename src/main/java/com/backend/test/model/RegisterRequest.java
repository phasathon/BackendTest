/**
 * 
 */
package com.backend.test.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author GHB-BIG
 *
 */
public class RegisterRequest {
	
	@NotNull
	@JsonProperty("username")
	private String username;
	@NotNull
	@JsonProperty("password")
	private String password;
	@NotNull
	@JsonProperty("address")
	private String address;
	@NotNull
	@JsonProperty("phone")
	private String phone;
	@NotNull
	@JsonProperty("salary")
	private int salary;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "RegisterRequest [username=" + username + ", password=" + password + ", address=" + address + ", phone="
				+ phone + ", salary=" + salary + "]";
	}
	
	
	
}
