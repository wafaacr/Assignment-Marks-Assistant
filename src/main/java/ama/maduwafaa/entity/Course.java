package ama.maduwafaa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity
public class Course {
	
	@Id
	@GeneratedValue
	private Integer courseId;
	
	private String courseName;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
	private List<ClassStream> classes;	


	public List<ClassStream> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassStream> classes) {
		this.classes = classes;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void addClassStream(ClassStream classStream) {
		classStream.setCourse(this);
	}
	

}
