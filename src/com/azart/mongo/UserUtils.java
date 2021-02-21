package com.azart.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.azart.constant.BookStoreConstants;
import com.azart.entities.User;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class UserUtils {

	public void create(User user) {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		
		Document doc = new Document("_id", user.get_id())
				.append("email", user.getEmail())
				.append("name", user.getName())
				.append("password", user.getPassword())
				.append("role", user.getRole());

		db.getCollection(BookStoreConstants.USERS_COLLECTION).insertOne(doc);
		
		client.close();
		
		System.out.println("User: [" + user.getName() + "] added successfuly");

	}

	public void delete(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		db.getCollection(BookStoreConstants.USERS_COLLECTION).deleteOne(searchQuery);

		client.close();
		
		System.out.println("User: key [" + key + "], value [" + value + "] deleted successfuly");

	}

	public User getUser(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		Gson gson = new Gson();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);

		FindIterable<Document> users = db.getCollection(BookStoreConstants.USERS_COLLECTION).find(searchQuery);

		User user = gson.fromJson(users.first().toJson(), User.class);
		
		client.close();

		return user;
	}

	public List<User> getAllUsers() {
		
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		
		List<User> users = new ArrayList<User>();
		Gson gson = new Gson();

		FindIterable<Document> cursor = db.getCollection(BookStoreConstants.USERS_COLLECTION).find();
		
		for (Document c : cursor) {
			User u = gson.fromJson(c.toJson(), User.class);

			users.add(u);
		}

		client.close();
		
		return users;
	}
	
	public boolean isExist(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		
		FindIterable<Document> users = db.getCollection(BookStoreConstants.USERS_COLLECTION).find(searchQuery);
		
		boolean is = users.first() != null;
		
		client.close();
		
		return is;
	}
}
