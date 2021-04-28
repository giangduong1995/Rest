package com.restful.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restful.exception.ResourceNotFoundException;
import com.restful.model.Employee;
import com.restful.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		List<Employee> listEmployee = employeeRepository.findAll();
		return listEmployee;
	}
	
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployee(@PathVariable Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee;
	}
	
	@PostMapping("/employees")
	public void createEmployee(@Validated @RequestBody Employee employee) {
		employeeRepository.save(employee);
	}
	
	@DeleteMapping("/employees")
	public void deleteEmployee() {
		employeeRepository.deleteAll();
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@Validated @PathVariable Long id, @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));;
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setAddress(employeeDetails.getAddress());
		
		final Employee updateEmployee = employeeRepository.save(employee);
		
		return updateEmployee;
		
		
	
	}
	
	
	
	

}
