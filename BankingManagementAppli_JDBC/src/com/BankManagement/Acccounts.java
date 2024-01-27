package com.BankManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Acccounts {

	private Connection connection;
	private Scanner scanner;
	
	public Acccounts(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
//	Gives Information from the User & make Account As per details
	public long open_account(String email) {
		if(!account_exist(email))  {
			String open_account_query="INSERT INTO Accounts(account_number, full_name, email, balance, security_pin) VALUES(?,?,?,?,?)";
			scanner.nextLine();
			
			System.out.print("Enter the Full Name: ");
			String full_name=scanner.nextLine();
			
			System.out.print("Enter the Initial Amount: ");
			double balance=scanner.nextDouble();
			scanner.nextLine();
			
			System.out.print("Enter The Secutity Pin: ");
			String security_pin=scanner.nextLine();
			try {
				long account_number=generateAccountNumber();
				PreparedStatement preparedStatement=connection.prepareStatement(open_account_query);
				preparedStatement.setLong(1, account_number);
				preparedStatement.setString(2, full_name);
				preparedStatement.setString(3, email);
				preparedStatement.setDouble(4, balance);
				preparedStatement.setString(5, security_pin);
				int rowsAffected=preparedStatement.executeUpdate();
				
				if(rowsAffected > 0) {
					return account_number;
				}
				else {
					throw new RuntimeException("Account Creation Failed...");
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("Account Already Exist...");
	}
	
//	For get the Account Number
	public long getAccount_number(String email) {
		String query="SELECT account_number from Accounts WHERE email=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())  {
				return resultSet.getLong("account_number");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Account Number Doesn't Exist...");
	}
	
//	Generate Account Number and make Private
	private long generateAccountNumber()  {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet=statement.executeQuery("SELECT account_number from Accounts ORDER BY account_number DESC LIMIT 1");
			if(resultSet.next())  {
				long last_account_number=resultSet.getLong("account_number");
				return last_account_number+1;
			}
			else {
//				Acc Start with this series
				return 100010;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 100010;
	}
	
	
	public  boolean account_exist(String email) { //get email from user & check it's alredy exist in database or not
		String query="SELECT account_number from Accounts WHERE email=?";
		try {
//			Retrive the data
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet=preparedStatement.executeQuery();
//			if data is come then
			if(resultSet.next())  {
//				return true
				return true;
			}
			else {
//				Otherwise
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
