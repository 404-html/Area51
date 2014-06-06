package servlets;

import java.io.IOException;

import javaMeasure.User;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.ISQLConnector.DBConnectFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataBaseController dbctrl = new DataBaseController();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getAttribute("username");
		String password = (String) request.getAttribute("password");
		boolean loginSuccess = false;
		try {
			loginSuccess = dbctrl.validateUser(new User(username, 0, password));
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		if (loginSuccess){
			request.getSession().setAttribute("username", request.getAttribute("username")); 
			request.getRequestDispatcher("NoliacServlet").forward(request, response);
		} else {
			request.setAttribute("loginFail", "true");
			request.getRequestDispatcher("userlogin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
