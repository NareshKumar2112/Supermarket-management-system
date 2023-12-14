package com.market.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.StoreDb;

public class StoreEliminator {

	private Connection connection;
	private PreparedStatement prestatement;
	private StoreDb database;
	private String acknowledge;
	private Scanner s;
	private Statement statement;
	private ResultSet resultset;
	
	public void delete() throws ClassNotFoundException, SQLException
	{
		database=new StoreDb();
		s=new Scanner(System.in);
		if(database.checkStore())
		{
			System.out.println("Enter 'yes' to confirm");
			acknowledge=s.next();
			if(acknowledge.equals("yes"))
			{
				if(database.deleteStore())
				{
					System.out.println("Store is deleted successfully");
				}
				else
				{
					System.out.println("Store is not deleted");
				}
			}
			else
			{
				System.out.println("Store is not deleted");
			}
		}
		else
		{
			System.out.println("Store is not found");
		}
	}
	
}
