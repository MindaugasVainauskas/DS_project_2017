package ie.gmit.sw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.server.Servitor;

public class NewServlet extends HttpServlet {
	private Servitor dService;
	private static long jobNumber = 0;
	
	public void init() throws ServletException{
		ServletContext ctx = getServletContext();
		try {
			dService = (Servitor) Naming.lookup("rmi://localhost:1099/dictionaryService");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Step 1) Write out the MIME type
		resp.setContentType("text/html"); 
		
		//Step 2) Get a handle on the PrintWriter to write out HTML
		PrintWriter out = resp.getWriter(); 
		
		String word = req.getParameter("word");
		String taskNumber = req.getParameter("frmTaskNumber");
		String definition = req.getParameter("definition");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Add job to in-queue
			definition = dService.search(word);
		}else{
			req.setAttribute("word", word);
			req.setAttribute("definition", definition);
		}
		
		out.print("<html><head><title>Dictionary Service with RMI</title>");		
		out.print("</head>");		
		out.print("<body>");
		out.print("<div><h4>Definition: </h4><p>"+definition+"</p></div>");
		out.print("<form method=\"post\" action=\"/DS_Project/Index.jsp\"><input type=\"submit\" value=\"Get Another Definition\"></input></form>");
		out.print("</body>");	
		out.print("</html>");	
		
		//JavaScript to periodically poll the server for updates (this is ideal for an asynchronous operation)
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);"); //Refresh every 10 seconds
		out.print("</script>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
