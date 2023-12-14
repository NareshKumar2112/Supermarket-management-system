package com.market.userapp;

public class Validator {

	public boolean isName(String name)
	{
		for(int i=0;i<name.length();i++)
		{
			if(!Character.isLetter(name.charAt(i))&&name.charAt(i)!=' ')
			{
				System.out.println("Enter the valid name");
				return false;
			}
		}
		return true;
	}
	public boolean isvalidPhoneNo(String phoneNo)
	{
		if(phoneNo.length()==10)
		{
			for(int i=0;i<phoneNo.length();i++)
			{
				if(!Character.isDigit(phoneNo.charAt(i)))
				{
					System.out.println("Enter the valid phone number");
					return false;
				}
			}
			return true;
		}
		System.out.println("Enter the valid phone number");
		return false;
	}
}
