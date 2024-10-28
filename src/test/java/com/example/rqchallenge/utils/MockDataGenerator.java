package com.example.rqchallenge.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.EmployeeByIdResponseDTO;
import com.example.rqchallenge.model.EmployeeListResponseDTO;

public class MockDataGenerator {

	public static EmployeeListResponseDTO getEmployeeListResponseDTO() {
		EmployeeListResponseDTO responseDTO = new EmployeeListResponseDTO();

		responseDTO.setData(getEmployeeList());
		return responseDTO;
	}

	public static EmployeeByIdResponseDTO getEmployeeByIdResponseDTO() {
		EmployeeByIdResponseDTO responseDTO = new EmployeeByIdResponseDTO();
		responseDTO.setData(getEmployee());
		responseDTO.setStatus("Success");
		return responseDTO;
	}

	public static Employee getEmployee() {
		Employee emp = new Employee(1l,"Aman Bhati",28,1234,"");
		return emp;
	}
	
	public static List<Employee> getEmployeeList() {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1l,"Aman Bhati",23,1000,""));
		empList.add(new Employee(2l,"Amit",25,1002,""));
		empList.add(new Employee(3l,"Vance",26,1004,""));
		empList.add(new Employee(4l,"Caesar",28,100001,""));
		empList.add(new Employee(5l,"Yuri",28,100006,""));
		empList.add(new Employee(6l,"Jenette",28,10002,""));
		empList.add(new Employee(7l,"Haley",28,10003,""));
		empList.add(new Employee(8l,"Tiger",28,10004,""));
		empList.add(new Employee(9l,"Nixon",28,10005,""));
		empList.add(new Employee(10l,"Bradley",28,10006,""));
		empList.add(new Employee(11l,"Doris",28,10007,""));


		return empList;
	}
}
