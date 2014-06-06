package servlets;

import java.io.IOException;

import javaMeasure.User;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

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
		//User gets loginpage
		request.getRequestDispatcher("userlogin.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User posts login data
		System.out.println("posting userdata");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + password);
		boolean loginSuccess = false;
		User loginUser = new User(username, 0, password);
		System.out.println(loginUser);
		try {loginSuccess = dbctrl.validateUser(loginUser);
		} catch (DataBaseException e) {		e.printStackTrace();	}
		System.out.println(loginSuccess);
		if (loginSuccess) {
			System.out.println("forwarding");
			request.getSession().setAttribute("user", loginUser);
			request.getSession().setAttribute("database", dbctrl);
			request.getRequestDispatcher("NoliacServlet").forward(request, response);
			System.out.println("forward finished");
		} else {
			request.getRequestDispatcher("userlogin.jsp").forward(request, response);
		}
	}
	

}
