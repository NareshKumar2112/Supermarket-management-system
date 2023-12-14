package com.market.admin;

import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.AdminDb;
import com.market.database.Database;
import com.market.entity.Admin;

public class AdminCreator {

	private String name;
	private String userName;
	private String password;
	private AdminValidator validator;
	private Scanner scanner;
	private Admin admin;
	private AdminDb database;
	
	public void create() throws ClassNotFoundException, SQLException
	{
		admin=new Admin();
		database=new AdminDb();
		validator=new AdminValidator();
		scanner=new Scanner(System.in);
		System.out.println("Enter the details format(name-username-password)");
		System.out.println("	->name ,mandatory\r\n"
				+ "	->username - text, min 3 - 30 char, mandatory\r\n"
				+ "	->password - text, alphanumeric, special char, min 8 char, mandatory\r\n");
		String detail=scanner.nextLine();
		String arr[]=detail.split("-");
		admin.setName(arr[0].trim().toLowerCase());
		admin.setPassword(arr[2].trim().toLowerCase());
		admin.setUsername(arr[1].trim().toLowerCase());
		if(arr.length==3&&validator.isValidName(arr[0].trim().toLowerCase())&&validator.isvalidpassword(arr[1].trim().toLowerCase())&&validator.isvalidUserName(arr[2].trim().toLowerCase()))
		{
			
			if(database.checkAdmin())
			{
				System.out.println("Admin is already created");
			}
			else
			{
				if(database.createAdmin(admin))
				{
					System.out.println("Admin is created successfully");
				}
				else
				{
					System.out.println("Admin is not created");
				}
			}
		}
		else
		{
			System.out.println("Enter the correct format");
		}
	}
}
