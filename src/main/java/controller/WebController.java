package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

@Controller
public class WebController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		LOGGER.debug("Received request root address");
		return "index.html";
	}

}
