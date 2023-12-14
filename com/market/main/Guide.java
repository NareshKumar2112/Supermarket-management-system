package com.market.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.market.admin.AdminCreator;
import com.market.adminService.AdminLogin;
import com.market.database.AdminDb;
import com.market.database.Database;
import com.market.purchaseapp.PurchaseLogin;
import com.market.salesapp.SalesLogin;

public class Guide {

	public void start() throws ClassNotFoundException, SQLException
	{
		Scanner scanner=new Scanner(System.in);
		while(true)
		{
			System.out.print(">>");
			String operator=scanner.nextLine();
			if(operator.trim().toLowerCase().equals("admin"))
			{
				AdminLogin admin=new AdminLogin();
				AdminDb database=new AdminDb();
				if(database.checkAdmin())
				{
					if(admin.check())
					{
						while(true)
						{
							System.out.println();
							AdminGuide guide=new AdminGuide();
							guide.allocate(guide.getCommand());
						}
					}
					else
					{
						System.out.println();
						System.out.println("Data is invalid");
					}
				}
				else
				{
					AdminCreator creator=new AdminCreator();
					creator.create();
				}
			}
			else if(operator.trim().toLowerCase().equals("user"))
			{	
				System.out.println("=========================Welcome to our super market=====================");
				while(true)
				{
					System.out.println();
					UserGuide user=new UserGuide();
					user.allocate(user.getCommand());
				}
			}
			else if(operator.trim().toLowerCase().equals("sales manager"))
			{
				SalesLogin sales=new SalesLogin();
				if(sales.check())
				{
					while(true)
					{
						System.out.println();
						SalesGuide guide=new SalesGuide();
						guide.allocate(guide.getCommand());
					}
				}
				else
				{
					System.out.println();
					System.out.println("Data is invalid");
				}
				
			}
			else if(operator.trim().toLowerCase().equals("purchase manager"))
			{
				PurchaseLogin purchase=new PurchaseLogin();
				if(purchase.check())
				{ 
					while(true)
					{
						System.out.println();
						PurchaseGuide guide=new PurchaseGuide();
						guide.allocate(guide.getCommand());
					}
				}
				else
				{
					System.out.println();
					System.out.println("Data is invalid");
				}
			}
			else if(operator.trim().toLowerCase().equals("exit"))
			{
				System.out.println("Thank You");
				System.exit(0);
			}
			else 
			{
				System.out.println("Command not found");
			}
		}
	 }
}

