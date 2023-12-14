package com.market.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.market.entity.Unit;

public class UnitDb {

	private Connection connection;
	private Database database;
	private Statement statement;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Unit unit;
	private String code;
	private String name;
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		database=new Database();
		connection=database.connect();
		return connection;
	}
	
	public boolean checkUnit(Unit unit) throws ClassNotFoundException, SQLException
	{
		this.unit=unit;
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from unit where name=?");
			prestatement.setString(1,unit.getName());
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
	public boolean checkUnit(String code) throws ClassNotFoundException, SQLException
	{
		this.code=code;
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from unit where code=?");
			prestatement.setString(1,code);
			resultset=prestatement.executeQuery();
			if(resultset.next())
			{
			     return true;
			}
			prestatement=connection.prepareStatement("select * from unit where name=?");
			prestatement.setString(1,code);
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
	public boolean createUnit(Unit unit) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.unit=unit;
		name=unit.getName();
		code=unit.getCode();
		try
		{
			prestatement=connection.prepareStatement("insert into unit values(?,?)");
			prestatement.setString(1,name);
			prestatement.setString(2,code);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public boolean editUnit(Unit unit) throws ClassNotFoundException, SQLException
	{
		this.unit=unit;
		name=unit.getName();
		code=unit.getCode();
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("update unit set name=? where code=?");
			prestatement.setString(1,name);
			prestatement.setString(2,code);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java"+e);
			return false;
		}
		return true;
	}
	public boolean deleteUnit(Unit unit) throws ClassNotFoundException, SQLException
	{
		this.unit=unit;
		code=unit.getCode();
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("delete from unit where code=?");
			prestatement.setString(1,code);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public ResultSet listUnit() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from unit");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet displayUnit(String catagory) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from product where unit=?");
		prestatement.setString(1,catagory);
		resultset=prestatement.executeQuery();
		return resultset;
	}
}
