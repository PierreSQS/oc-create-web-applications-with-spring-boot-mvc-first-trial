package com.openclassrooms.watchlist.controllers;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {
	private final Logger log = LoggerFactory.getLogger(CustomErrorController.class);
	

	@Override
	public String getErrorPath() {
		return "error";
	}
	
	@GetMapping("/error")
	public ModelAndView handleError(HttpServletResponse resp) {
		
		int statusCode = resp.getStatus();
		log.error("Error with code {} Happened!", statusCode);
		
		return new ModelAndView("error");
	}
	
	

}
