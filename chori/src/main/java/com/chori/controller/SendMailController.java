package com.chori.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chori.model.PiModel;

@Controller
@RequestMapping(value = "/")
public class SendMailController {
	
	private static final Log log = LogFactory.getLog(SizeController.class);

	/**
	 * Return send mail page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendemail", method = RequestMethod.GET)
	public ModelAndView gotoPageSendEmail(ModelMap model, HttpServletResponse response) {
		log.info(String.format("listPi with param 'response' in class: %s", getClass()));
		try {
			log.debug("return PageSendEmail view for request");
			response.setContentType("text/html");
			
			return new ModelAndView("operation/sendmail/sendmail");
		} catch (Exception e) {
			log.error(String.format("listPi with param 'response' in class %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}

	}
}
