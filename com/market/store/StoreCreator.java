package com.market.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.StoreDb;
import com.market.entity.Store;

public class StoreCreator {

	private Connection connection;
	private StoreDb database;
	private Statement statement;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Store store;
	private String details;
	private Scanner s;
	private String storeName;
	private String phoneNo;
	private String gstNumber;
	private String address;
	private StoreValidator validator;
	
	public void create() throws ClassNotFoundException, SQLException
	{
		database=new StoreDb();
		s=new Scanner(System.in);
		try
		{
			if(database.checkStore())
			{
				System.out.println("Store is created already");
			}
			else
			{
				System.out.println("Create your own store (format->name-phoneno-gstnumber-address)");
				System.out.println("	->name, phone number, address, gst number\r\n"
						+ "	->name  - text, mandatory with 3 to 30 chars	\r\n"
						+ "	->phone - number, mandatory, ten digits, digit start with 9/8/7/6\r\n"
						+ "	->address - text, mandatory\r\n"
						+ "	->gst number - text, 15 digit, mandatory");
				store=new Store();
				details=s.nextLine();
				String arr[]=details.split("-");
				if(arr.length!=4)
				{
					System.out.println("write in correct format");
				}
				else
				{
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
						store.setAddress(arr[3].trim());
						
						if(database.createStore(store))
						{
							System.out.println("Store is created successfully");
						}
						else
						{
							System.out.println("Store is not created successfully");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("storeCreator->"+e);
		}
	}
}
