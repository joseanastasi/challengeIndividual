package com.example.demo.interfaceService;

import java.util.Date;

import com.example.demo.modelo.CertificateOfDeposit;

public interface ICertificateOfDepositService {
	public int save(CertificateOfDeposit b);	
	public void cashCertificate(int noTxn, Date today) throws Exception;
}