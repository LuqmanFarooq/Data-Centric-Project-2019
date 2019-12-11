package com.shops;

import java.util.ArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}
	
	
public ArrayList<HeadOffice> loadOffices() throws Exception {
		
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
		Gson gson = new Gson();
		ArrayList<HeadOffice> offices = new ArrayList<HeadOffice>();

		FindIterable<Document> coll = collection.find();

		for (Document d : coll) {
			HeadOffice office = gson.fromJson(d.toJson(), HeadOffice.class);
			offices.add(office);
			System.out.println(d.toJson());
		  }
		
		 

		return offices;
		
		}

//adding New Head Office
	public void addHeadOffice(HeadOffice head) throws Exception {
		Document doc = new Document();
		doc.append("_id",head.getId())
			.append("location",head.getLocation());
		collection.insertOne(doc);	
	}
}
