import java.io.IOException;
import java.io.PrintWriter;

import javaMeasure.control.DataBaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaMeasure.User;

/**
 * Servlet implementation class NoliacServlet
 */
@WebServlet("/NoliacServlet")
//Front controller - Handles all requests.
public class NoliacServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public NoliacServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReq(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReq(request, response);
	}

	private void processReq(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null){ 
			// user is logged in
			String cmd = request.getParameter("cmd");
			if (cmd == null)
				cmd = "";
			switch (cmd) {
			case "report":
				request.getRequestDispatcher("ReportServlet").forward(request, response);
				break;
			case "admin":
				request.getRequestDispatcher("UserEditServlet").forward(request, response);
				break;
			case "logout":
				request.setAttribute("user", null);
				request.getRequestDispatcher("LoginServlet").forward(request, response);
				break;
			case "tryLogin":
				request.getRequestDispatcher("MenuServlet").forward(request, response);
				break;
			case "":
				request.getRequestDispatcher("MenuServlet").forward(request, response);
				break;
			default:
				request.getRequestDispatcher(request.getParameter("cmd")).forward(request, response);
				break;
			}
		} else { //user not logged in
			request.getRequestDispatcher("LoginServlet").forward(request, response);;
		}
	}

}
