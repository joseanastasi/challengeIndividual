package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceService.IBudgetService;
import com.example.demo.interfaceService.ICertificateOfDepositService;
import com.example.demo.modelo.Budget;
import com.example.demo.modelo.CertificateOfDeposit;
import com.example.demo.repository.RepositoryBudget;



@Service
public class CertificateOfDepositService implements ICertificateOfDepositService{
	
	@Autowired
	private RepositoryBudget repositoryBudget;
	

	@Override
	public int save(CertificateOfDeposit c) {
		int res = 0;
		Budget budget = new Budget();
		budget.setType("certificateOfDeposit");
		budget.setConcept("Plazo fijo");
		budget.setAmount(c.getAmount()*-1);
		budget.setDate(new Date());
	
		budget = repositoryBudget.save(budget);
		if (budget != null) {
			res=1;
		} 		
		return res;
	}

	private float calcInterest(Budget certificate, Date today) {		
		long diff = today.getTime() - certificate.getDate().getTime();
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		return (float) ((days * 0.01));
	}

	@Override
	public void cashCertificate(int noTxn, Date today) {
		Budget certificate = repositoryBudget.findById(noTxn).get();
		
		Budget cash = new Budget();
		cash.setType("Ingreso");
		cash.setConcept("Ingreso por Plazo fijo");
		cash.setAmount(calcInterest(certificate, today));
		cash.setDate(new Date());
	
		repositoryBudget.save(cash);
		repositoryBudget.delete(certificate);
	}
}














