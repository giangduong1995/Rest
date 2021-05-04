package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.exception.ResourceNotFoundException;
import com.crud.model.Address;
import com.crud.model.Employee;
import com.crud.repository.EmployeeRepository;
import com.crud.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/get/employees")
	public ResponseEntity<?> getAllEmp() {

		try {
			List<Employee> employees = employeeRepository.findAll();
			return ResponseEntity.ok(employees);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/getId")
	public ResponseEntity<?> getEmpById(@RequestParam Long id) {

		try {
			Optional<Employee> employee = employeeRepository.findById(id);
			return ResponseEntity.ok(employee);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/getFirstname")
	public ResponseEntity<?> getEmpByFirstName(@RequestParam String firstname) {

		try {
			Boolean check = employeeService.checkFirstName(firstname);
			if (check) {
				Employee findByFirstname = employeeRepository.findByFirstname(firstname);
				return ResponseEntity.ok(findByFirstname);
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/post/employee")
	public ResponseEntity<?> createEmp(@RequestBody Employee employeeDetail) {

		try {
			
			Employee newEmployee = employeeRepository.save(employeeDetail);
			return ResponseEntity.ok(newEmployee);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/put/employee/{id}")
	public ResponseEntity<?> updateEmp(@PathVariable Long id, @RequestBody Employee employeeDetail)
			throws ResourceNotFoundException {

		try {
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
			employee.setFirstname(employeeDetail.getFirstname());
			employee.setLastname(employeeDetail.getLastname());
			employee.setEmail(employeeDetail.getEmail());
			employee.setGender(employeeDetail.getGender());
			employee.setAge(employeeDetail.getAge());
			employee.setAddress(new Address(employeeDetail.getAddress().getId(), employeeDetail.getAddress().getStreet()));

			final Employee updateEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updateEmployee);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/delete/employees")
	public ResponseEntity<?> deleteEmp() {
		try {
			employeeRepository.deleteAll();
			List<Employee> employees = employeeRepository.findAll();
			return ResponseEntity.ok(employees);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/delete/employee/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable Long id) {
		try {
			employeeRepository.deleteById(id);
			List<Employee> employees = employeeRepository.findAll();
			return ResponseEntity.ok(employees);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

}
