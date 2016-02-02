package ama.maduwafaa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ama.maduwafaa.entity.Assignment;

@Repository
public class AssignmentXRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Assignment assignment){
		this.em.merge(assignment);
	}

	public Assignment findOne(int assignmentId) {        // using 'join fetch' because a single query should load both owners and pets
        // using 'left join fetch' because it might happen that an owner does not have pets yet
        Query query = this.em.createQuery("SELECT assignment FROM Assignment assignment left join fetch assignment.criterias WHERE assignment.id =:id");
        query.setParameter("id", assignmentId);
        return (Assignment) query.getSingleResult();
	}
}
