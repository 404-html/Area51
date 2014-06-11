package servlets;

import java.io.IOException;

import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;
import javaMeasure.control.interfaces.IDatabaseController.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserChooseServlet
 */
@WebServlet("/UserChooseServlet")
public class UserChooseServlet extends HttpServlet {
	DataBaseController dbctrl = new DataBaseController();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserChooseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/userchoose.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserChoose - Post");
		String username = request.getParameter("username");
		
		try {
			if(dbctrl.isUserNameInDB(username)){
				request.getSession().setAttribute("usernotfound", null);
				request.getSession().setAttribute("editing", dbctrl.getUserFromString(username));
				request.getRequestDispatcher("WEB-INF/useredit.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("usernotfound", true);
				request.getRequestDispatcher("WEB-INF/userchoose.jsp").forward(request, response);
			}
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
