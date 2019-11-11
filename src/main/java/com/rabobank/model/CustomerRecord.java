package com.rabobank.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Navin 
 * Record POJO
 */

@XmlRootElement(name = "record")
public class CustomerRecord {

	private int transactionRefNum;
	private String accountNumber;
	private BigDecimal  startBalance;
	private BigDecimal  mutation;
	private String description;
	private BigDecimal  endBalance;
	
	public int getTransactionRefNum() {
		return transactionRefNum;
	}

	@XmlAttribute(name = "reference")
	public void setTransactionRefNum(int reference) {
		this.transactionRefNum = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}