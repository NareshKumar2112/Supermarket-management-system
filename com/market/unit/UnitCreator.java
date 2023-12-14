package com.market.unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.StoreDb;
import com.market.database.UnitDb;
import com.market.entity.Store;
import com.market.entity.Unit;

public class UnitCreator {

	private Connection connection;
	private UnitDb database;
	private PreparedStatement prestatement;
	private Unit unit;
	private String name;
	private String code;
	private String details;
	private Scanner s;
	private UnitValidator validator;
	
	public void create() throws ClassNotFoundException, SQLException
	{
		database=new UnitDb();
		s=new Scanner(System.in);
		unit =new Unit();
		validator=new UnitValidator();
		try
		{
				System.out.println("Create your unit (format->name-code)");
				System.out.println("     ->name - text, mandatory with 3 to 30 chars \r\n"
						+ "     ->code - text, maximum 4 char, mandatory");
				
				details=s.nextLine();
				String arr[]=details.split("-");
				
				if(arr.length==2&&validator.isvalidcode(arr[1].trim().toLowerCase())&&validator.isvalidName(arr[0].trim().toLowerCase()))
				{	
					unit.setName(arr[0].trim().toLowerCase());
					unit.setCode(arr[1].trim().toLowerCase());
					if(!database.checkUnit(unit))
					{
						if(database.createUnit(unit))
						{
							System.out.println("Unit is created successfully");
						}
						else
						{
							System.out.println("Unit is not created");
						}
					}
					else
					{
						System.out.println("unit is already exists");						
					}
				}
				else
				{
					System.out.println("Data is invalid");
				}
			
		}
		catch(Exception e)
		{
			System.out.println("unitCreator->"+e);
		}
	}
}
