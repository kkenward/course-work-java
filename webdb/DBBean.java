package myweb;

import java.sql.*;

public class DBBean
{
	private Connection connection = null;
	private String username;
	private String password;
	private String driver;
	private String url;

	/** Initialize database connection */
	public void initializeJdbc()
	{
		try
		{
			System.out.println("Driver is " + driver);
			Class.forName(driver);

			// Connect to the sample database
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/** Get tables in the database */
	public String[] getTables()
	{
		String[] tables = null;

		try
		{

			DatabaseMetaData dbMetaData = connection.getMetaData();
			ResultSet rsTables = dbMetaData.getTables(null, null, null, new String[] { "TABLE" });
			int size = 0;
			while (rsTables.next())	size++;
			rsTables = dbMetaData.getTables(null, null, null, new String[] { "TABLE" });
			tables = new String[size];
			int i = 0;
			while (rsTables.next()) tables[i++] = rsTables.getString("TABLE_NAME");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return tables;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	public void setDriver(String driver)
	{
		this.driver = driver;
	}

	public String getDriver()
	{
		return driver;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	public Connection getConnection()
	{
		return connection;
	}

}
