package servlets;

import java.io.IOException;

import javaMeasure.User;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.IDatabaseController.UserNotFoundException;

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
		//Check for login
		if (request.getSession().getAttribute("user")!= null){
			response.sendRedirect("MenuServlet");
		} else {
			request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User posts login data
		System.out.println("UserLogin - Post");
		String username = request.getParameter("username");
		if (username != null){
			boolean loginSuccess = false;
			String password = request.getParameter("password");
			System.out.println("username: " + username);
			System.out.println("password: " + password);
			User loginUser = new User(username, 0, password);
			System.out.println(loginUser);
			//Validating user
			try {loginSuccess = dbctrl.validateUser(loginUser);
			} catch (DataBaseException e) {		e.printStackTrace();	
				System.err.println("failed to validateUser in login servlet");
			}
			System.out.println(loginSuccess);
			if (loginSuccess) {
				try {
				User log=dbctrl.getUserFromString(username);
				if(log.isActive()){
				System.out.println("Login success forwarding");
				
					request.getSession().setAttribute("user", log );
				
				request.getSession().setAttribute("database", dbctrl);
				response.sendRedirect("MenuServlet");
				System.out.println("forward finished");
				}
				else{
					request.setAttribute("loginFail", true);
					request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
				}
				
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{}
				}
			else{
				request.setAttribute("loginFail", true);
				request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
			}
				
			
		} else {
			if (request.getParameter("username")  != null) 
				request.setAttribute("loginFail", true);
			request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
		}
	}


}
