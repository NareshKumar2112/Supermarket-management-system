package com.market.adminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.market.database.Database;
import com.market.database.MongoDB;
import com.market.database.ProductDb;
import com.market.database.StoreDb;
import com.market.database.UnitDb;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ProductService {

	private Connection connection;
	private PreparedStatement prestatement;
	private ProductDb database;
	private UnitDb unit_database;
	private StoreDb store_database;
	private ResultSet resultset;
	private Scanner scanner;
	private MongoCollection collection;
	private MongoDB mongoDb;
	
	public void count() throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		resultset=database.countProduct();
		while(resultset.next())
		{
			System.out.println("The Number of products = "+resultset.getInt(1));
		}
	}
	public void displayStore() throws ClassNotFoundException, SQLException
	{
		store_database=new StoreDb();
		resultset=store_database.store();
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"      "+resultset.getString(2)+"       "+resultset.getString(3)+"     "+resultset.getString(4));
		}
	}
	public void list() throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		resultset=database.listAllProduct();
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
		}
	}
	public void listUnit() throws ClassNotFoundException, SQLException
	{
		unit_database=new UnitDb();
		resultset=unit_database.listUnit();
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"          "+resultset.getString(2));
		}
	}
	public void paginate(String page) throws ClassNotFoundException, SQLException
	{
		int pagenate=Integer.parseInt(page)*10;
		database=new ProductDb();
		resultset=database.paginateProduct();
		int count=0;
		while(resultset.next())
		{
			if(pagenate-10<=count&&count<=pagenate)
			{				
				System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			}
			count++;
		}
	}
	public void search() throws ClassNotFoundException, SQLException
	{
		scanner=new Scanner(System.in);
		System.out.println("Enter the productCode");
		String code=scanner.nextLine();
		database=new ProductDb();
		resultset=database.searchProduct(code);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
		}
	}
	public void displayRecords()
	{
		mongoDb=new MongoDB();
		mongoDb.Connection();
		MongoCursor<Document> cursor=mongoDb.displayAll();
		while(cursor.hasNext())
		{
			Document doc=cursor.next();
			System.out.println("Name                       "+doc.getString("name"));
			System.out.println("Phone Number               "+doc.getString("phoneNo"));
			System.out.println("Date                       "+doc.getString("date"));
			List<Integer> arr1=new ArrayList<Integer>();
			List<String> arr2=new ArrayList<String>();
			List<Integer> arr3=new ArrayList<Integer>();
			List<Integer> arr4=new ArrayList<Integer>();
			arr2=doc.getList("productName",String.class);
			arr1=doc.getList("quantity",Integer.class);
			arr3=doc.getList("cost/qty",Integer.class);
			arr4=doc.getList("cost",Integer.class);
			System.out.println("product Name               "+arr2);
			System.out.println("cost/qnt                   "+arr3);
			System.out.println("Quantity                   "+arr1);
			System.out.println("cost                       "+arr4);
			System.out.println("Total amount               "+doc.getInteger("total amount"));
			System.out.println("===============================================================");
			System.out.println();
		}
	}
	public void displayCat()
	{
		scanner=new Scanner(System.in);
		System.out.println("Enter the date in format(dd/mm/yy)");
		String date=scanner.nextLine();
		mongoDb=new MongoDB();
		MongoCursor<Document> cursor=mongoDb.displayDatewise(date);
		boolean flag=true;
		while(cursor.hasNext())
		{
			flag=false;
			Document doc=cursor.next();
			System.out.println("Name                       "+doc.getString("name"));
			System.out.println("Phone Number               "+doc.getString("phoneNo"));
			System.out.println("Date                       "+doc.getString("date"));
			List<Integer> arr1=new ArrayList<Integer>();
			List<String> arr2=new ArrayList<String>();
			List<Integer> arr3=new ArrayList<Integer>();
			List<Integer> arr4=new ArrayList<Integer>();
			arr2=doc.getList("productName",String.class);
			arr1=doc.getList("quantity",Integer.class);
			arr3=doc.getList("cost/qty",Integer.class);
			arr4=doc.getList("cost",Integer.class);
			System.out.println("product Name               "+arr2);
			System.out.println("cost/qnt                   "+arr3);
			System.out.println("Quantity                   "+arr1);
			System.out.println("cost                       "+arr4);
			System.out.println("Total amount               "+doc.getInteger("total amount"));
			System.out.println("===============================================================");
		}
		if(flag)
		{
			System.out.println("No records in such date");
		}
	}
	public void displayCat(String catagory) throws ClassNotFoundException, SQLException
	{
		database=new ProductDb();
		unit_database=new UnitDb();
		resultset=database.displayType(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
		resultset=database.displayName(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
		resultset=unit_database.displayUnit(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5));
		}
	}
}