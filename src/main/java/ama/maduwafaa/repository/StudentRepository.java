package ama.maduwafaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ama.maduwafaa.entity.Student;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
public interface StudentRepository extends JpaRepository <Student, Integer> {

}
