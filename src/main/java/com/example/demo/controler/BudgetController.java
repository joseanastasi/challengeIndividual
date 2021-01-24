package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.interfaceService.IUserService;
import com.example.demo.modelo.Budget;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;


@Controller
@RequestMapping
public class BudgetController {
	
	@Autowired
	private IBudgetService budgetService;

	@Autowired
	IUserService iUserService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/list")
	public String list(Model model) throws Exception {
		List<Budget>budget = budgetService.lastTen();
		Float s = budgetService.sum();
				
		model.addAttribute("budgets", budget);
		model.addAttribute("suma", s);
		return "index";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("budget", new Budget());		
		return "form";
	}
	 	
	
	@PostMapping("/save")
	public String save(User user, Budget b, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			return "form";
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		User myUser = userRepository.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		Long userId=myUser.getId();
		b.setUserId(userId);
		
		budgetService.save(b);
		return "redirect:/list";
	}
	
	
	@GetMapping("/delete/{noTxn}")
	public String delete(@PathVariable int noTxn) {
		budgetService.delete(noTxn);
		return "redirect:/list";
	}	
}