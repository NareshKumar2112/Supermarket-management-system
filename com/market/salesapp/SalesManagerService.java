package com.market.salesapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.market.adminService.ProductService;
import com.market.database.Database;
import com.market.database.ProductDb;
import com.market.database.UnitDb;

public class SalesManagerService {

	private ProductDb database;
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private ProductService productService;
	private UnitDb unitdb;
	
	public void display() throws ClassNotFoundException, SQLException
	{
		productService=new ProductService();
		productService.list();
	}
	public void count() throws ClassNotFoundException, SQLException
	{
		productService=new ProductService();
		productService.count();
	}
	public void displayCat(String catagory) throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		unitdb=new UnitDb();
		resultset=database.displayName(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
		resultset=database.displayType(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
		resultset=unitdb.displayUnit(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5));
		}
	}
}
