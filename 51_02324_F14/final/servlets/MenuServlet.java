package servlets;

import java.io.IOException;

import javaMeasure.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		//DataBaseController dbctrl = (DataBaseController) request.getSession().getAttribute("database");
		//Login Check
		System.out.println("get request");
		if (request.getSession().getAttribute("user") == null) 
			request.getRequestDispatcher("NoliacServlet").forward(request, response);
		//Button logout pressed
		if (request.getParameter("logout")!= null){
			request.getSession().setAttribute("user", null);
			response.sendRedirect("LoginServlet");
		} else {
			if(request.getParameter("edit")!=null){
				if(((User)request.getSession().getAttribute("user")).isAdmin()){
					request.getRequestDispatcher("UserChooseServlet").forward(request, response);
				}
				else{
					request.getRequestDispatcher("UserEditServlet").forward(request, response);
				}
				
			}else {
				//submitform pressed
				if (request.getParameter("batchNameSubmit")!= null) {
					request.getRequestDispatcher("ReportServlet").forward(request, response);
				}
				else{
					request.getRequestDispatcher("WEB-INF/menu.jsp").forward(request, response);
				}
				
			}
		}
	}

}
