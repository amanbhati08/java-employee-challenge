package com.example.rqchallenge.service.employee;

import java.util.List;
import java.util.Map;

import com.example.rqchallenge.model.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmployeeList();

	Employee getEmployeeById(String id);

	List<Employee> getEmployeeByName(String searchString);

	Integer getHighestSalaryOfEmployee();

	List<String> getTopTenHighestEarningEmployeeNames();

	Employee createEmployee(Map<String, Object> employeeInput);

	String deleteEmployeeById(String id);
	
}
