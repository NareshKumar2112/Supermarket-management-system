package com.market.product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.market.database.Database;
import com.market.database.UnitDb;

public class ProductValidator {

	private UnitDb database;
	
	public boolean isvalidCode(String code)
	{
		if(code.length()>=3&&code.length()<=6)
		{
			return true;
		}
		System.out.println("Enter the valid product code");
		return false;
	}
	public boolean isvalidName(String name)
	{
		if(name.length()>=3&&name.length()<=30)
		{
			return true;
		}
		System.out.println("Enter the valid product name");
		return false;
	}
	public boolean isvalidUnit(String unit) throws ClassNotFoundException, SQLException
	{
		database=new UnitDb();
		if(database.checkUnit(unit))
		{
			return true;
		}
		System.out.println("Enter the valid unit");
		return false;
	}
	public boolean isvalidType(String type)
	{
		String arr[]= {"fruit","vegetable","grosery","others"};
		List<String> lst=Arrays.asList(arr);
		if(lst.contains(type))
		{
			return true;
		}
		System.out.println("Select the valid type from list [fruit,vegetable,grosery,others]");
		return false;
	}
}
