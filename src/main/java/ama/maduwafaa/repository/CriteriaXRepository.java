package ama.maduwafaa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ama.maduwafaa.entity.Criteria;

@Repository
public class CriteriaXRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Criteria> findAll() {       
		return this.em.createQuery("SELECT criteria FROM Criteria").getResultList();
	}

	public void delete(Criteria criteria) {
		this.em.remove(criteria);
	}

	public void save(Criteria criteria) {
		this.em.merge(criteria);
	}

	
}
