package ama.maduwafaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

}
