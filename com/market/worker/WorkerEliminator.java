package com.market.worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.WorkerDb;
import com.market.entity.Worker;

public class WorkerEliminator {

	private Connection connection;
	private PreparedStatement prestatement;
	private WorkerDb database;
	private String acknowledge;
	private Scanner s;
	private String userName;
	private ResultSet resultset;
	private Worker worker;
	
	public void delete() throws ClassNotFoundException, SQLException
	{
		database=new WorkerDb();
		s=new Scanner(System.in);
		System.out.println("Detete the Worker,Enter the username");
		userName=s.nextLine();
		worker=new Worker();
		worker.setUserName(userName);
		System.out.println("Enter 'yes' to confirm");
		acknowledge=s.next();
		if(acknowledge.equals("yes"))
		{
			if(database.checkWorker(worker))
			{
				if(database.deleteWoker(worker))
				{
					System.out.println("Worker is deleted successfully");
				}
				else
				{
					System.out.println("Worker is not deleted");
				}
			}
			else
			{
				System.out.println("Worker is not found");
			}
		}
		else
		{
			System.out.println("Worker is not deleted");
		}
	}
	
}
