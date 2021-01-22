package com.example.demo.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.interfaceService.IUserService;
import com.example.demo.modelo.Budget;
import com.example.demo.modelo.TransferMoney;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransferMoneyService;


@Controller
@RequestMapping
public class TransferMoneyController {
	
	
	@Autowired
	private IBudgetService budgetService;
	
	@Autowired
	IUserService iUserService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	TransferMoneyService transferMoneyService;
	
		
	
	
	@GetMapping("/transferMoneyToUser/{id}")
	public String transferForm(Model model, @PathVariable(name ="id")Long id) throws Exception {
		User userWhoReceiveMoney = iUserService.getUserById(id);
		Long userIdToTransfer =  userWhoReceiveMoney.getId();
		
		TransferMoney transferMoney = new TransferMoney();
		transferMoney.setIdUserToTransfer(userIdToTransfer);
		Float s = budgetService.sum();
		
		model.addAttribute("availableMoney", s);
		model.addAttribute("userWhoReceiveMoney", userWhoReceiveMoney);
		model.addAttribute("IdUserWhoReceiveMoney", userIdToTransfer);
		model.addAttribute("transferMoney", transferMoney);
		return "transferMoney";
	}
		
	@PostMapping("/transferMoneySave")
	public String transferMoneyToTheUser(@Valid @ModelAttribute("transferMoney") TransferMoney transferMoney,BindingResult bindingResult, Model model) throws Exception {
		
		System.out.println(bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("availableMoney", budgetService.sum());
			model.addAttribute("transferMoney", new TransferMoney());
			return "transferMoney";
		}
		model.addAttribute("transferMoney", new TransferMoney());
		transferMoneyService.transferMoneyToUser(transferMoney,transferMoney.getIdUserToTransfer());
		return "redirect:/list";
	}
	
	
}












