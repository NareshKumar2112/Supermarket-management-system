package com.market.unit;

public class UnitValidator {

	public boolean isvalidName(String name)
	{
		if(name.length()>=3&&name.length()<=30)
		{
			return true;
		}
		System.out.println("Enter the valid unit name");
		return false;
	}
	public boolean isvalidcode(String code)
	{
		if(code.length()<=4)
		{
			return true;
		}
		System.out.println("Enter the valid unit code");
		return false;
	}
}
