package ie.gmit.sw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.server.Servitor;

public class RequestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Servitor dService;
	private static long jobNumber = 0;
	
	//set up queues
	private BlockingQueue<String> inQueue;
	private Map<String, String> outQueue;
	
	//Init method.
	public void init() throws ServletException{
		ServletContext ctx = getServletContext();
		
		//Instantiate queues as concrete subtypes of queue interface.
		inQueue = new LinkedBlockingQueue<String>();
		outQueue = new HashMap<String, String>();
		try {
			dService = (Servitor) Naming.lookup("rmi://localhost:1099/dictionaryService");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html"); 		
		PrintWriter out = resp.getWriter(); 
		//local temp string variable.
		String temp;
		
		String word = req.getParameter("word");
		String taskNumber = req.getParameter("frmTaskNumber");
		String definition = req.getParameter("definition");
		
		if (taskNumber == null){
			temp = new String();
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			
			inQueue.add(word);
			//Add job to in-queue
			try {
				temp = dService.search(inQueue.take());
				outQueue.put(taskNumber, temp);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		definition = outQueue.get(taskNumber);
		//remove the definition from out queue that has been returned.
		outQueue.remove(taskNumber);
		
		out.print("<html><head><title>Dictionary Service with RMI</title>");		
		out.print("</head>");		
		out.print("<body>");
		out.print("<div><h3>Task Number: "+taskNumber+"</h3></div>");
		out.print("<div><h4>Definition of word "+word+": </h4><p>"+definition+"</p></div>");
		out.print("<div>");
		out.print("<form method=\"post\" action=\"/DS_Project/Index.jsp\"><input type=\"submit\" value=\"Get Another Definition\"></input></form>");
		out.print("</div>");
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
