package com.azart.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.azart.constant.BookStoreConstants;
import com.azart.entities.Order;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class OrderUtils {
	
	public void create(Order order) {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		Document doc = new Document("_id", order.get_id())
				.append("userId", order.getUserId())
				.append("userName", order.getUserName())
				.append("bookId", order.getBookId())
				.append("bookTitle", order.getBookTitle())
				.append("date", order.getDate());

		db.getCollection(BookStoreConstants.ORDERS_COLLECTION).insertOne(doc);
		
		client.close();
		
		System.out.println("Oder: [" + order.get_id() + "] added successfuly");

	}

	public void delete(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		db.getCollection(BookStoreConstants.ORDERS_COLLECTION).deleteOne(searchQuery);

		client.close();
		
		System.out.println("Order: key [" + key + "], value [" + value + "] deleted successfuly");

	}

	public Order getOrder(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		Gson gson = new Gson();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);

		FindIterable<Document> orders = db.getCollection(BookStoreConstants.ORDERS_COLLECTION).find(searchQuery);

		Order order = gson.fromJson(orders.first().toJson(), Order.class);
		
		client.close();

		return order;
	}

	public List<Order> getAllOrders() {
		
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		List<Order> orders = new ArrayList<Order>();
		Gson gson = new Gson();

		FindIterable<Document> cursor = db.getCollection(BookStoreConstants.ORDERS_COLLECTION).find();
		for (Document c : cursor) {
			Order o = gson.fromJson(c.toJson(), Order.class);

			orders.add(o);
		}

		client.close();
		
		return orders;
	}
	
	public List<Order> getOrdersByCriteria(String key, String value) {
		
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		List<Order> orders = new ArrayList<Order>();
		Gson gson = new Gson();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		
		FindIterable<Document> cursor = db.getCollection(BookStoreConstants.ORDERS_COLLECTION).find(searchQuery);
		
		for (Document c : cursor) {
			Order o = gson.fromJson(c.toJson(), Order.class);

			orders.add(o);
		}

		client.close();
		
		return orders;
	}
	
	public boolean isExist(String key, String value) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		
		FindIterable<Document> orders = db.getCollection(BookStoreConstants.ORDERS_COLLECTION).find(searchQuery);
		
		boolean is = orders.first() != null;
		client.close();
		
		return is;
	}
}
