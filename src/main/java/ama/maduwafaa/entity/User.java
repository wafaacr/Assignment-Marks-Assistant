package ama.maduwafaa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import ama.maduwafaa.annotation.UniqueUsername;


/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer userId;	
	
	@Size(min = 3, message = "First name must be at least 3 characters!")	
	private String fName;
	
	@Size(min = 3, message = "Last name must be at least 3 characters!")
	private String lName;
		
	@Size(min = 7, message = "Email must be at least 7 characters!")
	@Email
	private String email;
	
	@Size(min = 3, message = "User name must be at least 3 characters!")
	@Column(unique=true)
	@UniqueUsername(message = "This user name already exists!")
	private String userName;
	
	@Size(min = 4, message = "Password must be at least 4 characters!")
	private String password;
	
	private boolean enabled;
	
	/*@ManyToOne
	@JoinTable(name="roleId")
	private Role role;*/
	
	@ManyToMany
	@JoinTable
	private List<Role> roles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<ClassStream> classes;
	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public List<ClassStream> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassStream> classes) {
		this.classes = classes;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
