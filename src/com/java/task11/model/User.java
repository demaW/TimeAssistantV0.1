package com.java.task11.model;

import com.java.task11.utils.MD5Utils;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String position;
	private int roleId;
	private Double salaryRate;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEncryptedPassword(String password) {
		this.password = MD5Utils.getMD5String(password);
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Double getSalaryRate() {
		return salaryRate;
	}

	public void setSalaryRate(Double salaryRate) {
		this.salaryRate = salaryRate;
	}

	public String toString(){
		return "Username: " + getFirstName() + " "+ getLastName() +"\n"+
				"email: " + getEmail() +"\n"+
				"password: " + getPassword()+"\n"+
				"position: " + getPosition() +"\n"+
				"salary rate: " + getSalaryRate();
	}
}
