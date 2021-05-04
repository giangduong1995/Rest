package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.employeeService;

@RestController
public class EmployeeController {

	@Autowired
	private employeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.create(employee), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmp() {
		return new ResponseEntity<>(employeeService.getAllEmp(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Employee>> getEmpById(@PathVariable(value = "id") Long employeeId)  {
		Optional<Employee> employee = employeeService.getEmpById(employeeId);
		return ResponseEntity.ok().body(employee);
 	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long id) {
		employeeService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable(value = "id") Long id, @RequestBody Employee employee) {
		employeeService.editEmployee(id, employee);
        return ResponseEntity.ok(employeeService.editEmployee(id, employee));
    }
	

}
