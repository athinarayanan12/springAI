package com.ai.advance.config;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.ai.advance.model.Employee;
import com.ai.advance.model.EmployeeDTO;
import com.ai.advance.service.EmployeeService;
/*Communicate with db*/
@Component
public class AITool {
	@Autowired
	private EmployeeService employeeService;
	
	@Tool(description = "Retrive information about employee")
	public Employee getEmployee(Long id ) {
		return employeeService.getById(id);
		
	}
	@Tool(description = "Retrive information of all employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
		
	}
	@Tool(description = "Update information of employee such as name,address,age")
	public void updateEmployee(Employee emp) {
		employeeService.updateEmployee(emp);
		
	}
	@Tool(description = "delete information of employee and return details of employee")
	public void deleteEmployee(Long id) {
		employeeService.deleteEmployeeById(id);
		
	}
}
