package com.market.worker;

public class WorkerValidator {

	public boolean isvalidUserType(String type)
	{
		if(type.toLowerCase().trim().equals("purchase")||type.toLowerCase().trim().equals("sales"))
		{
			return true;
		}
		System.out.println("Enter the valid type");
		return false;
	}
	
	public boolean isvalidUserName(String username)
	{
		if(username.length()>=3&&username.length()<=30)
		{
			return true;
		}
		System.out.println("Enter the valid username");
		return false;
	}
	
	public boolean isvalidpassword(String password)
	{
		if(password.length()>=8)
		{
			return true;
		}
		System.out.println("Enter the valid password");
		return false;
	}
	public boolean isvalidFirstname(String name)
	{
		if(name.length()>=3&&name.length()<=30)
		{
			return true;
		}
		System.out.println("Enter the valid name");
		return false;
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
