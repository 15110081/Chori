package com.chori.configuration;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.InternalResourceView;

public class JstlView extends InternalResourceView {
	private static final Logger logger = Logger.getLogger(JstlView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Expose the model object as request attributes.
		exposeModelAsRequestAttributes(model, request);
		// Determine the path for the request dispatcher.
		String dispatcherPath = prepareForRendering(request, response);
		logger.debug("VIEW FILE: " + dispatcherPath);
		// set original view being asked for as a request parameter
		// request.setAttribute("partial",
		// dispatcherPath.substring(dispatcherPath.lastIndexOf("/") + 1));
		request.setAttribute("partial", dispatcherPath);
		// force everything to be template.jsp
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("/WEB-INF/views/common/template.jsp");
		requestDispatcher.forward(request, response);
		// requestDispatcher.include(request, response);

	}

}