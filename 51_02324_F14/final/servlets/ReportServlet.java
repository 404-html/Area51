package servlets;

import java.io.IOException;

import javaMeasure.Batch;
import javaMeasure.BatchProfile;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public ReportServlet() {
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
		//Login check
		if (request.getSession().getAttribute("user")==null){
			response.sendRedirect("LoginServlet");
		} else {
			//Load batch from database
			String batchName = request.getParameter("selectedbatch");
			DataBaseController dbctrl = (DataBaseController) request.getSession().getAttribute("database");
			Batch batch = null;
			BatchProfile profile = null;
			try {
				batch = dbctrl.getBatch(batchName);
				profile = dbctrl.getBatchProfile(batch.getProfileID());
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
			request.setAttribute("batch", batch);
			request.setAttribute("profile", profile);
			//TODO generate attributes for report....
			request.getRequestDispatcher("WEB-INF/report.jsp").forward(request, response);
		}
	}
	private String[] getReport(Batch batch, BatchProfile profile){
		String[] reportData = new String[100];
		reportData[0] = batch.getBatchString();
		reportData[1] = profile.getProfileSettings().get(7).getValue();
		reportData[2] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(7).getValue()) - Double.parseDouble(profile.getProfileSettings().get(8).getValue()));
		reportData[3] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(7).getValue()) + Double.parseDouble(profile.getProfileSettings().get(8).getValue()));
		reportData[4] = batch.geta
		for(int i=0; i< 3; i++){
			
		}
		return null;
	}

}
