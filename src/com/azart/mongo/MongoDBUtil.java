package com.azart.mongo;

import java.util.ArrayList;
import java.util.List;

import com.azart.constant.BookStoreConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDBUtil {

	public void dropDB(String dbName) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDatabase db = client.getDatabase(dbName);

		db.drop();

		client.close();

		System.out.println("Database " + BookStoreConstants.DB_NAME + " deleted successfully");
	}

	public List<String> getDBList() {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoIterable<String> dbList = client.listDatabaseNames();
		
		List<String> databases = new ArrayList<>();

		if (dbList == null) {
			client.close();
			return null;
		}
		MongoCursor<String> cursor = dbList.iterator();
		while (cursor.hasNext()) {
			databases.add(cursor.next());
		}
		
		client.close();

		return databases;
	}

	public boolean isDBExist(String dbName) {
		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoIterable<String> dbList = client.listDatabaseNames();

		for (String database : dbList) {
			if (database.equalsIgnoreCase(dbName)) {
				client.close();
				return true;
			}
		}

		client.close();

		return false;
	}
}
