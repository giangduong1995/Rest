package com.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.one.model.InstructorDetail;

@Repository
public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Long>{

}
