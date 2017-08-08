package com.hcss.exception;

public class EmployeeException extends Exception {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage = "";

	public EmployeeException() {
		super();
	}

	public EmployeeException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}

	public String toString() {
		return "..EmployerException.." + exceptionMessage;
	}
}
