package com.caveofprogramming.spring.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;*/
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caveofprogramming.spring.web.dao.FormValidationGroup;
import com.caveofprogramming.spring.web.dao.Message;
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
	
	/*@Autowired
	private MailSender mailSender;*/

	@RequestMapping("/login")
	public String showLogin(Model model) {
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied(Model model) {

		return "denied";
	}

	@RequestMapping("/messages")
	public String showMessages() {

		return "messages";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		/* throw new AccessDeniedException("Hello"); */

		// List<User> users = usersService.getAllUsers();

		List<Offer> offers = offersService.getCurrentAdmin();

		// model.addAttribute("users", users);

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
	public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {

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

	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {

		List<Message> messages = null;

		if (principal == null) {
			messages = new ArrayList<Message>();
		} else {
			String username = principal.getName();
			messages = usersService.getMessages(username);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("messages", messages);
		data.put("number", messages.size());

		return data;
	}

	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {

		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");
		
		/*SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("umangborad123@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re: " + name + ", your message");
		mail.setText(text);
		
		try {
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't send message!");
		}*/
		
		Map<String, Object> rval = new HashMap<>();
		rval.put("success", true);
		rval.put("target", target);
		
		return rval;
	}
}
