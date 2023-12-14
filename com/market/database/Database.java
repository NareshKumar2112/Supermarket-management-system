package com.market.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.market.entity.Admin;
import com.market.entity.Product;
import com.market.entity.Store;
import com.market.entity.Unit;
import com.market.entity.Worker;
import com.market.store.StoreValidator;

public class Database {

	
	private Connection connection;

	public Connection connect() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket","root","adhav1405");
		return connection;
	}
}
