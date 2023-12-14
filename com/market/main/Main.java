package com.market.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.market.adminService.AdminLogin;
import com.market.purchaseapp.PurchaseLogin;
import com.market.salesapp.SalesLogin;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println();
		System.out.println("-------------------------WELCOME TO SUPER MARKET--------------------");
		System.out.println();
		System.out.println("select your role [Admin,User,Sales Manager,purchase Manager]");
		System.out.println();
		Guide guide=new Guide();
		guide.start();
	}
}