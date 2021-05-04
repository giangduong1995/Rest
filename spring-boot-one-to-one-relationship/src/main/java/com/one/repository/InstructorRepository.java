package com.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.one.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{
	

}
