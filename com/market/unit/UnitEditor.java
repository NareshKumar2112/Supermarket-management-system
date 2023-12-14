package com.market.unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.UnitDb;
import com.market.entity.Unit;

public class UnitEditor {

	private Connection connection;
	private UnitDb database;
	private PreparedStatement prestatement;
	private Scanner s;
	private String name;
	private String code;
	private String details;
	private ResultSet resultset;
	private UnitValidator validator;
	private Unit unit;
	
	public void update() throws ClassNotFoundException, SQLException
	{
		database=new UnitDb();
		s=new Scanner(System.in);
		validator=new UnitValidator();
		System.out.println("update the unit format(name-code)");
		System.out.println("     ->name - text, mandatory with 3 to 30 chars	\r\n"
				+ "     ->code - text, maximum 4 char, mandatory");
		
		details=s.nextLine();
		unit=new Unit();
		String arr[]=details.split("-");
		if(arr.length==2&&validator.isvalidcode(arr[1].trim().toLowerCase())&&validator.isvalidName(arr[0].trim().toLowerCase()))
		{
			unit.setName(arr[0].trim().toLowerCase());
			unit.setCode(arr[1].trim().toLowerCase());
			if(database.checkUnit(unit))
			{
				if(database.editUnit(unit))
				{
					System.out.println("Unit is updated successfully");
				}
				else
				{
					System.out.println("Unit is not updated");
				}
			}
			else
			{
				System.out.println("Unit is not found");
			}	
	}
  }
}
