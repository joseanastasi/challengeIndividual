package com.example.demo.controler;

import java.util.Date;
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
import com.example.demo.interfaceService.ICertificateOfDepositService;
import com.example.demo.modelo.Budget;
import com.example.demo.modelo.CertificateOfDeposit;


@Controller
@RequestMapping
public class CertificateOfDepositController {
	
	@Autowired
	private ICertificateOfDepositService certificateService;
	
	@Autowired
	private IBudgetService budgetService;
	
	@GetMapping("/certificate")
	public String add(Model model) {
		Float s = budgetService.sum();
		model.addAttribute("availableMoney", s);
		model.addAttribute("certificate", new CertificateOfDeposit());
		return "certificateOfDeposit";
	}
	
		
	@PostMapping("/certificateOfDeposit/save")
	public String save(@Valid CertificateOfDeposit c, BindingResult bindingResult , Model model) {
		if(bindingResult.hasErrors()) {
			return "certificateOfDeposit";
		}
		certificateService.save(c);
		return "redirect:/list";
	}

	@GetMapping("/cashCertificate/{noTxn}")
	public String edit(@PathVariable int noTxn, Model model) {
		certificateService.cashCertificate(noTxn, new Date());
		return "redirect:/list";
	}		
}












