package com.azart.constant;

import java.util.ResourceBundle;

public class BookStoreConstants {
    public static final String DB_HOST = ResourceBundle.getBundle("resources/connectionDB").getString("server.host");
    public static final Integer DB_PORT = Integer.parseInt(ResourceBundle.getBundle("resources/connectionDB").getString("server.port"));
    public static final String DB_NAME = ResourceBundle.getBundle("resources/db").getString("dbname");
    
    public static final String USERS_COLLECTION = ResourceBundle.getBundle("resources/db").getString("collectionUsers");
    public static final String BOOKS_COLLECTION = ResourceBundle.getBundle("resources/db").getString("collectionBooks");
    public static final String ORDERS_COLLECTION = ResourceBundle.getBundle("resources/db").getString("collectionOrders");
    
    public static final String SECRET_KEY = ResourceBundle.getBundle("resources/secret_key").getString("key");
    
    public static final String ADMIN_ROLE = "admin";
    public static final String USER_ROLE = "user";
    
    public static final String PROJECT = "Book Store";
    
    public static final Integer TOKEN_LIVE_TIME = 800000;
}
