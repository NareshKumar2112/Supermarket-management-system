package com.market.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.market.entity.Admin;

public class AdminDb {

	private Connection connection;
	private Database database;
	private Statement statement;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Admin admin;
	private String Name;
	private String password;
	private String username;
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		database=new Database();
		connection=database.connect();
		return connection;
	}
	
	public boolean checkAdmin(Admin admin) throws ClassNotFoundException, SQLException
	{
		this.admin=admin;
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from admin where username=? and password=?");
			prestatement.setString(1,admin.getUsername());
			prestatement.setString(2,admin.getPassword());
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
	public boolean checkAdmin() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from admin");
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
	public boolean createAdmin(Admin admin) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.admin=admin;
		String userName = admin.getUsername();
		Name=admin.getName();
		password=admin.getPassword();
		try
		{
			prestatement=connection.prepareStatement("insert into admin values(?,?,?)");
			prestatement.setString(1,Name);
			prestatement.setString(2,userName);
			prestatement.setString(3,password);
			prestatement.executeUpdate();
		}
		catch(Exception e) 
		{
			System.out.println("Database.java"+e);
			return false;
		}
		return true;
	}
	public boolean editAdmin(Admin admin) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.admin=admin;
		Name=admin.getName();
		password=admin.getPassword();
		username=admin.getUsername();
		try
		{
			prestatement=connection.prepareStatement("update admin set name=?,password=? where username=?");
			prestatement.setString(1,Name);
			prestatement.setString(2,password);
			prestatement.setString(3,username);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public boolean deleteAdmin(Admin admin) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.admin=admin;
		username=admin.getUsername();
		try
		{
			prestatement=connection.prepareStatement("delete from admin where username=?");
			prestatement.setString(1,username);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
}
