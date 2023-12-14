package com.market.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.AdminDb;
import com.market.database.Database;
import com.market.entity.Admin;
import com.market.entity.Worker;

public class AdminEliminator {

	private Connection connection;
	private PreparedStatement prestatement;
	private AdminDb database;
	private String acknowledge;
	private Scanner s;
	private String userName;
	private Admin admin;
	
	public void delete() throws ClassNotFoundException, SQLException
	{
		database=new AdminDb();
		s=new Scanner(System.in);
		System.out.println("Detete the Worker,Enter the username");
		userName=s.nextLine();
		admin=new Admin();
		admin.setUsername(userName);
		System.out.println("Enter 'yes' to confirm");
		acknowledge=s.next();
		if(acknowledge.equals("yes"))
		{
			if(database.checkAdmin())
			{
				if(database.deleteAdmin(admin))
				{
					System.out.println("Admin is deleted successfully");
				}
				else
				{
					System.out.println("Admin is not deleted");
				}
			}
			else
			{
				System.out.println("Admin is not found");
			}
		}
		else
		{
			System.out.println("Admin is not deleted");
		}
	}
	
}
