package com.rabobank.parser;

public class ParserNotSupportedException extends Exception {
	
	private String message;

	public ParserNotSupportedException(String message) {
		this.message = message;
	}
	
}
