import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class MyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	{

		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();

		// output your page here
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Hello, I just ran my first Java Servlet!");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
