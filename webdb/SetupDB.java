package chapter37;

import java.sql.*;

public class SetupDB
{
	public static void main(String[] args)
	{
		// Load the JDBC driver
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			
			System.out.println("! - NOT OK - Example did not run.");
			System.out.println("--> To run this, install MySQL and MySQL Connector");
			System.out.println("--> MySQL http://dev.mysql.com/downloads/mysql/");
			System.out.println("--> MySQL Connector:  http://www.mysql.com/downloads/connector/j");
			System.out.println("--> If you are already using another database, see connectors here:  http://www.mysql.com/downloads/connector/");
			return;
			
		}
		System.out.println("OK - Driver loaded!");

		// Establish a connection
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook","scott","tiger");
		}
		catch (SQLException e)
		{
			System.out.println("NOT OK - Your Database said this:  "+e.getMessage());
			System.out.println("--> Make your database happy by setting up user and password");
			System.out.println("--> You may also need to run script.sql file to set up your DB");
			return;
		}
		System.out.println("OK - Database connected");

		// Create a statement
		Statement statement = null;
		try
		{
			statement = connection.createStatement();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Execute a statement
		ResultSet resultSet = null;
		try
		{
			resultSet = statement.executeQuery("select firstName, mi, lastName from Student where lastName " + " = 'Smith'");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Iterate through the result and print the student names
		try
		{
			System.out.println("OK - printint out SQL statement results.  You should see people's names below:\n");
			while (resultSet.next())
				System.out.println("\t"+resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close the connection
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("OK - Test completed successfully - You are able to run and execute SQL statements from Java");
	}
}
