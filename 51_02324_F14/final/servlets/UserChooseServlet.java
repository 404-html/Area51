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
		check(request, response);
	}
	protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(((User) request.getSession().getAttribute("user")) != null){
			if(((User) request.getSession().getAttribute("user")).isActive()){
				if(((User) request.getSession().getAttribute("user")).isAdmin()){
					onpage(request, response);
				}
				else{
					request.getRequestDispatcher("MenuServlet").forward(request, response);
				}
			}	
			else{
				request.getRequestDispatcher("LoginServlet").forward(request, response);
			}
		}
		else{
			request.getRequestDispatcher("LoginServlet").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		check(request, response);
		
	}
	protected void onpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserChoose - Post");
		String chosen = request.getParameter("chosen");
		System.out.println(chosen);
		if(request.getParameter("Done")!=null){
			request.getRequestDispatcher("MenuServlet").forward(request, response);
		}
		else{
		try {
			if(dbctrl.isUserNameInDB(chosen)){
				System.out.println("editing "+chosen);
				request.getSession().setAttribute("usernotfound", null);
				request.getSession().setAttribute("editing", dbctrl.getUserFromString(chosen));
				request.getRequestDispatcher("UserEditServlet").forward(request, response);
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

}
