package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.interfaceService.ICertificateOfDepositService;
import com.example.demo.interfaceService.ITransferMoneyService;
import com.example.demo.modelo.Budget;
import com.example.demo.modelo.CertificateOfDeposit;
import com.example.demo.modelo.TransferMoney;
import com.example.demo.repository.RepositoryBudget;
import com.example.demo.repository.UserRepository;



@Service
public class TransferMoneyService implements ITransferMoneyService{
	
	@Autowired
	private RepositoryBudget repositoryBudget;
	

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public int transferMoneyToUser(TransferMoney t, Long id) throws Exception {
		
		int res=0;
		//crear un budget que le da un ingreso al usuario que recibio el dinero o sea al del id
		Budget budgetIncome = new Budget();
		budgetIncome.setType("Ingreso por Transferencia");
		budgetIncome.setAmount(t.getAmount());
		budgetIncome.setDate(new Date());
		budgetIncome.setUserId(id);
		
		budgetIncome = repositoryBudget.save(budgetIncome);
		if (budgetIncome != null) {
			res=1;
		} 		
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		User myUser = userRepository.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		Long userId=myUser.getId();		
		
		res = 0;
		Budget budgetOutcome = new Budget();
		budgetOutcome.setType("Pagos por Transferencia");
		budgetOutcome.setAmount(t.getAmount()*-1);
		budgetOutcome.setDate(new Date());
		budgetOutcome.setUserId(userId);
		budgetOutcome = repositoryBudget.save(budgetOutcome);
		 
		if (budgetOutcome != null) {
			res=1;
		} 		
		return res;
	}

	
	
}
	





