package com.BankManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.xml.transform.Result;
import java.util.Scanner;

public class User {

	private Connection connection;
	private Scanner scanner;
	
	public User(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
//	For ragister the new customer
	public void register() {
//		Gives input from the user 
		scanner.nextLine();
		System.out.print("Full Name: ");
		String full_name=scanner.nextLine();
		
		System.out.print("Email: ");
		String email=scanner.nextLine();
		
		System.out.print("Password: ");
		String password=scanner.nextLine();
		
//		to check user the email is alredy present in database or not
		if(user_exist(email))  {
			System.out.println("User Already Exists for this Email Address...");
			return;
		}
		
//		if the it's not present or it's new the this code will work  
//		query: getting input from user(fullname,email,pass)
		String register_query="INSERT INTO User(full_name, email,password) VALUES(?,?,?)";
		try {
//			insert the three function usinf PreparedStatement
			PreparedStatement preparedStatement=connection.prepareStatement(register_query);
//			Set the values and Placeholder number 
			preparedStatement.setString(1, full_name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
//			executeUpdate: For inserting data in database, excuteQuery:For retriving data  
			int affectedRows=preparedStatement.executeUpdate();
//			Check for affected rows
			if(affectedRows>0) {
				System.out.println("Registration Successfull...");
			}
			else {
				System.out.println("Registration Failed...");
			}
		}
//		Use of try catch block to handle the PreparedStatement
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//	For Login the new our customer
//	String: once the user will get login then we will share that
//	email througth the program with other function.
	public String login() {
		scanner.nextLine();
//		Gives Email & Passqord from the user
		System.out.print("Email: ");
		String email=scanner.nextLine();
		
		System.out.print("Password: ");
		String password=scanner.nextLine();
		
//		Checking those login details in database 
		String login_query="SELECT *FROM User WHERE email=? AND password=?";
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(login_query);
//			Set the inputs
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
//			ResultSet: For Holding the data
			ResultSet resultSet=preparedStatement.executeQuery();// for Retriving the data
//			if condtin: For checking perpose that given request is macth with database or not
			if(resultSet.next()) {
//				if given information match the return email
				return email;
			}
			else {
//				otherwise return null(Inforamation does't match)
				return null;
			}
		}
//		catch tha given exception
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	For checking perpose to perticular user are exist in our database or not
	public boolean user_exist(String email) {//get email
//		Check the email in table
		String query="SELECT * FROM User WHERE email= ?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet=preparedStatement.executeQuery();
//			check that email if come data related to database than return true
			if(resultSet.next()) {
				return true;
			}
//			Otherwise return false
			else {
				return false;
			}
		}
//		handling exception
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
