package com.market.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.StoreDb;
import com.market.entity.Store;

public class StoreEditor {

	private Connection connection;
	private PreparedStatement prestatement;
	private String name;
	private String phoneNo;
	private String gstNumber;
	private StoreDb database;
	private String updateString;
	private Scanner s;
	private Store store;;
	private Statement statement;
	private ResultSet resultset;
	private StoreValidator validator;
	private String address;
	
	public void update() throws ClassNotFoundException, SQLException
	{
		database=new StoreDb();
		s=new Scanner(System.in);
		store=new Store();
		
		System.out.println("Update the store format->name-phoneNumber-gstNumber-address");
		System.out.println("	->name, phone number, address, gst number\r\n"
				+ "	->name  - text, mandatory with 3 to 30 chars	\r\n"
				+ "	->phone - number, mandatory, ten digits, digit start with 9/8/7/6\r\n"
				+ "	->address - text, mandatory\r\n"
				+ "	->gst number - text, 15 digit, mandatory");
		updateString=s.nextLine();
		String arr[]=updateString.split("-");
		
		validator=new StoreValidator();
		if(!validator.isvalidName(arr[0]))
		{
			System.out.println("please enter the name with length >=3  and =<30");
		}
		if(!validator.isvalidPhoneNo(arr[1]))
		{
			System.out.println("Enter the valid phone number");
		}
		if(!validator.isvalidGstNumber(arr[2]))
		{
			System.out.println("Enter the valid GstNumber");
		}
		if(validator.isvalidName(arr[0])&&validator.isvalidPhoneNo(arr[1])&&validator.isvalidGstNumber(arr[2]))
		{
			store.setName(arr[0].trim());
			store.setPhoneNumber(arr[1].trim());
			store.setGstNumber(arr[2].trim());	
			store.setAddress(arr[arr.length-1].trim());
			if(database.checkStore(address))
			{
				if(database.editStore(store))
				{
					System.out.println("Store is editted successfully");
				}
				else
				{
					System.out.println("Store is not editted successfully");
				}
			}
			else
			{
					System.out.println("Store is not found");
			}
		}
	}
}
