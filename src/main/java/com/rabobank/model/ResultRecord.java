package com.rabobank.model;

/**
 * Created by Navin 
 * Report POJO
 */

public class ResultRecord {
	
	int transactionRefNum;
	String transactionDescription;
	String raboComments;
	
	public int getTransactionRefNum() {
		return transactionRefNum;
	}

	public void setTransactionRefNum(int transactionRefNum) {
		this.transactionRefNum = transactionRefNum;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getRaboComments() {
		return raboComments;
	}

	public void setRaboComments(String raboComments) {
		this.raboComments = raboComments;
	}

}
