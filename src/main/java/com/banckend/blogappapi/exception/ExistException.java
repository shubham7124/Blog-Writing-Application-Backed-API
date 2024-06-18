package com.banckend.blogappapi.exception;

public class ExistException  extends RuntimeException {

	private String ms;
	private boolean success;
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ExistException(String ms, boolean success) {
		super();
		this.ms = ms;
		this.success = success;
	}
	
}
