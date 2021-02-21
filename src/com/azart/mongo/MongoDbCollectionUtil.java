package com.azart.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.azart.constant.BookStoreConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDbCollectionUtil {

	public void dropCollection(String collectionName) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		MongoCollection<Document> collection = db.getCollection(collectionName);

		collection.drop();

		client.close();

		System.out.println("Collection " + collectionName + " deleted successfully");
	}

	public List<String> getCollectionList() {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoIterable<String> collectionList = client.getDatabase(BookStoreConstants.DB_NAME).listCollectionNames();
	    
		List<String> collections = new ArrayList<>();
	    
	    if (collectionList == null) {
	    	client.close();
			return null;
		}
		
		MongoCursor<String> cursor = collectionList.iterator();
		
		while(cursor.hasNext()){
			String table = cursor.next();
			collections.add(table);
		}
		
		client.close();

		return collections;
	}

	public boolean isCollectionExist(String collectionName) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoIterable<String> collectionsList = client.getDatabase(BookStoreConstants.DB_NAME).listCollectionNames();

		for (String collection : collectionsList) {
			if (collection.equalsIgnoreCase(collectionName)) {
				client.close();
				return true;
			}
		}

		client.close();

		return false;
	}

}
