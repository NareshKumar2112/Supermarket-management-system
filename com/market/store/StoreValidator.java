package com.market.store;

public class StoreValidator {

	public boolean isvalidName(String name)
	{
		if(name.length()>=3&&name.length()<=30)
		{
			return true;
		}
		System.out.println("Enter the valid name");
		return false;
	}
	public boolean isvalidPhoneNo(String number)
	{
		if(number.length()==10)
		{
			for(int i=0;i<number.length();i++)
			{
				if(Character.isLetter(number.charAt(i)))
				{
					System.out.println("Enter the valid phone number");
					return false;
				}
			}
			if(number.charAt(0)=='9'||number.charAt(0)=='8'||number.charAt(0)=='7'||number.charAt(0)=='6')
			{
				return true;
			}
			System.out.println("Enter the valid phone number");
			return false;
		}
		return false;
	}
	public boolean isvalidGstNumber(String number)
	{
		if(number.length()==10)
		{
			for(int i=0;i<number.length();i++)
			{
				if(Character.isLetter(number.charAt(i)))
				{
					System.out.println("Enter the valid GST number");
					return false;
				}
			}
			return true;
		}
		System.out.println("Enter the valid GST number");
		return false;
	}
}
