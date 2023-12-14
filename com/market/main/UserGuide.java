package com.market.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.market.userapp.UserHelper;
import com.market.userapp.UserService;

public class UserGuide {


	private String command;
	private String[] arr;
	private Scanner s;
	
	public UserGuide()
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
			UserService user=new UserService();
			
			if(arr[arr.length-1].trim().equals("product"))
			{
				user.display();
			}
			else if(arr[1].trim().toLowerCase().equals("product")&&arr.length==3)
			{
				user.displayCat(arr[arr.length-1]);
			}
			else if(arr[1].trim().toLowerCase().equals("records"))
			{
				user.showRecords();
			}
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].trim().toLowerCase().equals("shop"))
		{
			UserService user=new UserService();
			if(user.shopping())
			{
				user.billGenerate();
			}
		}
		else if(arr[0].trim().toLowerCase().equals("help"))
		{
			UserHelper help=new UserHelper();
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
