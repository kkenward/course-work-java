import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SimpleRegistration extends HttpServlet
{
	private static final long serialVersionUID = 2221946553621449882L;
	// Use a prepared statement to store a student into the database
	private PreparedStatement pstmt;
	private Statement listRecordsStatement;

	/** Initialize variables */
	public void init() throws ServletException
	{
		initializeJdbc();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("GET method not supported - you have to use the form to POST this servlet instead");
	}

	/** Process the HTTP Post request */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Obtain parameters from the client
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String mi = request.getParameter("mi");
		String phone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String address = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");

		try
		{
			if (lastName.length() == 0 || firstName.length() == 0)
			{
				out.println("Last Name and First Name are required");
			}
			else
			{
				storeStudent(lastName, firstName, mi, phone, email, address, city, state, zip);

				out.println(firstName + " " + lastName + " is now registered in the database");
								
				listAllRecords(out);
			}
		}
		catch (Exception ex)
		{
			out.println("Error: " + ex.getMessage());
		}
		finally
		{
			out.close(); // Close stream
		}
	}

	private void listAllRecords(PrintWriter out) throws SQLException
	{
		ResultSet listing = listRecordsStatement.executeQuery("select * from address");
	
		out.println("<h2>Records Currently in the Database:</h2>");
		out.println("<table border=\"1\">");
		while (listing.next())
		{
			out.println("<tr>");
			out.print("<td>"+ listing.getRow() + "</td>");
			for(int i=1;i<10;i++)
			{
				if (listing.getString(i).equals("")) out.print("<td>&nbsp;</td>");
				else out.print("<td>"+listing.getString(i) + "</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
	}

	/** Initialize database connection */
	private void initializeJdbc()
	{
		try
		{
			// Declare driver and connection string
			String driver = "com.mysql.jdbc.Driver";
			String connectionString = "jdbc:mysql://localhost/javabook";

			// Load the driver
			Class.forName(driver);

			// Connect to the sample database
			Connection conn = DriverManager.getConnection(connectionString,"scott","tiger");

			// Create a Statement
			pstmt = conn.prepareStatement("insert into Address " + "(lastName, firstName, mi, telephone, email, street, city, " + "state, zip) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			listRecordsStatement=conn.createStatement();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/** Store a student record to the database */
	private void storeStudent(String lastName, String firstName, String mi, String phone, String email, String address, String city, String state, String zip) throws SQLException
	{
		pstmt.setString(1, lastName);
		pstmt.setString(2, firstName);
		pstmt.setString(3, mi);
		pstmt.setString(4, phone);
		pstmt.setString(5, email);
		pstmt.setString(6, address);
		pstmt.setString(7, city);
		pstmt.setString(8, state);
		pstmt.setString(9, zip);
		pstmt.executeUpdate();
	}
}
