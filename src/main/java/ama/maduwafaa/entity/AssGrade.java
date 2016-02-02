package ama.maduwafaa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity 
public class AssGrade {
	
	@Id
	@GeneratedValue
	private Integer assGradeId;
	
	private Integer totalMarks;
	
	private String grade;
	
	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student students;	
	
	

	public Integer getAssGradeId() {
		return assGradeId;
	}

	public void setAssGradeId(Integer assGradeId) {
		this.assGradeId = assGradeId;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	

}
