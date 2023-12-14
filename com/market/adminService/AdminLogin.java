package com.market.adminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.market.admin.AdminValidator;
import com.market.database.AdminDb;
import com.market.database.Database;
import com.market.entity.Admin;
import com.market.store.StoreCreator;

public class AdminLogin {

	private AdminDb database;
	private Scanner scanner;
	private String details;
	private AdminValidator validator;
	private Admin admin;
	
	public boolean check() throws ClassNotFoundException, SQLException
	{
		scanner=new Scanner(System.in);
		database=new AdminDb();
		System.out.println("Enter the details format(username-password)");
		details=scanner.nextLine();
		String arr[]=details.split("-");
		validator=new AdminValidator();
		admin=new Admin();
		if(arr.length==2)
		{
			admin.setUsername(arr[0].trim().toLowerCase());
			admin.setPassword(arr[1].trim().toLowerCase());
			if(validator.isvalidUserName(admin.getUsername())&&validator.isvalidpassword(admin.getPassword()))
			{
				if(database.checkAdmin())
				{
					if(database.checkAdmin(admin))
					{
						System.out.println("Login successfull");
						return true;
					}
					else
					{
						System.out.println("Login falied");
						return false;
					}
				}
				else
				{
					System.out.println("Admin is not found");
					return false;
				}
				
			}
		
			else
			{
				System.out.println("Enter the correct details");
				return false;
			}
		}
		else
		{
			System.out.println("Enter the correct details");
			return false;
		}
	}
		
}
