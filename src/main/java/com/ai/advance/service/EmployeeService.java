package com.ai.advance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.advance.config.ResourceNotFoundException;
import com.ai.advance.model.Employee;
import com.ai.advance.model.EmployeeDTO;
import com.ai.advance.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}

	public Employee getById(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Id Not found"));
	}

	public void addEmployee(EmployeeDTO employee) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setId(employee.getId());
		emp.setAddress(employee.getAddress());
		emp.setName(employee.getName());
		emp.setAge(employee.getAge());
		employeeRepository.save(emp);
	}

	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee exist = getById(employee.getId());
		if(exist !=null) {
			exist.setName(employee.getName());
			exist.setAddress(employee.getAddress());
			exist.setAge(employee.getAge());
			employeeRepository.save(exist);
		}
		
	}

	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		Employee exist = getById(id);
		if(exist !=null) {
			employeeRepository.delete(exist);
		}
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

}
