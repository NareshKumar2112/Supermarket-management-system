package com.market.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.market.entity.Worker;

public class WorkerDb {

	private Connection connection;
	private Database database;
	private Statement statement;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Worker worker;
	private String userType;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNo;
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		database=new Database();
		connection=database.connect();
		return connection;
	}
	
	public boolean checkWorker(Worker worker) throws ClassNotFoundException, SQLException
	{
		this.worker=worker;
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from worker where username=?");
			prestatement.setString(1,worker.getUserName());
			resultset=prestatement.executeQuery();
			if(resultset.next())
			{
			     return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
		}
		return false;
		
	}
	public boolean createWoker(Worker worker) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.worker=worker;
		userType=worker.getUserType();
		userName=worker.getUserName();
		password=worker.getPassowrd();
		firstName=worker.getFirstName();
		lastName=worker.getLastName();
		phoneNo=worker.getPhoneNo();
		try
		{
			prestatement=connection.prepareStatement("insert into worker values(?,?,?,?,?,?)");
			prestatement.setString(1,userType+"manager");
			prestatement.setString(2,userName);
			prestatement.setString(3,password);
			prestatement.setString(4,firstName);
			prestatement.setString(5,lastName);
			prestatement.setString(6,phoneNo);
			prestatement.executeUpdate();
		}
		catch(Exception e) 
		{
			System.out.println("Database.java"+e);
			return false;
		}
		return true;
	}
	public boolean editWorker(Worker worker) throws ClassNotFoundException, SQLException
	{
		this.worker=worker;
		connection=connect();
		userType=worker.getUserType();
		userName=worker.getUserName();
		password=worker.getPassowrd();
		firstName=worker.getFirstName();
		lastName=worker.getLastName();
		phoneNo=worker.getPhoneNo();
		try
		{
			prestatement=connection.prepareStatement("update worker set usertype=?,password=?,firstname=?,lastname=?,phoneno=? where username=?");
			prestatement.setString(1,userType);
			prestatement.setString(2,password);
			prestatement.setString(3,firstName);
			prestatement.setString(4,lastName);
			prestatement.setString(5,phoneNo);
			prestatement.setString(6,userName);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public boolean deleteWoker(Worker worker) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.worker=worker;
		userName=worker.getUserName();
		try
		{
			prestatement=connection.prepareStatement("delete from worker where username=?");
			prestatement.setString(1,userName);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public ResultSet listAllWoker() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from worker");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet countWorker() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select count(*) from worker");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet searchWorker(String userName) throws ClassNotFoundException, SQLException
	{
		this.userName=userName;
		connection=connect();
		prestatement=connection.prepareStatement("select * from worker where username=?");
		prestatement.setString(1,userName);
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet paginateWorker() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from worker");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public boolean purchaseLogin(String userName,String password) throws ClassNotFoundException, SQLException
	{
		
		this.userName=userName;
		this.password=password;
		connection=connect();
		prestatement=connection.prepareStatement("select * from worker where username=? and password=? and usertype='purchase manager");
		prestatement.setString(1,userName);
		prestatement.setString(2,password);
		resultset=prestatement.executeQuery();
		while(resultset.next())
		{
			return true;
		}
		return false;
	}
	public boolean salesLogin(String userName,String password) throws ClassNotFoundException, SQLException
	{
		
		this.userName=userName;
		this.password=password;
		connection=connect();
		prestatement=connection.prepareStatement("select * from worker where username=? and password=? and usertype='purchase manager'");
		prestatement.setString(1,userName);
		prestatement.setString(2,password);
		resultset=prestatement.executeQuery();
		while(resultset.next())
		{
			return true;
		}
		return false;
	}
}
