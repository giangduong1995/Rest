package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class employeeServiceImpl implements employeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmp() {
		return employeeRepository.findAll();
	}
	
	@Override 
	public Optional<Employee> getEmpById(Long id) {
		return employeeRepository.findById(id);
	}
	
	@Override 
	public Employee create(Employee employe) {
		return employeeRepository.save(employe);
	}
	
	@Override
	public void remove(Long id) {
		employeeRepository.deleteById(id);
	}
	
	@Override 
	public Employee editEmployee(Long id, Employee employee) {
		Employee updateEmployeeDb = employeeRepository.findById(id).get(); 
			updateEmployeeDb.setAge(employee.getAge());
			updateEmployeeDb.setEmail(employee.getEmail());
			updateEmployeeDb.setFirstName(employee.getFirstName());
			updateEmployeeDb.setLastName(employee.getLastName());
			updateEmployeeDb.setEmail(employee.getEmail());
			updateEmployeeDb.setDepartment(employee.getDepartment());
		return employeeRepository.save(updateEmployeeDb);
	}
	
}
