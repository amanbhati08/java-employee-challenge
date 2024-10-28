package com.example.rqchallenge.model;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Employee implements Comparable<Employee> {

	@JsonProperty("id")
    private long id;

    @JsonProperty("employee_name")
    @JsonAlias("name")
    private String employeeName;

    @JsonProperty("employee_salary")
    @JsonAlias("salary")
    private int employeeSalary;

    @JsonProperty("employee_age")
    @JsonAlias("age")
    private int employeeAge;

    @JsonProperty("profile_image")
    @JsonAlias("place")
    private String profileImage;
    
    public Employee(long id, String employeeName, int employeeAge, int employeeSalary, String profileImage) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.profileImage = profileImage;
	}

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(employeeName, employee.employeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeName);
    }
    
	@Override
	public int compareTo(Employee o) {
		
		return o.employeeSalary-this.employeeSalary;
	}
}
