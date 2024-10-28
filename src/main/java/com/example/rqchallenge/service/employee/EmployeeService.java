package com.example.rqchallenge.service.employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.rqchallenge.common.Constants;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.EmployeeByIdResponseDTO;
import com.example.rqchallenge.model.EmployeeListResponseDTO;

import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	public WebClient webClient;

	@Override
	public List<Employee> getAllEmployeeList() {
		Mono<EmployeeListResponseDTO> employeeMono = webClient.get().uri(Constants.REST_API_URI_GET_ALL_EMPLOYEES)
				.retrieve().bodyToMono(EmployeeListResponseDTO.class);
		return employeeMono.block().getData();
	}

	@Override
	public Employee getEmployeeById(String id) {

		Mono<EmployeeByIdResponseDTO> employeeMono = webClient.get().uri(Constants.REST_API_URI_GET_EMPLOYEE_BY_ID + id)
				.retrieve().bodyToMono(EmployeeByIdResponseDTO.class);

		return employeeMono.block().getData();
	}

	@Override
	public List<Employee> getEmployeeByName(String searchString) {

		List<Employee> employeeList = getAllEmployeeList();

		return employeeList.stream()
				.filter(emp -> emp.getEmployeeName().toLowerCase().contains(searchString.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public Integer getHighestSalaryOfEmployee() {

		List<Employee> employeeList = getAllEmployeeList();

		return employeeList.stream().map(emp -> emp.getEmployeeSalary()).max(Comparator.naturalOrder()).get();
	}

	@Override
	public List<String> getTopTenHighestEarningEmployeeNames() {

		List<Employee> employeeList = getAllEmployeeList();

		return employeeList.stream().sorted().map(emp -> emp.getEmployeeName()).limit(10).collect(Collectors.toList());
	}

	@Override
	public Employee createEmployee(Map<String, Object> employeeInput) {

		EmployeeByIdResponseDTO employeeMono = webClient.post().uri(Constants.REST_API_URI_CREATE_EMPLOYEE)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(employeeInput)).retrieve().bodyToMono(EmployeeByIdResponseDTO.class)
				.block();

		return employeeMono.getData();
	}

	@Override
	public String deleteEmployeeById(String id) {

		Employee employee = getEmployeeById(id);
		webClient.delete().uri(Constants.REST_API_URI_DELETE_EMPLOYEE + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(Object.class)
				.block();

		return employee.getEmployeeName();
	}

}
