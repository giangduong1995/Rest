package com.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.Employee;
import com.crud.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Boolean checkFirstName(String name) {
		Employee employee = employeeRepository.findByFirstname(name);
		if (employee.getFirstname().length() < 2) {
			return false;
		}
		return true;
	}
}
