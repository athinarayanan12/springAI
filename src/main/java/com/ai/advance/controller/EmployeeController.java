package com.ai.advance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.advance.model.Employee;
import com.ai.advance.model.EmployeeDTO;
import com.ai.advance.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public Employee getById(@PathVariable Long id) {
		return employeeService.getById(id);
	}
	@PostMapping("/add")
	public void addEmployee(@RequestBody EmployeeDTO employee) {
		employeeService.addEmployee(employee);
	}
	@PutMapping("/update")
	public String updateEmployee(@RequestBody Employee employee) {
		
		employeeService.updateEmployee(employee);
		return "Employee updated successfully";
	}
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "Employee Deleted successfully";
	}
}
