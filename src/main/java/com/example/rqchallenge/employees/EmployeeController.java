package com.example.rqchallenge.employees;

import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController implements IEmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Override
	@GetMapping()
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> allEmployees = employeeService.getAllEmployeeList();
		return ResponseEntity.ok(allEmployees);
	}

	@Override
	@GetMapping("/search/{searchString}")
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) {
		List<Employee> searchedEmp = employeeService.getEmployeeByName(searchString);
		return ResponseEntity.ok(searchedEmp);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	@Override
	@GetMapping("/highestSalary")
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
		return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployee());
	}

	@Override
	@GetMapping("/topTenHighestEarningEmployeeNames")
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
		List<String> top10Emp = employeeService.getTopTenHighestEarningEmployeeNames();
		return ResponseEntity.ok(top10Emp);
	}

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) {
		Employee employee = employeeService.createEmployee(employeeInput);
		return ResponseEntity.ok(employee);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
		return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
	}
}
