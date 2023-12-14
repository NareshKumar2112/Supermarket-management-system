package com.market.purchaseapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.WorkerDb;

public class PurchaseLogin {

	private WorkerDb database;
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private String username;
	private String password;
	private Scanner scanner;
	
	public boolean check() throws ClassNotFoundException, SQLException
	{
		database=new WorkerDb();
		scanner=new Scanner(System.in);
		System.out.println("Enter the details format(username-password)");
		String details=scanner.nextLine();
		String arr[]=details.split("-");
		if(arr.length==2)
		{
		username=arr[0].trim().toLowerCase();
		password=arr[1].trim().toLowerCase();
		return database.purchaseLogin(username,password);
		}
		else
		{
			System.out.println("Enter the correct format");
			return false;
		}
	}
}
