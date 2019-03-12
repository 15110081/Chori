package com.chori.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoSendMailController {
//	@Autowired
	private JavaMailSender mailSender;
	
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// Using Gmail SMTP configuration.
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("bleachclone69@gmail.com");
		mailSender.setPassword("huancuibap");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@RequestMapping("/sendmail")
	public ModelAndView SendMailForm() {
		ModelAndView model = new ModelAndView(
				"configuration/sendmaildemo/demosendmail");
		return model;
	}

	@RequestMapping(value = "sendmail", method = RequestMethod.POST)
	public String sendEmail(HttpServletRequest request,
			final @RequestParam CommonsMultipartFile attachFile123) {

		// reads form input
		final String emailTo = request.getParameter("mailTo");
		final String subject = request.getParameter("subject");
		final String message = request.getParameter("message");

		// for logging
		System.out.println("emailTo: " + emailTo);
		System.out.println("subject: " + subject);
		System.out.println("message: " + message);
		System.out.println("attachFile: " + attachFile123.getOriginalFilename());

		mailSender = getMailSender();
		
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(
						mimeMessage, true, "UTF-8");
				messageHelper.setTo(emailTo);
				messageHelper.setSubject(subject);
				messageHelper.setText(message);

				// determines if there is an upload file, attach it to the
				// e-mail
				String attachName = attachFile123.getOriginalFilename();
				if (!attachName.equals("")) {

					messageHelper.addAttachment(attachName,
							new InputStreamSource() {
								public InputStream getInputStream()
										throws IOException {
									return attachFile123.getInputStream();
								}
							});
				}

			}
		});

		return "configuration/sendmaildemo/successsendmail";
	}
}
