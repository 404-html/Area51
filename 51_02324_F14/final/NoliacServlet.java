

import java.io.IOException;
import java.io.PrintWriter;
import javaMeasure.control.DataBaseController;

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
	DataBaseController dbctrl = new DataBaseController();

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
			request.getRequestDispatcher("WEB-INF/form.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("LoginServlet").forward(request, response);;
		}
	}
	
}
