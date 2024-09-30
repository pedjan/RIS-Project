package com.example.demo.exceptions;

public class NeuspeloPisanjeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NeuspeloPisanjeException(String poruka) {
		super(poruka);
	}

}
