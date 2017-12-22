
package ie.gmit.sw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.client.DictionaryServiceClient;
import ie.gmit.sw.client.Request;
import ie.gmit.sw.client.Servitor;

@WebServlet("/index")
public class DictionaryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	DictionaryServiceClient dsc;
	
	public void init() throws ServletException{
		try {
			dsc = new DictionaryServiceClient();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String word = req.getParameter("word");
		String tNum = req.getParameter("taskNum");
		
		out.print("<html><head><title>RMI Dictionary App</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		String def = dsc.getDefinition(tNum);
		
		req.setAttribute("word", word);
		req.setAttribute("description", def);
		
		out.print("</body>");	
		out.print("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String word = req.getParameter("word");
		String desc = req.getParameter("description");

		Request r = new Request(word, desc);
		dsc.addRequest(r);
		
	}

	
}
