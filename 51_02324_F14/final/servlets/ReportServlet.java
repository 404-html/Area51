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
		// length row
		reportData[0] = batch.getBatchString();
		reportData[1] = profile.getProfileSettings().get(7).getValue();
		reportData[2] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(7).getValue()) - Double.parseDouble(profile.getProfileSettings().get(8).getValue()));
		reportData[3] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(7).getValue()) + Double.parseDouble(profile.getProfileSettings().get(8).getValue()));
		reportData[4] = profile.getProfileSettings().get(9).getValue();
		reportData[5] = null; // do not have the needed info
		reportData[6] = null; // do not have the needed info
		reportData[7] = null; // do not have the needed info
		
		// width row
		reportData[8] = profile.getProfileSettings().get(10).getValue();
		reportData[9] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(10).getValue()) - Double.parseDouble(profile.getProfileSettings().get(11).getValue()));
		reportData[10] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(10).getValue()) + Double.parseDouble(profile.getProfileSettings().get(11).getValue()));
		reportData[11] = profile.getProfileSettings().get(12).getValue();
		reportData[12] = null;
		reportData[13] = null;
		reportData[14] = null;
		
		// thickness row
		reportData[15] = profile.getProfileSettings().get(13).getValue();
		reportData[16] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(13).getValue()) - Double.parseDouble(profile.getProfileSettings().get(14).getValue()));
		reportData[17] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(13).getValue()) + Double.parseDouble(profile.getProfileSettings().get(14).getValue()));
		reportData[18] = profile.getProfileSettings().get(15).getValue();
		reportData[19] = null;
		reportData[20] = null;
		reportData[21] = null;
		
		// capacitans row
		reportData[22] = profile.getProfileSettings().get(26).getValue();
		reportData[23] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(26).getValue()) - Double.parseDouble(profile.getProfileSettings().get(17).getValue()));
		reportData[24] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(26).getValue()) + Double.parseDouble(profile.getProfileSettings().get(17).getValue()));
		reportData[25] = profile.getProfileSettings().get(28).getValue();
		reportData[26] = null;
		reportData[27] = null;
		reportData[28] = null;
		
		// stroke row
		reportData[29] = profile.getProfileSettings().get(29).getValue();
		reportData[30] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(29).getValue()) - Double.parseDouble(profile.getProfileSettings().get(30).getValue()));
		reportData[31] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(29).getValue()) + Double.parseDouble(profile.getProfileSettings().get(30).getValue()));
		reportData[32] = profile.getProfileSettings().get(31).getValue();
		reportData[33] = null;
		reportData[34] = null;
		reportData[35] = null;
		
		
		
		
		
		
		
		
		
		
		
//		reportData[30] = ;
		
		
		for(int i=0; i< 3; i++){
			
		}
		return null;
	}

}
