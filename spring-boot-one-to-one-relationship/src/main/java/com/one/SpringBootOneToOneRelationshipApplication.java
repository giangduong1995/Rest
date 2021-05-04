package com.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.one.model.Instructor;
import com.one.model.InstructorDetail;
import com.one.repository.InstructorDetailRepository;
import com.one.repository.InstructorRepository;

@SpringBootApplication
public class SpringBootOneToOneRelationshipApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOneToOneRelationshipApplication.class, args);
    }

    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    @Override
    public void run(String...args) throws Exception {

        Instructor instructor = new Instructor(1l, "Ramesh", "Fadatare", "ramesh@gmail.com", null);

        InstructorDetail instructorDetail = new InstructorDetail(1l, "Java Guides", "Cricket Playing", null);

        // associate the objects
        instructor.setInstructorDetail(instructorDetail);

        instructorRepository.save(instructor);
        
        instructorDetail.setInstructor(instructor);
        
        instructorDetailRepository.save(instructorDetail);
        
        
    }
}