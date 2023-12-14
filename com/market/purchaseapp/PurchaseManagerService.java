package com.market.purchaseapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.adminService.ProductService;
import com.market.database.Database;
import com.market.database.ProductDb;
import com.market.database.UnitDb;
import com.market.entity.Product;

public class PurchaseManagerService {

	private ProductDb database;
	private UnitDb unitdb;
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private ProductService productService;
	private Scanner scanner;
	private String details;
	private Product product;
	private String name;
	private String unitCode;
	private String type;
	private int cost;
	private int stock;
	private String productCode;
	
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
		resultset=database.displayName(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
	}
	public void purchase() throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		product=new Product();
		scanner=new Scanner(System.in);
		System.out.println("Enter the product details format(productCode-productName-unitCode-productType-cost(in number)-stock(in number)");
		System.out.println("	->code - text, min - 2 - 6, mandatory\r\n"
				+ "	->name - text, min 3 - 30 char, mandatory\r\n"
				+ "	->unitcode - text, kg/l/piece/combo, mandatory\r\n"
				+ "	->type - text, between enumerated values, mandatory \r\n"
				+ "	->price - number, mandatory\r\n"
				+ "	->stock - number, default 0\r\n"
				+ "	");
		details=scanner.nextLine();
		String arr[]=details.split("-");
		if(arr.length==6)
		{
			product.setProductCode(arr[0]);
			product.setName(arr[1]);
			product.setUnitCode(arr[2]);
			product.setType(arr[3]);
			product.setCost(Integer.parseInt(arr[4]));
			product.setStock(Integer.parseInt(arr[5]));
			resultset=database.buyStock(product);
			if(resultset.next())
			{
				if(!database.updateStock(resultset.getInt(6)+stock,product.getProductCode()))
				{
					System.out.println("stock is not updated");
				}
			}
			else
			{
				if(!database.createProduct(product))
				{
					System.out.println("product is not added");
				}
			}
			System.out.println("Thank You");
		}
		else
		{
			System.out.println("Enter in correct format");
		}
		
	}
		
}


