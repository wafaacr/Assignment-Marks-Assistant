package ama.maduwafaa.controller;

import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ama.maduwafaa.service.EmailService;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	 /* 
     * Send HTML mail
     */
    @RequestMapping(value = "/sendMailSimple/{assignmentId}", method = RequestMethod.POST)
    public String sendSimpleMail(
            @RequestParam("recipientName") final String recipientName,
            @RequestParam("recipientEmail") final String recipientEmail,
            @RequestParam("recipientLecturer") final String recipientLecturer,
            @RequestParam("emailBody") final String emailBody,
            final Locale locale) 
            throws MessagingException {

        this.emailService.sendSimpleMail(recipientName, recipientEmail,recipientLecturer,emailBody, locale);
        
        return "redirect:/lecturer/markingsheet/{assignmentId}?success=true";
        
    }
    

}
