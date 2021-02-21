package com.azart.mongo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.bson.Document;

import com.azart.constant.BookStoreConstants;
import com.azart.entities.Book;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class BookUtils {

	public void create(Book book) {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		Document doc = new Document("_id", book.get_id()).append("title", book.getTitle())
				.append("authors", book.getAuthors()).append("publisher", book.getPublisher())
				.append("yearOfPublication", book.getYearOfPublication()).append("price", book.getPrice());

		db.getCollection(BookStoreConstants.BOOKS_COLLECTION).insertOne(doc);
		client.close();

		System.out.println("Book: [" + book.getTitle() + "] added successfuly");
	}

	public void delete(String key, String value) {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		db.getCollection(BookStoreConstants.BOOKS_COLLECTION).deleteOne(searchQuery);

		client.close();

		System.out.println("Book: key [" + key + "], value [" + value + "] deleted successfuly");
	}

	public Book getBook(String key, String value) {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		Gson gson = new Gson();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);

		FindIterable<Document> books = db.getCollection(BookStoreConstants.BOOKS_COLLECTION).find(searchQuery);

		Book book = gson.fromJson(books.first().toJson(), Book.class);

		client.close();

		return book;
	}

	public void update(String key, String value, String fieldName, String newFieldValue) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		BasicDBObject searchQuery = new BasicDBObject();
		BasicDBObject setData = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		searchQuery.append(key, value);
		setData.append(fieldName, newFieldValue);
		update.append("$set", setData);

		db.getCollection(BookStoreConstants.BOOKS_COLLECTION).updateOne(searchQuery, update);

		client.close();
		System.out.println("Book: key [" + key + "], value [" + value + "] updated successfuly");
	}

	public List<Book> getAllBooks() {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		List<Book> books = new ArrayList<Book>();
		Gson gson = new Gson();

		FindIterable<Document> cursor = db.getCollection(BookStoreConstants.BOOKS_COLLECTION).find();

		for (Document c : cursor) {
			Book b = gson.fromJson(c.toJson(), Book.class);

			books.add(b);
		}

		client.close();

		return books;
	}

	public boolean isExist(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);

		FindIterable<Document> books = db.getCollection(BookStoreConstants.BOOKS_COLLECTION).find(searchQuery);

		boolean is = books.first() != null;

		client.close();

		return is;
	}

	public List<Book> getBooksFromFile(ServletContext context) {

		String filename = ResourceBundle.getBundle("resources/pathToFile").getString("books.json.path");
		List<Book> books = new ArrayList<Book>();
		InputStream is = context.getResourceAsStream(filename);
		StringBuilder sb = new StringBuilder();
		Gson gson = new Gson();

		if (is != null) {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(isr);
				String text;

				while ((text = reader.readLine()) != null) {
					sb.append(text);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		books = Arrays.asList(gson.fromJson(sb.toString(), Book[].class));

		return books;
	}

}
