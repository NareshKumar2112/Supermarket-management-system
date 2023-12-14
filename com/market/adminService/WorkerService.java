package com.market.adminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.WorkerDb;

public class WorkerService {

	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Scanner scanner;
	private WorkerDb database;
	
	public void count() throws ClassNotFoundException, SQLException
	{
		database=new WorkerDb();
		resultset=database.countWorker();
		while(resultset.next())
		{
			System.out.println("The Number of workers = "+resultset.getInt(1));
		}
	}
	public void list() throws ClassNotFoundException, SQLException
	{
		database=new WorkerDb();
		resultset=database.listAllWoker();
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"       "+resultset.getString(4)+" "+resultset.getString(5)+"         "+resultset.getString(6));
		}
	}
	public void paginate(String page) throws ClassNotFoundException, SQLException
	{
		int pagenate=Integer.parseInt(page)*10;
		database=new WorkerDb();
		resultset=database.paginateWorker();
		int count=0;
		while(resultset.next())
		{
			if(pagenate-10<=count&&count<=pagenate)
			{				
				System.out.println(resultset.getString(1)+"       "+resultset.getString(4)+" "+resultset.getString(5)+"         "+resultset.getString(6));
			}
			count++;
		}
	}
	public void search() throws ClassNotFoundException, SQLException
	{
		database=new WorkerDb();
		scanner=new Scanner(System.in);
		System.out.println("->Enter the username");
		String username=scanner.nextLine();
		resultset=database.searchWorker(username);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"       "+resultset.getString(4)+" "+resultset.getString(5)+"         "+resultset.getString(6));
		}
	}
}
