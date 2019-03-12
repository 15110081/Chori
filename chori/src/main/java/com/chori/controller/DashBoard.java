package com.chori.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "/", "/Dashboard" })
public class DashBoard {

	// @Autowired
	// ServletContext context;

	private static final Logger logger = Logger.getLogger(DashBoard.class);
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/", "/index", "/dashboard" }, method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		// System.err.println(context.getRealPath("/"));
		return new ModelAndView("common/dashboard");
	}

	@RequestMapping(value = { "/dk" }, method = RequestMethod.GET)
	public String testHandleRequest() {

		return "common/login";
	}

}
