package ama.maduwafaa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity
public class ClassStream {
	
	@Id
	@GeneratedValue
	private Integer classId;
	
	private Integer classNo;
	
	@ManyToMany(mappedBy = "lClass")
	private List<Student> student;
	
	
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;
	
	@OneToMany(mappedBy = "lClass", cascade = CascadeType.REMOVE)
	private List<Assignment> assignments;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}	

	
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getClassNo() {
		return classNo;
	}

	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
	}

	public void addStudent(Student student) {
		student.setClassStream(this);
	}

}
