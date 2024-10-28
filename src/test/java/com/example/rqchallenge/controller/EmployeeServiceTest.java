package com.example.rqchallenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.rqchallenge.RqChallengeApplicationTests;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.EmployeeByIdResponseDTO;
import com.example.rqchallenge.model.EmployeeListResponseDTO;
import com.example.rqchallenge.service.employee.EmployeeService;
import com.example.rqchallenge.utils.MockDataGenerator;

import reactor.core.publisher.Mono;

/*
 * Sevice Layer testing can also be done via mockwebserver dependecy as well.
 */

public class EmployeeServiceTest extends RqChallengeApplicationTests {

	private EmployeeListResponseDTO empDto = new EmployeeListResponseDTO();

	private EmployeeByIdResponseDTO empByIdDto = new EmployeeByIdResponseDTO();

	@InjectMocks
	private EmployeeService employeeService;
	@Mock
	private WebClient webClient;
	@Mock
	private WebClient.Builder webClientBuilder;
	@Mock
	private WebClient.RequestHeadersUriSpec RequestHeadersUriSpec;
	@Mock
	private WebClient.RequestHeadersSpec requestHeaderSpec;
	@Mock
	private WebClient.RequestBodyUriSpec requestBodyUriSpec;
	@Mock
	private WebClient.RequestBodySpec requestBodySpec;
	@Mock
	private WebClient.ResponseSpec responseSpec;

	@BeforeEach
	public void setup() {
		empDto = MockDataGenerator.getEmployeeListResponseDTO();
		empByIdDto = MockDataGenerator.getEmployeeByIdResponseDTO();
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		getAllEmployee();

		List<Employee> allEmployeesList = employeeService.getAllEmployeeList();

		assertEquals(allEmployeesList, empDto.getData());
		assertEquals(allEmployeesList.size(), empDto.getData().size());
	}

	@Test
	public void testGetEmployeesByNameSearch() throws Exception {
		getAllEmployee();

		List<Employee> allEmployeesList = employeeService.getEmployeeByName("am");

		Assertions.assertEquals(allEmployeesList.get(0).getEmployeeName(), "Aman Bhati");
		Assertions.assertEquals(allEmployeesList.get(1).getEmployeeName(), "Amit");

	}

	@Test
	public void testGetEmployeeById() throws Exception {
		getEmployeeByID();

		Employee employee = employeeService.getEmployeeById("1");

		Assertions.assertEquals(employee, empByIdDto.getData());
	}

	@Test
	public void testGetHighestSalaryOfEmployees() throws Exception {
		getAllEmployee();

		Integer highestSalaryOfEmployee = employeeService.getHighestSalaryOfEmployee();

		Assertions.assertEquals(highestSalaryOfEmployee, 100006);
	}

	@Test
	public void testGetTopTenHighestEarningEmployeeNames() throws Exception {
		getAllEmployee();

		List<String> topTenHighestEarningEmployeeNames = employeeService.getTopTenHighestEarningEmployeeNames();

		assertEquals(topTenHighestEarningEmployeeNames.contains("Aman Bhati"), false);
		assertEquals(topTenHighestEarningEmployeeNames.contains("Jenette"), true);
		assertEquals(topTenHighestEarningEmployeeNames.size(), 10);
	}

	@Test
	public void testCreateEmployee() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Aman Bhati");
		map.put("salary", "1000");
		map.put("age", "28");

		when(webClient.post()).thenReturn(requestBodyUriSpec);
		when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
		when(requestBodySpec.header(any(), any())).thenReturn(requestBodySpec);
		when(requestBodySpec.body(any())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		EmployeeByIdResponseDTO empDto = MockDataGenerator.getEmployeeByIdResponseDTO();
		when(responseSpec.bodyToMono(EmployeeByIdResponseDTO.class)).thenReturn(Mono.just(empDto));

		Employee employee = employeeService.createEmployee(map);

		assertEquals(empDto.getData(), employee);
	}

	@Test
	public void testDeleteEmployee() throws Exception {

		getEmployeeByID();

		when(webClient.delete()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.header(any(), any())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(empByIdDto));

		String employeeName = employeeService.deleteEmployeeById("1");

		assertEquals(employeeName, "Aman Bhati");

	}

	private void getAllEmployee() {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeListResponseDTO.class)).thenReturn(Mono.just(empDto));
	}

	private void getEmployeeByID() {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeByIdResponseDTO.class)).thenReturn(Mono.just(empByIdDto));
	}

}