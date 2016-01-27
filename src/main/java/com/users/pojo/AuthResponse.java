package com.users.pojo;

public class AuthResponse {
	
	private boolean anyError;
	private String status;
	
	public boolean isAnyError() {
		return anyError;
	}
	public void setAnyError(boolean anyError) {
		this.anyError = anyError;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
