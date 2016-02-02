package ama.maduwafaa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity
public class Criteria {
	

	@Id
	@GeneratedValue
	private Integer criteriaId;
	
	@NotNull
	private String description;
	
	@NotNull
    @Min(1)
	private Integer possibleMarks;
	
	

	@ManyToOne
	@JoinColumn(name = "assignmentId")
	private Assignment assignment;


	public Integer getCriteriaId() {
		return criteriaId;
	}


	public void setCriteriaId(Integer criteriaId) {
		this.criteriaId = criteriaId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPossibleMarks() {
		return possibleMarks;
	}


	public void setPossibleMarks(Integer possibleMarks) {
		this.possibleMarks = possibleMarks;
	}

	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}
