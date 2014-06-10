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
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {

	DataBaseController dbctrl = new DataBaseController();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/useredit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User posts edit
		System.out.println("UserLogin - Post");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String active = request.getParameter("active");
		String admin = request.getParameter("admin");

		
		try {
			if (dbctrl.isUserNameInDB(username)) {
				request.setAttribute("editfail", null);
				request.setAttribute("edited", true);
				System.out.println("forwarding");
				request.getRequestDispatcher("").forward(request, response);
				System.out.println("WEB-INF/useredit.jsp");
			} else {
				if (request.getParameter("username")  != null) 
					request.setAttribute("editfail", true);
					request.setAttribute("edited", null);
				request.getRequestDispatcher("WEB-INF/useredit.jsp").forward(request, response);
			}
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
