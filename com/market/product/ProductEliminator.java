package com.market.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.ProductDb;
import com.market.entity.Product;

public class ProductEliminator {

	private Connection connection;
	private PreparedStatement prestatement;
	private ProductDb database;
	private String acknowledge;
	private Scanner s;
	private String productCode;
	private ResultSet resultset;
	private Product product;
	
	public void delete() throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		s=new Scanner(System.in);
		System.out.println("Detete the Product,Enter the product code");
		productCode=s.nextLine();
		product=new Product();
		product.setProductCode(productCode);
		if(database.checkProduct(product.getProductCode()))
		{
			System.out.println("Enter 'yes' to confirm");
			acknowledge=s.next();
			if(acknowledge.equals("yes"))
			{
				if(database.deleteProduct(product))
				{
					System.out.println("Product is deleted successfully");
				}
				else
				{
					System.out.println("Product is not deleted");
				}
			}
		}
		else
		{
			System.out.println("Product not found");
		}
		
	}
	
}
