package com.ysu.model;

//对应的就是数据库中的user_id,id_user
public class User {

	private long userId;
	private String userName;
	private String userPassWord;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	public User(long userId, String userName, String userPassWord, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassWord = userPassWord;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassWord=" + userPassWord + "]";
	}
	
}
