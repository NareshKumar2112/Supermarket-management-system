package com.market.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.ProductDb;
import com.market.entity.Product;

public class ProductEditor {

	private PreparedStatement prestatement;
	private Connection connection;
	private ProductDb database;
	private String unitCode;
	private String name;
	private String type;
	private Scanner s;	
	private int cost;
	private int stock;
	private String details;
	private String productCode;
	private ResultSet resultset;
	private Product product;
	private ProductValidator validator;
	
	public void update() throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		s=new Scanner(System.in);
		System.out.println("update the product format(productCode-productName-unitCode-productType-cost(in number)-stock(in number)");
		System.out.println("	->code - text, min - 2 - 6, mandatory\r\n"
				+ "	->name - text, min 3 - 30 char, mandatory\r\n"
				+ "	->unitcode - text, kg/l/piece/combo, mandatory\r\n"
				+ "	->type - text, between enumerated values, mandatory \r\n"
				+ "	->price - number, mandatory\r\n"
				+ "	->stock - number, default 0\r\n"
				+ "	");
		
		details=s.nextLine();
		String arr[]=details.split("-");
		
		try
		{
			if(arr.length!=6)
			{
				System.out.println("Enter the correct format");
			}
			else
			{
				if(validator.isvalidCode(arr[0])&&validator.isvalidName(arr[1])&&validator.isvalidType(arr[3])&&validator.isvalidUnit(arr[2]))
				{
				product=new Product();
				product.setProductCode(arr[0]);
				product.setName(arr[1]);
				product.setUnitCode(arr[2]);
				product.setType(arr[3]);
				product.setCost(Integer.parseInt(arr[4]));
				product.setStock(Integer.parseInt(arr[5]));

				if(database.checkProduct(product))
				{
					if(database.editProduct(product))
					{
						System.out.println("Product is update successfully");
					}
					else
					{
						System.out.println("Product is not updated");
					}
				}
				else
				{
					System.out.println("Product is  not fount");
				}
				}
				else
				{
					System.out.println("Data is invalid format");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("product editor->"+e);
		}
	}

}
