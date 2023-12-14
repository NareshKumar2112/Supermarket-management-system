package com.market.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.market.purchaseapp.PurchaseHelper;
import com.market.purchaseapp.PurchaseManagerService;
import com.market.salesapp.SalesManagerService;

public class PurchaseGuide {

	private String command;
	private String[] arr;
	private Scanner s;
	
	public PurchaseGuide()
	{
		s=new Scanner(System.in);
		System.out.print(">>");
		this.command=s.nextLine();
	}
	public String getCommand()
	{
		return command;
	}
	public void allocate(String command) throws ClassNotFoundException, SQLException
	{
		this.command=command;
		arr=command.trim().split("\\s+");
		
		if(arr[0].trim().toLowerCase().equals("list"))
		{
			PurchaseManagerService manager=new PurchaseManagerService();
			
			if(arr[arr.length-1].trim().equals("product"))
			{
				manager.display();
			}
			else if(arr[1].trim().toLowerCase().equals("product")&&arr.length==3)
			{
				manager.displayCat(arr[arr.length-1]);
			}
			
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].trim().toLowerCase().equals("count"))
		{
			PurchaseManagerService manager=new PurchaseManagerService();
			
			if(arr[arr.length-1].trim().equals("product"))
			{
				manager.count();
			}
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].trim().toLowerCase().equals("purchase")&&arr[1].trim().toLowerCase().equals("product"))
		{
			PurchaseManagerService manager=new PurchaseManagerService();
			manager.purchase();
		}
		else if(arr[0].trim().toLowerCase().equals("help"))
		{
			PurchaseHelper help=new PurchaseHelper();
			help.help();
		}
		else if(arr[0].trim().toLowerCase().equals("exit"))
		{
			Guide guide=new Guide();
			guide.start();
		}
		else
		{
			System.out.println("Comannd not found");
		}
	}
	
}
