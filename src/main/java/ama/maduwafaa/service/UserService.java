package ama.maduwafaa.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ama.maduwafaa.entity.Assignment;
import ama.maduwafaa.entity.ClassStream;
import ama.maduwafaa.entity.Course;
import ama.maduwafaa.entity.Criteria;
import ama.maduwafaa.entity.Role;
import ama.maduwafaa.entity.Student;
import ama.maduwafaa.entity.User;
import ama.maduwafaa.repository.AssignmentRepository;
import ama.maduwafaa.repository.ClassStreamRepository;
import ama.maduwafaa.repository.CourseRepository;
import ama.maduwafaa.repository.CriteriaRepository;
import ama.maduwafaa.repository.RoleRepository;
import ama.maduwafaa.repository.StudentRepository;
import ama.maduwafaa.repository.UserRepository;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Service
@Transactional
public class UserService {
	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassStreamRepository classStreamRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CriteriaRepository criteriaRepository;

	@Autowired
	private AssignmentRepository assignmentRepository;

	// Method of list all users in the system
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// This method return a user
	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	// New code for display criteria
	// List all criteria in the system
	public List<Criteria> findAllCriteria() {
		return criteriaRepository.findAll();
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByRoleName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	public void saveStudent(Student aStd) {
		studentRepository.save(aStd);
	}

	public List<Student> findAllStudent() {
		return studentRepository.findAll();
	}

	public void saveCourse(Course aCrs) {
		courseRepository.save(aCrs);
	}

	public void saveClass(ClassStream aClsStrm) {
		classStreamRepository.save(aClsStrm);
	}

	public List<Course> findAllCourse() {
		return courseRepository.findAll();
	}

	public List<ClassStream> findAllClassStream() {
		return classStreamRepository.findAll();
	}

	// Get user name to display individual user data
	public Object findOneWithName(String name) {
		User user = userRepository.findByUserName(name);
		return findOne(user.getUserId());
	}

	//***new
	// Get user name to display individual user data
	/*public Object findOneWithAssignmentName(String assigName) {
		Assignment assignment = assignmentRepository.findByAssignmentName(assigName);
		return findOne(assignment.getAssignmentId());
	}*/

	@Transactional
	public void saveCriteria(Criteria criteria) {
		criteriaRepository.save(criteria);
	}
	
	@Transactional
	public void removeCriteria(Criteria criteria) {
		criteriaRepository.delete(criteria);

	}

	// Method of list all users in the system
	/*
	 * public List<AssignmentTest> findAllCriteria(){ return
	 * assignmentTestRepository.findAll(); }
	 */

	@Transactional
	public void saveAssignment(Assignment assignment) {
		assignmentRepository.save(assignment);

	}

	@Transactional
	public Assignment findAssignmentById(int assignmentId) {
		// TODO Auto-generated method stub
		return assignmentRepository.findOne(assignmentId);

	}
	/*
	 * public List<Criteria> getAllCriteria() { Session session =
	 * sessionFactory.getCurrentSession(); List criterias = session.createQuery(
	 * "select distinct c from Assignment as s left join fetch c.criteria"
	 * ).list();
	 * 
	 * return criterias; }
	 */

	//Delete selected user by admin
	public void deleteUsers(int id) {
		userRepository.delete(id);
		
	}
/*
	public Set<Criteria> findByAssignmentId(int assignmentId) {
		return criteriaRepository.findCriteriaById(assignmentId);
	}
*/
}
