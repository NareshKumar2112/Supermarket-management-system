package com.market.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.market.entity.Store;

public class StoreDb {

	private Connection connection;
	private Database database;
	private Statement statement;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Store store;
	private String storeName;
	private String phoneNo;
	private String gstNumber;
	private String address;
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		database=new Database();
		connection=database.connect();
		return connection;
	}
	
	public boolean createStore(Store store) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.store=store;
		storeName=store.getName();
		phoneNo=store.getPhoneNumber();
		gstNumber=store.getGstNumber();
		address=store.getAddress();
		try
		{
		prestatement=connection.prepareStatement("insert into store(name,phoneNo,gstNumber,address) values(?,?,?,?)");
		prestatement.setString(1,storeName);
		prestatement.setString(2,phoneNo);
		prestatement.setString(3,gstNumber);
		prestatement.setString(4,address);
		prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public boolean checkStore(String address) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from store where address=?");
			prestatement.setString(1,address);
			resultset=prestatement.executeQuery();
			if(resultset.next())
			{
			     return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
		}
		return false;
		
	}
	public boolean checkStore() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		try
		{
			statement=connection.createStatement();
			resultset=statement.executeQuery("select * from store");
			if(resultset.next())
			{
			     return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
		}
		return false;
	}
	public boolean editStore(Store store) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.store=store;
		storeName=store.getName();
		phoneNo=store.getPhoneNumber();
		gstNumber=store.getGstNumber();
		address=store.getAddress();
		try
		{
			prestatement=connection.prepareStatement("update store set name=?,gstNumber=?,phoneNo=? where address=?");
			prestatement.setString(1,storeName);
			prestatement.setString(2,gstNumber);
			prestatement.setString(3,phoneNo);
			prestatement.setString(4,resultset.getString(3));
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("storeEditor->"+e);
			return false;
		}
		return true;
	}
	public boolean deleteStore() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("truncate store");
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public ResultSet store() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		statement=connection.createStatement();
		resultset=statement.executeQuery("select * from store");
		return resultset;
	}
}
