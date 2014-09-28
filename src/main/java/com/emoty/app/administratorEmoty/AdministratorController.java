package com.emoty.app.administratorEmoty;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emoty.app.model.Administrator;

@Controller
public class AdministratorController {

	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/adminPage/default")
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Hello");
		model.addAttribute("message", "Spring Security Hello World");
		//model.addAttribute("administrator", new Administrator() );
		
		return new ModelAndView("admin");
	}
}
