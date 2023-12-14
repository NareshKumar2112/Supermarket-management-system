package com.market.unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.UnitDb;
import com.market.entity.Unit;

public class UnitEliminator {


	private Connection connection;
	private PreparedStatement prestatement;
	private UnitDb database;
	private String acknowledge;
	private Scanner s;
	private String unitCode;
	private ResultSet resultset;
	private Unit unit;
	
	public void delete() throws ClassNotFoundException, SQLException
	{
		unit=new Unit();
		database=new UnitDb();
		s=new Scanner(System.in);
		System.out.println("Delete the unit ,Enter the unitcode");
		unitCode=s.nextLine();
		unit.setCode(unitCode);
		System.out.println("Enter 'yes' to confirm");
		acknowledge=s.next();
		if(acknowledge.equals("yes"))
		{
			if(database.checkUnit(unit))
			{
			     if(database.deleteUnit(unit))
			     {
			    	 
			     }
			}
			else
			{
				System.out.println("Unit not found");
			}
		}
		
	}

}
