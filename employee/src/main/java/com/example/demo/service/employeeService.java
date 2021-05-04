package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Employee;

public interface employeeService {
	
	List<Employee> getAllEmp();
	
	Optional<Employee> getEmpById(Long id);
	
	Employee create(Employee employee);
	
	void remove(Long id);
	
	Employee editEmployee(Long id, Employee employee);	
	
}
