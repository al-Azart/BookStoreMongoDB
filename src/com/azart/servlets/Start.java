package com.azart.servlets;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.azart.constant.BookStoreConstants;
import com.azart.entities.Book;
import com.azart.mongo.BookUtils;
import com.azart.mongo.MongoDBUtil;
import com.azart.mongo.MongoDbCollectionUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MongoClient client = new MongoClient(BookStoreConstants.DB_HOST, BookStoreConstants.DB_PORT);
		MongoDBUtil dbUtil = new MongoDBUtil();
		MongoDbCollectionUtil collectionUtil = new MongoDbCollectionUtil();


		if (!dbUtil.isDBExist(BookStoreConstants.DB_NAME)) {

			createUsersMongoCollection(client);
			createBooksMongoCollections(client);

		} else {
			if (!collectionUtil.isCollectionExist(BookStoreConstants.USERS_COLLECTION)) {
				createUsersMongoCollection(client);
			}
			if (!collectionUtil.isCollectionExist(BookStoreConstants.BOOKS_COLLECTION)) {
				createBooksMongoCollections(client);
			}
		}

		System.out.println("BookStore started successfuly");
		response.sendRedirect(request.getContextPath() + "/loginPage.jsp");
	}

	private void createUsersMongoCollection(MongoClient client) {

		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);
		MongoCollection<Document> collection = db.getCollection(BookStoreConstants.USERS_COLLECTION);
		IndexOptions indexOptions = new IndexOptions().unique(true);

		Document doc = new Document("_id", ResourceBundle.getBundle("resources/admin").getString("admin.id"))
				.append("email", ResourceBundle.getBundle("resources/admin").getString("admin.email"))
				.append("name", ResourceBundle.getBundle("resources/admin").getString("admin.name"))
				.append("password", ResourceBundle.getBundle("resources/admin").getString("admin.password"))
				.append("role", ResourceBundle.getBundle("resources/admin").getString("role"));

		collection.createIndex(Indexes.ascending("email"), indexOptions);

		db.getCollection(BookStoreConstants.USERS_COLLECTION).insertOne(doc);

		System.out.println("User: [" + ResourceBundle.getBundle("resources/admin").getString("admin.name")
				+ "] added successfuly");

		System.out.println("Collection: [" + BookStoreConstants.USERS_COLLECTION + "] created successfuly");
	}

	private void createBooksMongoCollections(MongoClient client) {

		ServletContext context = getServletContext();
		MongoDatabase db = client.getDatabase(BookStoreConstants.DB_NAME);

		BookUtils butil = new BookUtils();
		List<Book> books = butil.getBooksFromFile(context);

		for (Book b : books) {
			Document doc = new Document("_id", b.get_id()).append("title", b.getTitle())
					.append("authors", b.getAuthors()).append("publisher", b.getPublisher())
					.append("yearOfPublication", b.getYearOfPublication()).append("price", b.getPrice());

			db.getCollection(BookStoreConstants.BOOKS_COLLECTION).insertOne(doc);
			System.out.println("Book: [" + b.getTitle() + "] added successfuly");
		}

		System.out.println("Collection: [" + BookStoreConstants.BOOKS_COLLECTION + "] created successfuly");
	}
}
