package ama.maduwafaa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity
public class Student {
	
	@Id
	@GeneratedValue
	Integer id;

	String stdId;

	String fName;
	
	String lName;
	
	String email;
	
	String streetAddress;
	
	String suburb;
	
	String city;
	
//	String courseNum;
	
	@ManyToMany
	@JoinTable
	private List<ClassStream> lClass;	

	@OneToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
	private List<AssGrade> assGrades;
	

	String classNum;

	public List<ClassStream> getlClass() {
		return lClass;
	}

	public void setlClass(List<ClassStream> lClass) {
		this.lClass = lClass;
	}

	public List<AssGrade> getAssGrades() {
		return assGrades;
	}

	public void setAssGrades(List<AssGrade> assGrades) {
		this.assGrades = assGrades;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setCity(String city) {
		this.city = city;
	}

/*	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
*/
	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(ClassStream classNum) {
		//this.classNum = classNum;
		lClass.add(classNum);
	}

	public void setClassStream(ClassStream classStream) {
	}

}
