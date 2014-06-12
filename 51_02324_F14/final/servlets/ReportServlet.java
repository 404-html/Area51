package servlets;

import java.io.*;

import javaMeasure.Batch;
import javaMeasure.BatchProfile;
import javaMeasure.control.DataBaseController;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
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
		download(request, response);
		//processReq(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReq(request, response);
	}

	private void processReq(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
				request.setAttribute("reportData", getReport(batch, profile));
				request.getRequestDispatcher("WEB-INF/report.jsp").forward(request, response);
			} catch (DataBaseException e) {
				e.printStackTrace();
			}
		}

		}
	
	private void download(HttpServletRequest request,HttpServletResponse response) throws ServletException {
	 		response.setContentType("application/octet-stream");
	 		response.setHeader("Content-Disposition",
	 		"attachment;filename=downloadfilename.csv");
		Writer writer = null;
		ServletContext context = request.getSession().getServletContext();
	 	String realContextPath = context.getRealPath(request.getContextPath());
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
//	 	File dir = new File(realContextPath);
//	 	dir = dir.getParentFile();
//	 	try {

//	 	FileOutputStream esc =  new FileOutputStream(new File(dir,"newfile.csv"));
//	 	OutputStreamWriter pen = new OutputStreamWriter(esc, "utf-8");
//	     writer = new BufferedWriter(pen);
//	     request.getRequestDispatcher("newfile.csv").forward(request, response);
//	 	} catch (IOException ex) {
//	 		ex.printStackTrace();
//	 		System.out.println("I can't let you do that, "+request.getSession().getAttribute("username")+".");
//	   // report
//	 	} finally {
//	 		 
//	    try {writer.close();
//	    } catch (Exception ex) {}
//	 	}
		
	}
	
	private StringBuffer generateCsvFileBuffer() {
		StringBuffer writer = new StringBuffer();
		
		writer.append("Aced it.");
		return writer;
	}

	public String[] getReport(Batch batch, BatchProfile profile){
		String[] reportData = new String[56];

		reportData[0] = batch.getBatchString();


		for(int i = 0; i < 7; i++){
			reportData[i+1] = profile.getProfileSettings().get(i).getValue();
		}
		//		reportData[0] = profile.getProfileSettings().get(0).getValue();
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
		//		// length row	
		//		
		//		reportData[1] = profile.getProfileSettings().get(7).getValue();
		//		reportData[2] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(7).getValue()) - Double.parseDouble(profile.getProfileSettings().get(8).getValue()));
		//		reportData[3] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(7).getValue()) + Double.parseDouble(profile.getProfileSettings().get(8).getValue()));
		//		reportData[4] = profile.getProfileSettings().get(9).getValue();
		//		reportData[5] = null; // do not have the needed info
		//		reportData[6] = null; // do not have the needed info
		//		reportData[7] = null; // do not have the needed info
		//		
		//		// width row
		//		reportData[8] = profile.getProfileSettings().get(10).getValue();
		//		reportData[9] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(10).getValue()) - Double.parseDouble(profile.getProfileSettings().get(11).getValue()));
		//		reportData[10] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(10).getValue()) + Double.parseDouble(profile.getProfileSettings().get(11).getValue()));
		//		reportData[11] = profile.getProfileSettings().get(12).getValue();
		//		reportData[12] = null;
		//		reportData[13] = null;
		//		reportData[14] = null;
		//		
		//		// thickness row
		//		reportData[15] = profile.getProfileSettings().get(13).getValue();
		//		reportData[16] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(13).getValue()) - Double.parseDouble(profile.getProfileSettings().get(14).getValue()));
		//		reportData[17] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(13).getValue()) + Double.parseDouble(profile.getProfileSettings().get(14).getValue()));
		//		reportData[18] = profile.getProfileSettings().get(15).getValue();
		//		reportData[19] = null;
		//		reportData[20] = null;
		//		reportData[21] = null;
		//		
		//		// capacitans row
		//		reportData[22] = profile.getProfileSettings().get(26).getValue();
		//		reportData[23] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(26).getValue()) - Double.parseDouble(profile.getProfileSettings().get(27).getValue()));
		//		reportData[24] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(26).getValue()) + Double.parseDouble(profile.getProfileSettings().get(27).getValue()));
		//		reportData[25] = profile.getProfileSettings().get(28).getValue();
		//		reportData[26] = null;
		//		reportData[27] = null;
		//		reportData[28] = null;
		//		
		//		// stroke row
		//		reportData[29] = profile.getProfileSettings().get(29).getValue();
		//		reportData[30] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(29).getValue()) - Double.parseDouble(profile.getProfileSettings().get(30).getValue()));
		//		reportData[31] = String.valueOf(Double.parseDouble(profile.getProfileSettings().get(29).getValue()) + Double.parseDouble(profile.getProfileSettings().get(30).getValue()));
		//		reportData[32] = profile.getProfileSettings().get(31).getValue();
		//		reportData[33] = null;
		//		reportData[34] = null;
		//		reportData[35] = null;


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
