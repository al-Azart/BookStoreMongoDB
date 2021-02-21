package com.azart.entities;

import java.util.List;

public class Book {

	private String _id;
	private String title;
	private List<String> authors;
	private String publisher;
	private String yearOfPublication;
	private double price;

	public Book() {
	}

	public Book(String _id, String title, List<String> authors, String publisher, String yearOfPublication,
			double price) {
		this._id = _id;
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.yearOfPublication = yearOfPublication;
		this.price = price;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String>  getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
