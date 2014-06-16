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
		check(request, response);
	}
	protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(((User) request.getSession().getAttribute("user")) != null){
			if(((User) request.getSession().getAttribute("user")).isActive()){
				if(((User) request.getSession().getAttribute("user")).isAdmin()){
					onPage(request, response);
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
	protected void onPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User posts edit
		System.out.println("UserLogin - Post");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String active = request.getParameter("active");
		String admin = request.getParameter("admin");
		boolean ad = true;
		if(admin==null){
			ad=false;
		}
		boolean ac = true;
		if(active==null){
			ac=false;
		}
		if((request.getParameter("Save")!=null)){
			try {
	
					User u = (User)request.getSession().getAttribute("editing");
					if(!(u.isActive()&&u.isAdmin())||(u.isActive()&&u.isAdmin()&&(dbctrl.canWeRemoveAnotherAdmin()||(ac&&ad)))){
						u.setUserName(name);
						u.setPassWord(password);
						u.setActive(ac);
						u.setAdmin(ad);
						dbctrl.updateUser(u);
						request.setAttribute("editfail", null);
						request.setAttribute("edited", true);
						if(((User)request.getSession().getAttribute("user")).getUserID()==u.getUserID()){
							
							if(ac){
							
								request.getSession().setAttribute("user", u);
								request.getRequestDispatcher("UserChooseServlet").forward(request, response);
							}
							else{
								request.getSession().setAttribute("user", null);
								request.getRequestDispatcher("LoginServlet").forward(request, response);
							}
							
						}
						else{
						System.out.println("forwarding");
						request.getRequestDispatcher("UserChooseServlet").forward(request, response);
						}
					}
					else{
						System.out.println("I can't let you remove the last admin, " +request.getSession().getAttribute("username")+".");
						request.getRequestDispatcher("WEB-INF/useredit.jsp").forward(request, response);
					}
					
			} catch (DataBaseException e) {
				request.getRequestDispatcher("WEB-INF/useredit.jsp").forward(request, response);
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
		else if(request.getParameter("Done")!=null){
			request.getRequestDispatcher("MenuServlet").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/useredit.jsp").forward(request, response);
		}
	}

}
