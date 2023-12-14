package com.market.admin;

public class AdminValidator {

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
	public boolean isValidName(String name)
	{
		for(int i=0;i<name.length();i++)
		{
			if(Character.isDigit(name.charAt(i)))
			{
				return false;
			}
		}
		System.out.println("Enter the valid name");
		return true;
	}
}
