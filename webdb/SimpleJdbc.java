package chapter37;

import java.sql.*;

public class SimpleJdbc
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		// Load the JDBC driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		System.out.println("Driver loaded");

		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl", "scott", "tiger");
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = statement.executeQuery("select firstName, mi, lastName from Student where lastName " + " = 'Smith'");

		// Iterate through the result and print the student names
		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));

		// Close the connection
		connection.close();
	}
}
