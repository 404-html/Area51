package servlets;

import java.io.*;

import javaMeasure.Batch;
import javaMeasure.BatchProfile;
import javaMeasure.PropertyHelper;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
	private Batch batch = null;
	private BatchProfile profile = null;
	/**
	 * Default constructor. 
	 */
	public ReportServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//download(request, response);
		processReq(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReq(request, response);
	}

	private void processReq(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//Login check
		if (request.getSession().getAttribute("user")!=null){
			if(request.getParameter("saveAsExcel")!=null){
				download(request, response);
			}
			else if((request.getParameter("newReport")!=null)){
				request.getRequestDispatcher("MenuServlet").forward(request, response);
				System.out.println("redirect til menu");
			}
			else{
				//Load batch from database
				String batchName = request.getParameter("batchNameSubmit"); //selectedbatch
				System.out.println(batchName);
				DataBaseController dbctrl = (DataBaseController) request.getSession().getAttribute("database");
				try {
					batch = dbctrl.getBatch(batchName);
					profile = dbctrl.getBatchProfile(batch.getProfileID());
					request.setAttribute("reportData", getReport(batch, profile));
					request.getRequestDispatcher("WEB-INF/report.jsp").forward(request, response);
				} catch (DataBaseException e) {
					e.printStackTrace();
				}
			}

		}else {	
			response.sendRedirect("LoginServlet");
		}

	}

	private void download(HttpServletRequest request,HttpServletResponse response) throws ServletException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=downloadfilename.csv");
		try{
			ServletOutputStream out = response.getOutputStream();
			StringBuffer sb = generateCsvFileBuffer();
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

			byte[] outputByte = new byte[4096];

			while(in.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}

			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}		
	}

	private StringBuffer generateCsvFileBuffer() {
		StringBuffer writer = new StringBuffer();
		String[] report;
		report = this.getReport(batch, profile);
		
		for(int i = 0; i < report.length; i++){
			writer.append(PropertyHelper.readFromProperty("csvFileLabels", "" + i));
			if(i < report.length -1){
				writer.append(";");
			}
		}
		writer.append("\n");
		for(int i = 0; i < report.length; i++){
			writer.append(report[i]);
			if(i < report.length -1){
				writer.append(";");
			}
		}
		return writer;
	}

	public String[] getReport(Batch batch, BatchProfile profile){
			
		String[] reportData = new String[56];

		// index 0 is reserved for the batchname
		reportData[0] = batch.getBatchString();
	


		for(int i = 0; i < 7; i++){
			reportData[i+1] = profile.getProfileSettings().get(i).getValue();
		}
		int reportIndex = 8;
		int row = 0;
		for(int i = 7; i < 16; i++){


			if(( i < 10 || i > 12) && i < 16){
				reportData[reportIndex] = profile.getProfileSettings().get(i).getValue();
				if(row < 3){
					try{
						reportData[reportIndex+1] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(i).getValue()) - Double.parseDouble(profile.getProfileSettings().get(i+12).getValue()));
						reportData[reportIndex+2] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(i).getValue()) + Double.parseDouble(profile.getProfileSettings().get(i+12).getValue()));
					} catch (NumberFormatException e){
						if(reportData[reportIndex+1] == null){
							reportData[reportIndex+1] = "";
						}
						reportData[reportIndex+2] = "";
					}

				} else{
					try{
						reportData[reportIndex+1] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(i).getValue()) - Double.parseDouble(profile.getProfileSettings().get(i+10).getValue()));
						reportData[reportIndex+2] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(i).getValue()) + Double.parseDouble(profile.getProfileSettings().get(i+10).getValue()));
					} catch (NumberFormatException e){
						if(reportData[reportIndex+1] == null){
							reportData[reportIndex+1] = "";
						}
						reportData[reportIndex+2] = "";
					}

				}
				reportData[reportIndex+3] = profile.getProfileSettings().get(i+21).getValue();
				reportData[reportIndex+4] = "";
				reportData[reportIndex+5] = "";
				reportData[reportIndex+6] = "";
				reportData[reportIndex+7] = "";
				reportIndex = reportIndex + 8;
				row++;
			}

		}

		for(int i = 0; i < reportData.length; i++){
			if(i > 7){
				if(i%8 == 0){
					System.out.println("");
				}
				System.out.print(i + ": " + reportData[i] + "\t");
			}
		}
		return reportData;
	}

	public static void main(String[] args){
		ReportServlet rs = new ReportServlet();
		DataBaseController db = new DataBaseController();
		Batch b = null;
		BatchProfile bp = null;
		try {
			b = db.getBatch(34);
			bp = db.getBatchProfile(127);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.getReport(b, bp);

	}

}
