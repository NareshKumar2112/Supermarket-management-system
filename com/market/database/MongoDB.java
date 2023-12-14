package com.market.database;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.market.entity.Shop;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;
	private Shop shop;
	private String name;
	private String phoneNo;
	private ArrayList<String> productName;
	private ArrayList<Integer>quantity;
	private ArrayList<Integer>cost;
	private ArrayList<Integer>cost_qnt;
	private int total;
	private String date;
	
	public void Connection()
	{
		mongoClient=new MongoClient("localhost",27017);
		mongoDatabase=mongoClient.getDatabase("Supermarket");
		mongoCollection=mongoDatabase.getCollection("Shopping");
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}

	public void setMongoDatabase(MongoDatabase mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}

	public MongoCollection<Document> getMongoCollection() {
		return mongoCollection;
	}

	public void setMongoCollection(MongoCollection<Document> mongoCollection) {
		this.mongoCollection = mongoCollection;
	}
	public void shoppingRecords(Shop shop)
	{
		this.shop=shop;
		name=shop.getName();
		phoneNo=shop.getPhoneNo();
		productName=shop.getProductName();
		quantity=shop.getQuantity();
		cost=shop.getCost();
		cost_qnt=shop.getCost_qnt();
		total=shop.getTotal();
		date=shop.getDate();
		Connection();
		mongoCollection.insertOne(new Document().append("name",name).append("phoneNo",phoneNo).append("productName",	productName ).append("quantity",quantity).append("cost/qty",cost_qnt).append("cost",cost).append("total amount",total).append("date",date));
	}
	public MongoCursor<Document> displayBill(String name,String date)
	{
		Connection();
		BasicDBObject object=new BasicDBObject();
		object.put("name",name.toLowerCase().trim());
		object.put("date",date.toLowerCase().trim());
		MongoCursor<Document> cursor=mongoCollection.find(object).iterator();
		return cursor;
	}
	public MongoCursor<Document> displayIndividual(String name,String phoneNo)
	{
		Connection();
		BasicDBObject object=new BasicDBObject();
		object.put("name",name);
		object.put("phoneNo",phoneNo);
		MongoCursor<Document> cursor=mongoCollection.find(object).iterator();
		return cursor;
	}
	public MongoCursor<Document> displayAll()
	{
		Connection();
		MongoCursor<Document> cursor=mongoCollection.find().iterator();
		return cursor;
	}
	public MongoCursor<Document> displayDatewise(String date)
	{
		Connection();
		this.date=date;
		BasicDBObject object=new BasicDBObject();
		object.put("date",date);
		MongoCursor<Document> cursor=mongoCollection.find(object).iterator();
		return cursor;
	}
}
