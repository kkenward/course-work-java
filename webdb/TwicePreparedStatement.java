package chapter37;

import java.sql.*;

public class TwicePreparedStatement
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		System.out.println("Twice use prepared statement example!\n");
		Connection con = null;
		PreparedStatement ps;

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql:localhost:3306/jdbctutorial", "root", "root");
		String sql = "SELECT * FROM movies WHERE year_made = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, 2002);

		ResultSet rs1 = ps.executeQuery();
		System.out.println("List of movies that made in year 2002");

		while (rs1.next())
		{
			String mov_name = rs1.getString(1);
			int mad_year = rs1.getInt(2);
			System.out.println(mov_name + "\t- " + mad_year);
		}

		ps.setInt(1, 2003);
		ResultSet rs2 = ps.executeQuery();
		System.out.println("List of movies that made in year 2003");

		while (rs2.next())
		{
			String mov_name = rs2.getString(1);
			int mad_year = rs2.getInt(2);
			System.out.println(mov_name + "\t- " + mad_year);
		}

	}
}