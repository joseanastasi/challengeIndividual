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
import com.example.demo.modelo.Budget;
import com.example.demo.modelo.CertificateOfDeposit;
import com.example.demo.repository.RepositoryBudget;
import com.example.demo.repository.UserRepository;



@Service
public class CertificateOfDepositService implements ICertificateOfDepositService{
	
	@Autowired
	private RepositoryBudget repositoryBudget;
	

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public int save(CertificateOfDeposit c) {
		int res = 0;
		Budget budget = new Budget();
		budget.setType("certificateOfDeposit");
		budget.setAmount(c.getAmount()*-1);
		budget.setDate(new Date());
		budget.setUserId(c.getUserId());
		budget = repositoryBudget.save(budget);
		if (budget != null) {
			res=1;
		} 		
		return res;
	}

	private float calcInterest(Budget certificate, Date today) {
		
		long diff = today.getTime() - certificate.getDate().getTime();
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		float certificateAmount = certificate.getAmount();
		
		return certificateAmount *((days * -0.01F));
	}

	@Override
	public void cashCertificate(int noTxn, Date today) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		User myUser = userRepository.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		
		Long userId=myUser.getId();
		
		Budget certificate = repositoryBudget.findById(noTxn).get();
		Budget cash = new Budget();
		cash.setType("Deposito");
		cash.setAmount(calcInterest(certificate, today));
		cash.setDate(new Date());
		cash.setUserId(userId);
	
		
				
		repositoryBudget.save(cash);
		repositoryBudget.delete(certificate);
	}
}














