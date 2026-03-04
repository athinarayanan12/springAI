package com.ai.advance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.advance.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
