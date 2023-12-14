package com.market.userapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.bson.Document;
import com.market.adminService.ProductService;
import com.market.database.Database;
import com.market.database.MongoDB;
import com.market.database.ProductDb;
import com.market.database.UnitDb;
import com.market.entity.Shop;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class UserService {

	private ProductDb database;
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private ProductService productService;
	private Scanner scanner;
	private int totalAmt;
	private MongoDB mongoDb;
	private MongoCollection<Document> collection;
	private String name;
	private String phoneNo;
	private Validator validator;
	private String date;
	private Shop shop;
	private UnitDb unitdb;
	
	public void display() throws ClassNotFoundException, SQLException
	{
		productService=new ProductService();
		productService.list();
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
		resultset=database.displayName(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
		resultset=unitdb.displayUnit(catagory);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"     "+resultset.getString(2)+"       "+resultset.getString(3)+"        "+resultset.getString(4)+"         "+resultset.getInt(5)+"        "+resultset.getInt(6));
			
		}
		
	}
	public boolean shopping() throws ClassNotFoundException, SQLException
	{
		mongoDb=new MongoDB();
		shop=new Shop();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		date=dtf.format(now);  
		database=new ProductDb();
		scanner=new Scanner(System.in);
		System.out.println("Enter details format(name-phone number)");
		String detail=scanner.nextLine();
		String arr[]=detail.split("-");
		validator=new Validator();
		totalAmt=0;
		if(arr.length==2)
		{
			name=arr[0];
			phoneNo=arr[1];
			if(validator.isName(name)&&validator.isvalidPhoneNo(phoneNo))
			{
				System.out.println("Products that are available");
				System.out.println("---------------------------");
				System.out.println();
				UserService user=new UserService();
				user.display();
				System.out.println();
				
				System.out.println("Enter the productcode - quantity,productcode-quantity...");
				String products=scanner.nextLine();
				String array[]=products.split("[-,]");
				HashMap<String ,Integer> map=new HashMap<String,Integer>();
				if(array.length%2==0)
				{
					int i=1;
					while(i<array.length)
					{
						map.put(array[i-1],Integer.parseInt(array[i]));
						i=i+2;
					}
				}
				else
				{
					System.out.println("Enter the correct format");
					return false;
				}
				ArrayList<Integer> arr1=new ArrayList<Integer>();
				ArrayList<String> arr2=new ArrayList<String>();
				ArrayList<Integer> arr3=new ArrayList<Integer>();
				ArrayList<Integer> arr4=new ArrayList<Integer>();
				for(Map.Entry<String,Integer> m:map.entrySet())
				{
					String item=m.getKey();
					int qnt=m.getValue();
					int cost=0;
					resultset=database.checkStock(qnt,item);
					boolean flag=false;
					while(resultset.next())
					{
						flag=true;
						cost=resultset.getInt(5)*qnt;
						totalAmt+=cost;
						int stock=resultset.getInt(6);
						int sell=resultset.getInt(7);
						database.updateStockProduct(qnt, item,stock,sell);
						arr1.add(qnt);
						arr2.add(resultset.getString(2));
						arr3.add(cost);
						arr4.add(resultset.getInt(5));
					}
					if(!flag)
					{
						System.out.println("No stock");
						return false;
					}
				}
				shop.setName(arr[0].trim().toLowerCase());
				shop.setPhoneNo(arr[1].trim().toLowerCase());
				shop.setProductName(arr2);
				shop.setCost(arr3);
				shop.setCost_qnt(arr4);
				shop.setQuantity(arr1);
				shop.setTotal(totalAmt);
				shop.setDate(date);
				mongoDb.shoppingRecords(shop);
				}
			else
			{
				System.out.println("Enter the correct format");
				return  false;
			}
		}
		else
		{
			System.out.println("Enter the correct format");
			return  false;
		}
		return true;
	}
	public void billGenerate()
	{
		System.out.println("Here is your bill");
		mongoDb=new MongoDB();
		MongoCursor<Document> cursor=mongoDb.displayBill(name.toLowerCase().trim(),date.toLowerCase().trim());
		while(cursor.hasNext())
		{
			Document doc=cursor.next();
			System.out.println("Name                       "+doc.getString("name"));
			System.out.println("Phone Number               "+doc.getString("phoneNo"));
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
			System.out.println("Total amount               "+doc.getInteger("total amount")+"-Rs");
		}
	}
	public void showRecords()
	{
		validator=new Validator();
		scanner=new Scanner(System.in);
		System.out.println("Enter the name and phoneNo format(name-phoneNumber)");
		String details=scanner.nextLine();
		String arr[]=details.split("-");
		if(arr.length==2&&validator.isName(arr[0].trim().toLowerCase())&&validator.isvalidPhoneNo(arr[1].trim().toLowerCase()))
		{
			
			name=arr[0].toLowerCase().trim();
			phoneNo=arr[1].toLowerCase().trim();
			mongoDb=new MongoDB();
			boolean flag=true;
			MongoCursor<Document> cursor=mongoDb.displayIndividual(name,phoneNo);
			while(cursor.hasNext())
			{
				Document doc=cursor.next();
				flag=false;
				System.out.println("Name                       "+doc.getString("name"));
				System.out.println("Phone Number               "+doc.getString("phoneNo"));
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
				System.out.println("Total amount               "+doc.getInteger("total amount")+"-Rs");
				System.out.println("================================================================");
				System.out.println();
			}
			if(flag)
			{
				System.out.println("No record found");
			}
		}
		else
		{
			System.out.println("Enter the correct details");
		}
	}
}
