package ama.maduwafaa.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ama.maduwafaa.entity.Assignment;
import ama.maduwafaa.entity.ClassStream;
import ama.maduwafaa.entity.Course;
import ama.maduwafaa.entity.Criteria;
import ama.maduwafaa.entity.FileUpload;
import ama.maduwafaa.entity.Student;
import ama.maduwafaa.entity.User;
import ama.maduwafaa.service.UserService;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Controller
public class UserController {

	// Arraylist to store from CSV file
	ArrayList<Student> stdList = new ArrayList<Student>();
	ArrayList<Course> courseList = new ArrayList<Course>();
	ArrayList<ClassStream> classStreamList = new ArrayList<ClassStream>();
	ArrayList<Criteria> criteriaList = new ArrayList<Criteria>();
	
	//ArrayList<Student> savedStudent = new ArrayList<Student>();
	ArrayList<Course> savedCourse = new ArrayList<Course>();
	ArrayList<ClassStream> savedClsStrm = new ArrayList<ClassStream>();
       
	@Autowired
	private UserService userService;
	
	
	/**
	 * Method for Create object of type user
	 * @return user object
	 */
	@ModelAttribute("user")
	public User construct(){		
		return new User();
		
	}
	
	@ModelAttribute("users")
	public List<User> users(){
		return userService.findAll();
	}

	/**
	 * @return Course object
	 */
	@ModelAttribute("course")
	public Course course(){
		return new Course();
	}
	
	/**
	 * @return All Course in database
	 */
	@ModelAttribute("courses")
    public List<Course> courses() {
        return userService.findAllCourse();
    }
	
	/**
	 * 
	 * @param model
	 * @return admin/users
	 */
	@RequestMapping("/admin/users")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "admin/users";
	}
	
	@RequestMapping("/users/{userId}")//This is dynamic part of the url
	public String detail(Model model, @PathVariable int userId){ //insert the value to the path variable
		model.addAttribute("user", userService.findOne(userId));
		return "admin/userDetail";
	}
	
	/**
	 * Method for Registration
	 * @return admin/user-register
	 */
	@RequestMapping("/user-register")
	public String showRegister(){
		return "user-register";
	}


	/**
	 * Method for the Registration
	 * @param user
	 * @return redirect:/admin/user-register?success=true
	 */
	@RequestMapping(value="/user-register", method=RequestMethod.POST)
	//Can get the user object by using ModelAttribute
	//@Valid annotation for hibernate validation to trigger the validation and result will be saved the object of type binding result 
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(user);
		return "redirect:/user-register?success=true";
	}
	
	/**
	 * Display admin dashboard
	 * @param model
	 * @param principal
	 * @return admin/dashboard
	 */
	@RequestMapping(value="/admin/dashboard", method=RequestMethod.GET)
	public String adminHome(Model model, Principal principal) { //Object of type principal, this object is in user session and contain name of the user
		String name = principal.getName(); //This is the name of the user
		model.addAttribute("user", userService.findOneWithName(name));
		return "admin/dashboard";
	}
	
	/**
	 * Display lecturer dashboard
	 * @param model
	 * @param principal
	 * @return lecturer/dashboard
	 */
	@RequestMapping(value="/lecturer/dashboard", method=RequestMethod.GET)
	public String lecturerHome(Model model, Principal principal) { //Object of type principal, this object is in user session and contain name of the user
		String name = principal.getName(); //This is the name of the user
		model.addAttribute("user", userService.findOneWithName(name));
		return "lecturer/dashboard";
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	//Can get the user object by using ModelAttribute
	public String home(Model model, Principal principal) { //Object of type principal, this object is in user session and contain name of the user
		String name = principal.getName(); //This is the name of the user
		model.addAttribute("user", userService.findOneWithName(name));
		return "dashboard";
	}
	
	/**
	 * Method For Manage Student List
	 * @return admin/manageStudentList
	 */
	@RequestMapping(value = "/admin/manageStudentList" , method = RequestMethod.GET)
	public String manageStList(){
		return "admin/manageStudentList";
	}
	

	/**
	 * Individual user can see their own account details
	 * @return user-detail
	 * 
	 */
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal) { //Object of type principal, this object is in user session and contain name of the user
		String name = principal.getName(); //This is the name of the user		
		model.addAttribute("user", userService.findOneWithName(name));		
		return "admin/userDetail";
	}
	
	
		


	/**
	 * Method to read content of CSV file and store into database
	 * @param file
	 * @param model
	 * @return admin/uploadSuccess
	 * @throws IllegalStateException
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/admin/uploadFile", method = RequestMethod.POST)
	public String save(@RequestParam("file") MultipartFile file, ModelMap model) 
			throws IllegalStateException, IOException{
		
		// Uploaded file name and directory details
		String saveDirectory = "c:/files/";		
		String directory = "";
		String fileName = "";
		
		// Read content of uploaded file
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		if(file != null && file.getSize() > 0){
				fileName = file.getOriginalFilename();
				if(!"".equalsIgnoreCase(fileName)){
					// Handle file content - multipartFile.getInputStream()
					directory = saveDirectory + fileName;
					file.transferTo(new File(saveDirectory + fileName));
			}
				FileUpload aFile = new FileUpload();
		        aFile.setName(fileName);
		        aFile.setDirectory(directory);
		        
		        model.addAttribute("file",aFile);
		        
		        br = new BufferedReader(new FileReader(directory));
		        
		     // Arraylist of Student to store from CSV file
		        ArrayList<Student> stdList = new ArrayList<Student>();
		        
		        // Read CSV 
		        while((line = br.readLine())!= null){
		        	String[] student = line.split(cvsSplitBy);
		        	
		        	// Create new student
		        	Student aStd = new Student();
		        	
		        	aStd.setStdId(student[0]);
		        	aStd.setFName(student[1]);
		        	aStd.setLName(student[2]);
		        	aStd.setStreetAddress(student[3]);
		        	aStd.setSuburb(student[4]);
		        	aStd.setCity(student[5]);
		        	aStd.setEmail(student[6]);
		        	
		        	Course aCrs = new Course();
		        	
		        	aCrs.setCourseName(student[7]);
		        			        	
		        	userService.saveCourse(aCrs);
		        	
		        	ClassStream aClsStrm = new ClassStream();
		        	
		        	Integer classNum = Integer.parseInt(student[8]);
		        	
		        	aClsStrm.setClassNo(classNum);
		        	
		        	// Add class stream to course
		        	aCrs.addClassStream(aClsStrm);
		        	
		        	userService.saveClass(aClsStrm);

		        	// Add student to class stream ?? 
		        	aClsStrm.addStudent(aStd);
		        	
		        	//aStd.setCourseNum(student[7]);
		        	//aStd.setClassNum(student[8]);
		        	//aStd.setClassNum(aClsStrm);
		        	
		        	userService.saveStudent(aStd);
		        	
		        	stdList.add(aStd);
		        	courseList.add(aCrs);
		        	classStreamList.add(aClsStrm);
		        	
		            model.addAttribute("student",aStd);
		        }
		        model.addAttribute("stdList",stdList);

		        return "admin/uploadSuccess";
		}
		else
		{
			return "redirect:/admin/manageStudentList?success=true";
		}
        
        
	}

	/**
	 *  Method to save student according to the particular class stream & 
	 *  class stream to a particular course
	 * @param model
	 * @return "admin/students"
	 * (not working)
	 */
	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String save(Model model) 
	{
		
		/*HashSet<Student> savedStudent = new HashSet<Student>();
		
		for(Student std : stdList){
			//String stdId = std.getStdId();
			//savedStudent.add(std);
			if(!savedStudent.isEmpty())
			{
				if(!savedStudent.contains(std))
				{
					savedStudent.add(std);
					System.out.println(std.getStdId());
					//userService.saveStudent(std);
				}
			}
			else
			{
				//for(Student savStd : savedStudent ){
					//String savedStdId = savStd.getStdId();					
					//if(!(stdId.equals(savedStdId)))
					//if(!savedStudent.contains(stdId))
					//{
						savedStudent.add(std);
						System.out.println(std.getStdId());
			        	//userService.saveStudent(std);
					//} 
				//}
			}
		}/*
		for(Course crs : courseList){
			String crsName = crs.getCourseName();
			savedCourse.add(crs);
				for(Course savCrs : savedCourse ){
					String savedCrsName = savCrs.getCourseName();					
					if(!(crsName.equals(savedCrsName)))
					{
						savedCourse.add(crs);
			        	userService.saveCourse(crs);
					} 
				}
		}
		for(ClassStream cls : classStreamList){
			Integer clsNum = cls.getClassNo();
			savedClsStrm.add(cls);
				for(ClassStream savCls : savedClsStrm ){
					Integer savedClsNo = savCls.getClassNo();					
					if(!(clsNum.equals(savedClsNo)))
					{
						savedClsStrm.add(cls);
			        	userService.saveClass(cls);
					} 
				}
		}
*/
        model.addAttribute("stdList",stdList);
	//	model.addAttribute("savedStudent",savedStudent);
	//	model.addAttribute("savedCourse",savedCourse);
	//	model.addAttribute("savedClsStrm",savedClsStrm);
	   
		return "admin/students";
	}
	  
    /**
     * Method to create an assignment object
     * @return assignment object
     */
    @ModelAttribute("assignment")
    public Assignment assignment(){
    	return new Assignment();
    }
 	
	/**
	 * Set lecturer to a course
	 * @param model
	 * @return admin/manageLecturer
	 * 
	 */
	@RequestMapping(value="/admin/manageLecturer", method=RequestMethod.GET)
	public String manageLecturer(Model model){
		model.addAttribute("users", userService.findAll());
		model.addAttribute("courses", userService.findAllCourse());
		return "admin/manageLecturer";
	}
	
	/**
	 * Set lecturer to a course
	 * @param model
	 * @return admin/manageLecturer
	 * 
	 */
	@RequestMapping(value="/admin/manageLecturer", params={"setLecturer"})
	public String saveLecturerCourse(Model model){
		return "admin/manageLecturer";
	}
	
	/**
	 * Remove registered user by admin
	 * @param model
	 * @return admin/users
	 * 
	 */
	@RequestMapping("users/remove/{id}")
	public String removeUser(@PathVariable int id){
		userService.deleteUsers(id);
		return "redirect:/admin/users";
	}
}
