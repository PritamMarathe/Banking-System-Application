# Banking-System-Application
It's Application of Banking System
<br>

<h2>Description About the Project :- </h2>
<h3>Banking Appl Class:</h3>
<br>
 It's  main application class "BankingAppl", which serves as the entry point and orchestrates various user interactions within the banking management system. It includes functionalities such as user registration, login, and managing bank accounts. Here's a brief overview of the main functionalities in the BankingAppl class:-

1.  Database Connection:-
   - It establishes the connection with the predefined database using JDBC and the provided credentials.
   - Loads the MySQL JDBC driver using `Class.forName`.
   - Uses DriverManager to establish a connection to the database.

2.  User Interaction Loop:-
   - After establishing the database connection, it creates instances of the User, Accounts, and AccountManager classes, passing the database connection and scanner object.
   - It presents a menu to the user, allowing them to register, log in, or exit the banking system.

3.  User Registration/Login:-
   - If the user chooses to register, it invokes the register method from the User class.
   - If the user chooses to log in, it uses the login method from the User class to authenticate the user.

4.  Account Management:-
   - Once a user is logged in, it provides options to manage the user's bank account such as debiting money, crediting money, transferring money, and checking the account balance.
   - It interacts with the AccountManager class to perform these account-related operations.

5.  Exiting the System:-
   - If the user chooses to exit, it terminates the application with a farewell message.

  It's crucial to ensure that the program handles user inputs effectively, manages exceptions appropriately, and maintains the security of sensitive user information throughout the interactions.


<h3>User Class:-</h3>
<br>
 The  User class in a banking management application. This class contains methods for user registration, login, and checking if a user exists in the database. The code uses JDBC to interact with the database and handle user-related operations.

Here's a brief overview of the functionality provided in the User class:

1.  User Registration (register method):-
   - It prompts the user to input their full name, email, and password.
   - It checks if the user already exists in the database based on the input email.
   - If the user does not exist, it constructs a SQL INSERT query to add the user's information to the database.
   - It uses PreparedStatement to securely execute the INSERT query and informs the user about the success or failure of the registration process.

2.  User Login (login method):-
   - It prompts the user for their email and password.
   - It constructs a SQL SELECT query to check if the provided email and password match the database records.
   - It uses PreparedStatement to execute the SELECT query and returns the email if the credentials match, otherwise, it returns null.

3.  User Existence Check (user_exist method):-
   - It takes an email as input and constructs a SQL SELECT query to check if the email exists in the database.
   - It uses PreparedStatement to execute the SELECT query and returns true if the email exists, otherwise, it returns false.

Overall, the User class provides essential functionality for user management within a banking application. It handles user registration, login authentication, and user existence checks via interaction with the underlying database.


<h3>AcccountsClass:-</h3><br>
 It is a part of a Java program for a bank account management system Application. It includes a class called  Acccounts  with several methods for account management. Here's a breakdown of the main elements:

1. User OpenAccount:- <br>
   -The open_account method takes user input for full name, initial balance, and security pin to create a new account in the database. <br>
   -It checks if the account already exists for the given email and, if not, generates a unique account number and inserts the account details into the database.

2.  For Get Account Number:-<br>
   -The getAccount_number method retrieves the account number associated with a given email from the database.

3. For Generate Account Number:-<br>
   -The generateAccountNumber method generates a unique account number for a new account by querying the database for the last account number and incrementing it.

4. Exist Account:-<br>
   -The account_exist method checks if an account already exists for the given email in the database.

The class uses JDBC (Java Database Connectivity) to interact with the database, and it takes a Connection and Scanner object as parameters in the constructor for database connection and user input handling.

Overall, this Java class provides functionality to open new accounts, retrieve account numbers, and check for existing accounts in a bank management system.  


<h3>Account Manager Class:-</h3><br>
 It is responsible for managing bank account transactions such as crediting, debiting, transferring money between accounts, and checking the account balance.

The class contains methods for various banking operations:
1.  credit_money:- <br>
   -This method allows the user to deposit money into their account. It prompts the user to enter the amount to be credited and the security pin. <br>
   -It then verifies the account and pin, and if successful, updates the account balance by the credited amount.

2.  dabit_money:-<br>
   -This method facilitates the withdrawal of money from the account. <br>
   -It asks for the amount to be debited and the security pin, verifies the account and pin, checks for sufficient balance, and updates the account balance if the conditions are met.

3.  transfer_money:-<br>
   -This method enables the transfer of money from one account to another. It prompts the user to enter the receiver's account number, the amount to be transferred, and the security pin. <br>
   -The method then verifies the sender's account and pin, checks for sufficient balance, and if all conditions are met, transfers the specified amount from the sender's account to the receiver's account.

4.  getBalance:- <br>
   -This method allows the user to check the account balance.<br>
   - It prompts the user to enter the security pin, verifies the account and pin, and if successful, retrieves and displays the account balance.

The class uses SQL queries to interact with the database for account verification and balance updates. It utilizes prepared statements to prevent SQL injection and uses transaction management (commit and rollback) to ensure the atomicity of transactions.

Overall, the `AccountManager` class provides a comprehensive set of functionalities for managing bank account transactions with proper security checks and database interaction.  

<br>
Author Name:- Pritam Marathe
