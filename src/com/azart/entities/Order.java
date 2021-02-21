package com.azart.entities;

public class Order {
	private String _id;
	private String userId;
	private String userName;
	private String bookId;
	private String bookTitle;
	private String date;
	
	public Order() {}
	
	public Order(String _id, String userId, String userName, String bookId, String bookTitle, String date) {
		super();
		this._id = _id;
		this.userId = userId;
		this.userName = userName;
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.date = date;
	}

	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
}
