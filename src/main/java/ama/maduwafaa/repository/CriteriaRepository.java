package ama.maduwafaa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ama.maduwafaa.entity.Assignment;
import ama.maduwafaa.entity.Criteria;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
public interface CriteriaRepository extends JpaRepository<Criteria, Integer>{
	
	List <Criteria> findByAssignment(Assignment assignment);
}
