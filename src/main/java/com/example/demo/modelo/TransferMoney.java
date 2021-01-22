package com.example.demo.modelo;


public class TransferMoney {
	private float amount;
	private Long userId;
private Long idUserToTransfer;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIdUserToTransfer() {
		return idUserToTransfer;
	}

	public void setIdUserToTransfer(Long idUserToTransfer) {
		this.idUserToTransfer = idUserToTransfer;
	}
	
	
	}
