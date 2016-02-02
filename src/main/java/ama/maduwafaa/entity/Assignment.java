package ama.maduwafaa.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity
public class Assignment {
	
	@Id
	@GeneratedValue
	private Integer assignmentId;
	
	private String assignmentName;
	
	
	@ManyToOne
	@JoinColumn(name = "classId")
	private ClassStream lClass;	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "assignment")
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "assignment")
    @Fetch(FetchMode.SELECT)
	private Set<Criteria> criterias;
	
	
	public ClassStream getlClass() {
		return lClass;
	}

	public void setlClass(ClassStream lClass) {
		this.lClass = lClass;
	}

	
	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	
	public void setCriteriaInternal(Set<Criteria> criterias) {
		this.criterias = criterias;
    }

    public Set<Criteria> getCriteriasInternal() {
        if (this.criterias == null) {
            this.criterias = new HashSet<Criteria>();
        }
        return this.criterias;
    }
	public List<Criteria> getCriteria() {
        List<Criteria> sortedCriterias = new ArrayList<Criteria>(getCriteriasInternal());
        return Collections.unmodifiableList(sortedCriterias);
	}
	
	public void addCriteria(Criteria criteria) {
        criteria.setAssignment(this);
    }

	

}
