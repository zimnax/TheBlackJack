package com.andrew.safronov.sintez.theblackjack.exception;

public class ExceptionInfo {

	public final String url;

	public final String ex;

	public ExceptionInfo(String url, Exception ex) {
		this.url = url;
		this.ex = ex.getLocalizedMessage();
	}
}