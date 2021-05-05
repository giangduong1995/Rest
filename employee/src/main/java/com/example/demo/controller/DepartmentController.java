package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;


	@GetMapping("/departments")
	public ResponseEntity<?> getAllDepartment() {

		try {
			List<Department> departments = departmentRepository.findAll();
			return ResponseEntity.ok(departments);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/department/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {

		try {
			Optional<Department> department = departmentRepository.findById(id);
			return ResponseEntity.ok(department);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	

	@PostMapping("/department")
	public ResponseEntity<?> createDepartment(@RequestBody Department departmentDetail) {

		try {
			
			Department newDepartment = departmentRepository.save(departmentDetail);
			return ResponseEntity.ok(newDepartment);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/department/{id}")
	public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetail)
			throws ResourceNotFoundException {

		try {
			Department department = departmentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
			department.setDepartmentName(departmentDetail.getDepartmentName());
			department.setManager(departmentDetail.getManager());
			department.setNumberOfEmployee(departmentDetail.getNumberOfEmployee());

			final Department updateDepartment = departmentRepository.save(department);
			return ResponseEntity.ok(updateDepartment);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/departments")
	public ResponseEntity<?> deleteDepartment() {
		try {
			departmentRepository.deleteAll();
			List<Department> departments = departmentRepository.findAll();
			return ResponseEntity.ok(departments);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/department/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable Long id) {
		try {
			departmentRepository.deleteById(id);
			List<Department> department = departmentRepository.findAll();
			return ResponseEntity.ok(department);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

}
