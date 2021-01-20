package com.example.demo.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CertificateOfDeposit {
	private float amount;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
