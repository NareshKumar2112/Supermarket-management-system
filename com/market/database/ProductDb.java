package com.market.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.market.entity.Product;

public class ProductDb {

	private Connection connection;
	private Database database;
	private Statement statement;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private Product product;
	private String productCode;
	private String name;
	private String unitCode;
	private String type;
	private int cost;
	private int stock;
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		database=new Database();
		connection=database.connect();
		return connection;
	}
	public boolean checkProduct(Product product) throws ClassNotFoundException, SQLException
	{
		this.product=product;
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from product where code=? or name=?");
			prestatement.setString(1,product.getProductCode());
			prestatement.setString(2, product.getName());
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
	
	public boolean checkProduct(String code) throws ClassNotFoundException, SQLException
	{
		this.product=product;
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from product where code=?");
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
	public boolean createProduct(Product product) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.product=product;
		productCode = product.getProductCode();
		name=product.getName();
		unitCode=product.getUnitCode();
		type=product.getType();
		cost=product.getCost();
		stock=product.getStock();
		
		try
		{			
			prestatement=connection.prepareStatement("insert into product values(?,?,?,?,?,?,?)");
			prestatement.setString(1,productCode);
			prestatement.setString(2,name);
			prestatement.setString(3,unitCode);
			prestatement.setString(4,type);
			prestatement.setInt(5,cost);
			prestatement.setInt(6, stock);
			prestatement.setInt(7,0);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("productCreator->"+e);
			return false;
		}
		return true;
	}
	public boolean editProduct(Product product) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.product=product;
		productCode = product.getProductCode();
		name=product.getName();
		unitCode=product.getUnitCode();
		type=product.getType();
		cost=product.getCost();
		stock=product.getStock();
		try
		{
			prestatement=connection.prepareStatement("update product set name=?,unit=?,type=?,cost=?,stock=? where code=?");
			prestatement.setString(1,name);
			prestatement.setString(2,unitCode);
			prestatement.setString(3,type);
			prestatement.setInt(4,cost);
			prestatement.setInt(5,stock);
			prestatement.setString(6,productCode);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public boolean deleteProduct(Product product) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.product=product;
		productCode=product.getProductCode();
		try
		{
			prestatement=connection.prepareStatement("delete from product where code=?");
			prestatement.setString(1,this.productCode);
			prestatement.executeUpdate();
		}
		
		catch(Exception e)
		{
			System.out.println("Database->"+e);
			return false;
		}
		return true;
	}
	public ResultSet listAllProduct() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select *from product");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet countProduct() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select count(*) from product");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet searchProduct(String productCode) throws ClassNotFoundException, SQLException
	{
		this.productCode=productCode;
		connection=connect();
		prestatement=connection.prepareStatement("select * from product where code=?");
		prestatement.setString(1,productCode);
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet paginateProduct() throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from product");
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet displayType(String catagory) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from product where type=?");
		prestatement.setString(1,catagory);
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet displayName(String catagory) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		prestatement=connection.prepareStatement("select * from product where name=?");
		prestatement.setString(1,catagory);
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public ResultSet buyStock(Product product) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.product=product;
		productCode=product.getProductCode();
		name=product.getName();
		unitCode=product.getUnitCode();
		type=product.getType();
		cost=product.getCost();
		stock=product.getStock();
		prestatement=connection.prepareStatement("select * from product where code=?");
		prestatement.setString(1,productCode);
		resultset=prestatement.executeQuery();
		return resultset;
	}
	public boolean updateStock(int stock,String code) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.product=product;
		try
		{
		prestatement=connection.prepareStatement("update product set stock=? where code=?");
		prestatement.setInt(1,stock);
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
	public boolean updateStockProduct(int qnt,String item,int stock,int sell) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		this.product=product;
		try
		{
			prestatement=connection.prepareStatement("update product set stock=?,sell=? where code=?");
			prestatement.setInt(1,stock-qnt);
			prestatement.setInt(2,sell+qnt);
			prestatement.setString(3,item);
			prestatement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			return false;
		}
		return true;
	}
	public ResultSet checkStock(int qnt,String item) throws ClassNotFoundException, SQLException
	{
		connection=connect();
		try
		{
			prestatement=connection.prepareStatement("select * from product where code=? and stock>=?");
			prestatement.setString(1,item);
			prestatement.setInt(2,qnt);
			resultset=prestatement.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("Database.java->"+e);
			
		}
		return resultset;
	}
	
}
