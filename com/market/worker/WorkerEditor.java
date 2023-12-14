package com.market.worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.market.database.Database;
import com.market.database.WorkerDb;
import com.market.entity.Worker;

public class WorkerEditor {

	private Connection connection;
	private WorkerDb database;
	private Worker worker;
	private PreparedStatement prestatement;
	private String details;
	private Scanner s;
	private String userType;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private ResultSet resultset;
	private WorkerValidator validator;
	
	public void edit() throws ClassNotFoundException, SQLException
	{
		s=new Scanner(System.in);
		database=new WorkerDb();
		worker=new Worker();
		validator=new WorkerValidator();
		System.out.println("Enter the user details format(usertype-username-password-firstname-lastname-phonenumber)");
		System.out.println("	->usertype - text, purchase/sales, mandatory\r\n"
				+ "	->username - text, min 3 - 30 char, mandatory\r\n"
				+ "	->password - text, alphanumeric, special char, min 8 char, mandatory\r\n"
				+ "	->firstname - text, mandatory with 3 to 30 chars\r\n"
				+ "	->lastname  - text, optional\r\n"
				+ "	->phone - number, mandatory, ten digits, digit start with 9/8/7/6	");
		details=s.nextLine();
		String arr[]=details.split("-");
		if(arr.length==6&&validator.isvalidFirstname(arr[3])&&validator.isvalidpassword(arr[2])&&validator.isvalidPhoneNo(arr[5])&&validator.isvalidUserName(arr[1])&&validator.isvalidUserType(arr[0]))
		{
			worker.setUserType(arr[0]);
			worker.setUserName(arr[1]);
			worker.setPassowrd(arr[2]);
			worker.setFirstName(arr[3]);
			worker.setLastName(arr[4]);
			worker.setPhoneNo(arr[5]);
			if(database.checkWorker(worker))
			{
				if(database.editWorker(worker))
				{
					System.out.println("worker is updated successfully");
				}
				else
				{
					System.out.println("worker is not updated");
				}
			}
			else
			{
				System.out.println("Worker is not found");
			}
		}
		else
		{
			System.out.println("Enter the correct format");
		}
	}
}
