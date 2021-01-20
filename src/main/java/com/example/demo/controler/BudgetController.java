package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.modelo.Budget;


@Controller
@RequestMapping
public class BudgetController {
	
	@Autowired
	private IBudgetService budgetService;
	
	

	@GetMapping("/list")
	public String list(Model model) {
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
	public String save(@Valid Budget b, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		}
		
		budgetService.save(b);
		return "redirect:/list";
	}
	
	@GetMapping("/edit/{noTxn}")
	public String edit(@PathVariable int noTxn, Model model) {
		boolean isActive=false;
		if(noTxn>0) {
			isActive = true;
		}
		Optional<Budget> budget= budgetService.listNoTxn(noTxn);
		model.addAttribute("isActive", isActive);
		model.addAttribute("budget",budget);
		return "form";
	}
	
	@GetMapping("/delete/{noTxn}")
	public String delete(@PathVariable int noTxn, Model model) {
		budgetService.delete(noTxn);
		return "redirect:/list";
	}	
}