package ama.maduwafaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ama.maduwafaa.entity.Assignment;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

/*	Assignment findByAssignmentName(String assigName);*/
	
}
