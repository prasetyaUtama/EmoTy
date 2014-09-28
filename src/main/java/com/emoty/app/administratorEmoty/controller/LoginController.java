package com.emoty.app.administratorEmoty.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller
@RequestMapping("/login")
public class LoginController extends SimpleFormController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView printWelcome(Locale locale, Model model) {

		
		return new ModelAndView("login");

	}



}
