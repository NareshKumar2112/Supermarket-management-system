package com.market.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.ProductDb;
import com.market.database.UnitDb;
import com.market.entity.Product;

public class ProductCreator {

	private String name;
	private String unitCode;
	private String type;
	private int cost;
	private int stock;
	private Connection connection;
	private ProductDb database;
	private PreparedStatement prestatement;
	private Scanner s;
	private String details;
	private Product product;
	private String productCode;
	private ProductValidator validator;
	private UnitDb unitdb;
	
	public void create() throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		s=new Scanner(System.in);
		product=new Product();
		unitdb=new UnitDb();
		System.out.println("Enter the product details format(productCode-productName-unitCode-productType-cost(in number)-stock(in number)");
		System.out.println("	->code - text, min - 2 - 6, mandatory\r\n"
				+ "	->name - text, min 3 - 30 char, mandatory\r\n"
				+ "	->unitcode - text, kg/l/pi/pack, mandatory\r\n"
				+ "	->type - text,fruit/vegetable/grosery/others, mandatory \r\n"
				+ "	->price - number, mandatory\r\n"
				+ "	->stock - number, default 0\r\n"
				+ "	");
		
		details=s.nextLine();
		validator=new ProductValidator();
		String arr[]=details.split("-");
		if(arr.length==6)
		{
			if(validator.isvalidCode(arr[0].trim().toLowerCase())&&validator.isvalidName(arr[1].trim().toLowerCase())&&validator.isvalidType(arr[3].trim().toLowerCase())&&validator.isvalidUnit(arr[2].trim().toLowerCase()))
			{
				product.setProductCode(arr[0]);
				product.setName(arr[1]);
				product.setUnitCode(arr[2]);
				product.setType(arr[3]);
				product.setCost(Integer.parseInt(arr[4]));
				product.setStock(Integer.parseInt(arr[5]));
				
				if(database.checkProduct(product))
				{
					System.out.println("Product is already created");
				}
				else
				{
					if(unitdb.checkUnit(product.getUnitCode()))
					{
						if(database.createProduct(product))
						{
							System.out.println("Product is created successfully");
						}
						else
						{
							System.out.println("Product is not created");
						}
					}
					else
					{
						System.out.println("Unit is not found");
					}
				}
			}
			else
			{
				System.out.println("Data is invalid format");
			}	
		}	
		else
		{
			System.out.println("Enter in correct format");
		}
		
	}
}
