package com.users.pojo;

import java.util.List;

import com.users.mongo.User;

public class UsersResponse {
	private boolean anyError;
	private String status;
	private String errorMessage;
	private String errorCode;
	private int count;
	private String startTime;
	private String endTime;
	private long reponseTime;
	private List<User> users;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public long getReponseTime() {
		return reponseTime;
	}
	public void setReponseTime(long reponseTime) {
		this.reponseTime = reponseTime;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
