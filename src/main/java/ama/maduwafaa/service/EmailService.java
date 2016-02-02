package ama.maduwafaa.service;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
@Service
public class EmailService {
	
	@Autowired	
	private JavaMailSender mailSender;

	@Autowired
	
	private TemplateEngine templateEngine;
	
    /**
     * Send HTML mail (simple) 
     * @param recipientName
     * @param recipientEmail
     * @param recipientLecturer
     * @param emailBody
     * @param locale
     * @throws MessagingException
     */
    public void sendSimpleMail(
            final String recipientName, final String recipientEmail,
            String recipientLecturer, String emailBody, final Locale locale) 
            throws MessagingException {
        
        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("fromLecturer", recipientLecturer);
        ctx.setVariable("emailContent", emailBody);
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Assignment Result");
        message.setFrom("assignmentMarkAssistant@gmail.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-simple", ctx);
        message.setText(htmlContent, true /* isHtml */);
        
        // Send email
        this.mailSender.send(mimeMessage);

    }

}
