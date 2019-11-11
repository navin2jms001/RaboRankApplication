package com.rabobank.model;

/**
 * Created by Navin 
 * Response object for this application
 */

public class RaboAppResponse {

	private int status;
	private String message;
	private Object data;
	
	public RaboAppResponse() {
		
	}
	
	public RaboAppResponse(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Error [responseMessage=" + message + ", responseCode=" + status + "]";
	}
}
