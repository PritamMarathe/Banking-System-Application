package com.BankManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//import static java.lang.Class.forName;

public class BankingAppl {

//	Varialbles For Database Creadantial
//	(Private: Because of Security Perposes)
//	(Static: Because no need to create Object to this class)
//	(Final: Because whatever value have there it will be fix throught preogram)
	private static final String url="jdbc:mysql://localhost:3306/BankingSystemAppl";
	private static final String username="root";
	private static final String password="6282";
	
//	main method & handling exception
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		System.out.println("Hello");
		
//		Load the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		
		}
//		Establish the connection with the help of getConnection method(It's geting 3 three arguments)
//		And then establish connection with database
		try {
			Connection connection=DriverManager.getConnection(url, username,password);
//			Geting input from user and We are connection with our three class 
			Scanner scanner=new Scanner(System.in);
//			It's Object of our 3 class & create a constractor pass the instancec parameter in connection,scanner
			User user=new User(connection,scanner);
			Acccounts accounts=new Acccounts(connection,scanner);
			AccountManager accountManager=new AccountManager(connection,scanner);
			
			String email;
			long account_number;
			
//			For Ask User Which task they want to perform
			while(true)  {
				System.out.println("****Welcome to Banking System***");
				System.out.println();
				System.out.println("1.Register");
				System.out.println("2.Login");
				System.out.println("3.Exist");
				System.out.println("Enter the Choice: ");
				int choice1=scanner.nextInt();
				
				switch (choice1) {
//				IF user User press 1 for the Register
				case 1:
					user.register();
					break;

				case 2:
//					If User press 2 for the Login
					email=user.login();
					if(email!=null) {
						System.out.println("User Logged In!");
						if(!accounts.account_exist(email))  {
							System.out.println();
//							For Other Service user want Account. So thats reason we want Account in database for perform other Activits
							System.out.println("1. Open a new Bank Account");
							System.out.println("2. Exist");
							if(scanner.nextInt() == 1)  {
								account_number=accounts.open_account(email);
								System.out.println("Account Created Successfully....");
								System.out.println("Your Account Number is: "+account_number);
							}
							else {
								break;
							}
						}
// 						After User Succefully Created Account. We have those Service for the User Choses Service 
						account_number=accounts.getAccount_number(email);
						int choice2=0;
						while(choice2!=5)  {
							System.out.println("1.Debit Money");
							System.out.println("2.Credit Money");
							System.out.println("3.Transfer Money");
							System.out.println("4.Check Balance");
							System.out.println("5.Log Out");
							System.out.println("Enter Your Choice: ");
							
							choice2=scanner.nextInt();
							switch(choice2)  {
							case 1:
								accountManager.dabit_money(account_number);
								break;
							case 2:
								accountManager.credit_money(account_number);
								break;
							case 3:
								accountManager.transfer_money(account_number);
								break;
							case 4:
								accountManager.getBalance(account_number);
								break;
							case 5:
								break;
							default:
								System.out.println("Enter the Valid Choice...");
								break;
							}
						}
					} else {
						System.out.println("Incorrect Email or Password!");
					}
					
//					If user want to Exist 
				case 3:
					System.out.println("THANK YOU FOR USING PRITAM BANKING SYSTEM...🙏🙏🙏  VISIT AGAIN");
					System.out.println("Existing System...!");
					return;
					
//					If User Not Chooses Releted Option then Print this msg
				default:
					System.out.println("Enter the Valid Choice");
					break;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
