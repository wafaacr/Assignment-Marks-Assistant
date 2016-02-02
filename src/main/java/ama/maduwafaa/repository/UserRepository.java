package ama.maduwafaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ama.maduwafaa.entity.User;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);

}
