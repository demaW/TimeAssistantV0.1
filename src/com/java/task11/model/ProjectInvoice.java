package com.java.task11.model;


public class ProjectInvoice {
private String taskName;
private String taskState;
private String firstName;
private String lastName;
private String position;
private int workedTime;
private int planedTime;
private double salaryRate;
private double costPerEmployee;
private double planedCostPerEmployee;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getPosition() {
	return position;
}
public void setPosition(String posistion) {
	this.position = posistion;
}
public int getWorkedTime() {
	return workedTime;
}
public void setWorkedTime(int workedTime) {
	this.workedTime = workedTime;
}
public double getSalaryRate() {
	return salaryRate;
}
public void setSalaryRate(double salaryRate) {
	this.salaryRate = salaryRate;
}
public double getCosPerEmployee() {
	return costPerEmployee;
}
public void setCosPerEmployee() {
		
	this.costPerEmployee =  getSalaryRate() * getWorkedTime();
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public int getPlanedTime() {
	return planedTime;
}
public void setPlanedTime(int planedTime) {
	this.planedTime = planedTime;
}
public double getPlanedCostPerEmployee() {
	return planedCostPerEmployee;
}
public void setPlanedCostPerEmployee() {
	this.planedCostPerEmployee = getSalaryRate() * getPlanedTime();
}
public String getTaskState() {
	return taskState;
}
public void setTaskState(String taskState) {
	this.taskState = taskState;
}


}
