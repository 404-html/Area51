

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoliacServlet
 */
@WebServlet("/NoliacServlet")
public class NoliacServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NoliacServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("login") != null) {
			request.getRequestDispatcher(request.getPathInfo()).forward(request, response);
		} else {
		    PrintWriter out = response.getWriter();
		    out.println(request.getPathInfo());
			//request.getRequestDispatcher("\\userlogin.jsp").forward(request, response);
		}
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head><title>Syvtabellen - fra en servlet</title></head>");
	    out.println("<body>");
	    out.println(request.getPathInfo() + "<br>");
	    out.println("<p>Her er syv-tabellen:<br>");
	 
	    for (int i=1; i<=10; i++)
	    {
	      out.println("Syv gange "+ i +" er: "+ 7*i +".<br>");
	    } 
	    out.println("</body>");
	    out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
