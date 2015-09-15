package com.catalystdevworks.slujan.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		LOGGER.debug("Received request root address");
		return "index.html";
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
		 	LOGGER.debug("Received request at /login");
	        return new ModelAndView("login", "error", error);
	    }

}
