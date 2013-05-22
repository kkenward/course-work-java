<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="myweb.DBBean"%>
<jsp:useBean id="dBBeanId" scope="session" class="myweb.DBBean">
</jsp:useBean>

<jsp:setProperty name="dBBeanId" property="driver" value="com.mysql.jdbc.Driver" />
<jsp:setProperty name="dBBeanId" property="url" value="jdbc:mysql://localhost/javabook" />
<jsp:setProperty name="dBBeanId" property="username" value="scott" />
<jsp:setProperty name="dBBeanId" property="password" value="tiger" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DBLoginInitialization</title>
</head>
<body>

<%
	dBBeanId.initializeJdbc();

	PreparedStatement pstmt = dBBeanId.getConnection().prepareStatement("insert into Address (lastName, firstName, mi, telephone, email, street, city, state, zip) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	pstmt.setString(1, request.getParameter("lastName"));
	pstmt.setString(2, request.getParameter("firstName"));
	pstmt.setString(3, request.getParameter("mi"));
	pstmt.setString(4, request.getParameter("phone"));
	pstmt.setString(5, request.getParameter("email"));
	pstmt.setString(6, request.getParameter("address"));
	pstmt.setString(7, request.getParameter("city"));
	pstmt.setString(8, request.getParameter("state"));
	pstmt.setString(9, request.getParameter("zip"));
	pstmt.executeUpdate();
%>


<table border="1">

	<%
		Statement statement = dBBeanId.getConnection().createStatement();
		ResultSet listing = statement.executeQuery("select * from address");
		int columnCount = listing.getMetaData().getColumnCount();

		while (listing.next())
		{
			out.println("<tr>");
			out.print("<td>" + listing.getRow() + "</td>");
			for (int i = 1; i <= listing.getMetaData().getColumnCount(); i++)
			{
				System.out.print(i+" ");
				System.out.println(listing.getString(i));
				if (listing.getString(i)!=null && listing.getString(i).equals("")) 
					out.print("<td>&nbsp;</td>");
				else 
					out.print("<td>" + listing.getString(i) + "</td>");
			}
			out.println("</tr>");
		}
	%>

</table>




</body>
</html>