package ama.maduwafaa.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ama.maduwafaa.entity.Assignment;
import ama.maduwafaa.entity.Course;
import ama.maduwafaa.entity.Criteria;
import ama.maduwafaa.entity.Student;
import ama.maduwafaa.service.UserService;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Controller
@SessionAttributes(types = Assignment.class)
public class AssignmentController {
	
	ArrayList<Criteria> criteriaList = new ArrayList<Criteria>();

	Set<Criteria> critList;
	
	String criteriaFrTxtBx = "";
	
	@Autowired 
	private UserService userService;
	
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
	 * @return studentId
	 */
	@ModelAttribute("stdData")
	public Student constructStd(){		
		return new Student();
		
	}
	
	/**
	 * @return Criteria object
	 */
	@ModelAttribute("criteriaData")
	public Criteria constructCriteria(){		
		return new Criteria();
		
	}

	/**
	 * @return list of student
	 */
	@ModelAttribute("students")
	public List<Student> students() {
	    return userService.findAllStudent();
	}
	
	/**
	 * Set Assignment
	 * 
	 * @param model
	 * @return addAssignment page
	 */
	@RequestMapping(value = "/lecturer/addAssignment", method = RequestMethod.GET)
	public String setAssignment(Model model)
	{
		Assignment assignment = new Assignment();
		model.addAttribute("assignment",assignment);
		return "lecturer/addAssignment";
	}
	
	/**
	 * Save assignment and display the list of assignment
	 * 
	 * @param assignment
	 * @param course
	 * @param redirectAttributes
	 * @return assignments page
	 */
	@RequestMapping(value = "/lecturer/saveAssignment", method = RequestMethod.POST)
	public String saveAssignment(@ModelAttribute("assignment") Assignment assignment, 
								 @ModelAttribute("course") Course course,
								 RedirectAttributes redirectAttributes)
	{
		userService.saveAssignment(assignment);
		redirectAttributes.addFlashAttribute("assignment", assignment);
		redirectAttributes.addFlashAttribute("course",course);
		return "redirect:/lecturer/assignments";
	}
	
	/**
	 * Display the list of assignment
	 * 
	 * @param model
	 * @return assignments page
	 */
	@RequestMapping(value = "/lecturer/assignments", method = RequestMethod.GET)
	public String showAssignments()
	{
		return "lecturer/assignments";
	}	
	
	/**
	 * Display criteria form for specified assignment
	 * 
	 * @param assignmentId
	 * @param model
	 * @return setCriteria page
	 */
	@RequestMapping(value = "/lecturer/setCriteria/{assignmentId}" , method = RequestMethod.GET)
	 public String showCriteriaForm(@PathVariable int assignmentId, Model model)
	{
	  Assignment assignment = this.userService.findAssignmentById(assignmentId);
      Criteria criteria = new Criteria();
      assignment.addCriteria(criteria);
      model.addAttribute("criteria", criteria);
      model.addAttribute("criteriaList",criteriaList);
	  return "lecturer/setCriteria";
	}
	
	/**
	 * Add new criteria and display the list of updated criteria in the page
	 * 
	 * @param criteria
	 * @param model
	 * @return setCriteria page for specified assignment
	 */
	@RequestMapping(value = "/lecturer/setCriteria/{assignmentId}", params={"addRow"})
	public String addCriteria(@Valid Criteria criteria, Model model, BindingResult result)
	{
			criteriaList.add(criteria);
			model.addAttribute("criteriaList",criteriaList);
			userService.saveCriteria(criteria);

			return "redirect:/lecturer/setCriteria/{assignmentId}";			
		
	}
	
	/**
	 * Remove selected criteria and display the updated list of criteria
	 * 
	 * @param criteria
	 * @param model
	 * @param bindingResult
	 * @param req
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/lecturer/setCriteria/{assignmentId}", params={"removeCriteria"})
	public String removeCriteria(@ModelAttribute("criteria") Criteria criteria,
							  Model model,
							  final BindingResult bindingResult, final HttpServletRequest req,
							  RedirectAttributes redirectAttributes)
	
	{
		final Integer rowId = Integer.valueOf(req.getParameter("removeCriteria"));
		int delRowId = 0;
		System.out.println("Size of list : " + criteriaList.size());
			for(Criteria c : criteriaList)
			{
				Integer id = c.getCriteriaId();
				System.out.println(id);
				if(id.equals(rowId))
				{
					System.out.println("There is a match found");
					String desc = c.getDescription();
					System.out.println("Index : " + delRowId + "Content : " + id + " " + desc);

					criteriaList.remove(c);
					
					userService.removeCriteria(c);
					break;
				}
			}
		
		model.addAttribute("criteriaList",criteriaList);
		//return "lecturer/setCriteria";
		return "redirect:/lecturer/setCriteria/{assignmentId}";
	}
	
	/**
	 * Save list of criteria and redirect to specified assignment marking sheet
	 * 
	 * @param criteria
	 * @param model
	 * @param assignmentId
	 * @param principal
	 * @return markingSheet for the specified assignment
	 */
	@RequestMapping(value = "/lecturer/saveCriteria/{assignmentId}", method = RequestMethod.POST)
	public String saveCriteria(@ModelAttribute("criteria") Criteria criteria, Model model,
							   @PathVariable int assignmentId,Principal principal)
	{	
		Assignment assignment = this.userService.findAssignmentById(assignmentId);
		Set<Criteria> criteriaSet = new HashSet<Criteria>(criteriaList);
		System.out.println("Saved criterias");
		for(Criteria c : criteriaSet)
		{
			System.out.println(c.getDescription());
			criteriaFrTxtBx = criteriaFrTxtBx.concat(c.getDescription());
		}
		assignment.setCriteriaInternal(criteriaSet);
		criteriaList.clear();
		
		critList  = assignment.getCriteriasInternal();
        
		System.out.println(criteriaFrTxtBx);
        
		String name = principal.getName();
	       model.addAttribute("user", userService.findOneWithName(name));

	       return "redirect:/lecturer/markingsheet/{assignmentId}";
	}
	/**
	 * Method For marking sheet
	 * @return lecturer/markingsheet
	 */
	@RequestMapping(value = "/lecturer/markingsheet/{assignmentId}" , method = RequestMethod.POST)
	public String markingSheet(Model model, @PathVariable int assignmentId, Principal principal){
		Assignment assignment = this.userService.findAssignmentById(assignmentId);
        model.addAttribute("assignment",assignment);
        
		String name = principal.getName();
	    model.addAttribute("user", userService.findOneWithName(name));

        model.addAttribute("critList",critList);
        return "lecturer/markingsheet";
	}
	
	/**
	 * Display marking sheet for a particular course
	 * 
	 * @param model
	 * @param assignmentId
	 * @param principal
	 * @return lecturer/markingsheet
	 */
	@RequestMapping(value = "/lecturer/markingsheet/{assignmentId}" , method = RequestMethod.GET)
	public String viewMarkingSheet(Model model, @PathVariable int assignmentId, Principal principal){
		Assignment assignment = this.userService.findAssignmentById(assignmentId);

		model.addAttribute("assignment",assignment); 		
        
        String name = principal.getName();
        model.addAttribute("user", userService.findOneWithName(name));

		model.addAttribute("criteriaFrTxtBx",criteriaFrTxtBx);
        model.addAttribute("critList",critList);

		return "lecturer/markingsheet";
	}
}
