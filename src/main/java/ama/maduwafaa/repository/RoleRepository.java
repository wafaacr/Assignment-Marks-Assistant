package ama.maduwafaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ama.maduwafaa.entity.Role;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
	
}

