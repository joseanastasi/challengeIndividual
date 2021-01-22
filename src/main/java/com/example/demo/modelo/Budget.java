package com.example.demo.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table (name = "budget")
public class Budget implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noTxn;
	
	private Long userId;
	@NotNull
	private float amount;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private Date date;
	@NotNull
	private String type;
	
	public Budget() {
	}

	public Budget(float amount, Date date, String type) {
		super();
		this.amount = amount;
		this.date = date;
		this.type = type;
	}
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getNoTxn() {
		return noTxn;
	}

	public void setNoTxn(int noTxn) {
		this.noTxn = noTxn;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}