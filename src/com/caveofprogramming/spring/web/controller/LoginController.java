package com.caveofprogramming.spring.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.OffersService;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class LoginController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;

	@RequestMapping("/login")
	public String showLogin(Model model) {

		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied(Model model) {

		return "denied";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		/*throw new AccessDeniedException("Hello");*/

		//List<User> users = usersService.getAllUsers();
		
		List<Offer> offers = offersService.getCurrentAdmin();
		
		//model.addAttribute("users", users);
		
		model.addAttribute("offers", offers);

		return "admin";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";// You can redirect wherever you want,
										// but generally it's a good practice to
										// show login screen again. 
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			return "newaccount";
		}

		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		usersService.create(user);

		return "accountcreated";
	}
}
