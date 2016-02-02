package ama.maduwafaa.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ama.maduwafaa.entity.ClassStream;
import ama.maduwafaa.entity.Course;
import ama.maduwafaa.entity.Criteria;
import ama.maduwafaa.entity.Role;
import ama.maduwafaa.entity.User;
import ama.maduwafaa.repository.AssGradeRepository;
import ama.maduwafaa.repository.AssignmentXRepository;
import ama.maduwafaa.repository.ClassStreamRepository;
import ama.maduwafaa.repository.CourseRepository;
import ama.maduwafaa.repository.CriteriaRepository;
import ama.maduwafaa.repository.RoleRepository;
import ama.maduwafaa.repository.StudentRepository;
import ama.maduwafaa.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	//Spring will inject the instance of role repository in here
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AssGradeRepository assGradeRepository;
	
	@Autowired
	private AssignmentXRepository assignmentRepository;
	
	@Autowired
	private ClassStreamRepository classStreamRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CriteriaRepository criteriaRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	//Initialize the database. This method call the spring context creation
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setRoleName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setRoleName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		//Create admin user
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setUserName("admin");
		userAdmin.setfName("John");
		userAdmin.setlName("Casey");
		userAdmin.setEmail("admin@gmail.com");	
		//Encode the password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);	
		
		/*//Initial Course data 1
		Course course1 = new Course();
		course1.setCourseName("Networking Fundamentals");
		courseRepository.save(course1);
		
		//Initial Course data 2
		Course course2 = new Course();
		course2.setCourseName("Operating System Fundamentals");
		courseRepository.save(course2);
		
		
		//Initial data for class stream table
		ClassStream clsStream = new ClassStream();
		clsStream.setClassNo(6201);
		clsStream.setUser(userAdmin);
		clsStream.setCourse(course1);
		classStreamRepository.save(clsStream);*/
		//clsStream.set
		//Initial data of the Criteria table
		
		/*Criteria criteria1 = new Criteria();
		criteria1.setDescription("System Implimentation");
		criteria1.setPossibleMarks(60);
		criteriaRepository.save(criteria1);
		
		Criteria criteria2 = new Criteria();
		criteria2.setDescription("Program Documentation");
		criteria2.setPossibleMarks(10);
		criteriaRepository.save(criteria2);
		
		Criteria criteria3 = new Criteria();
		criteria3.setDescription("Testing and Result");
		criteria3.setPossibleMarks(5);
		criteriaRepository.save(criteria3);*/
		
	}
	

}
