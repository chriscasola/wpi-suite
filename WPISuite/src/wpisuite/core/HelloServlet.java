package wpisuite.core;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

import wpisuite.models.*;
public class HelloServlet extends HttpServlet 
{

	MockDataStore data;
	
	public HelloServlet()
	{
		data = MockDataStore.getMockDataStore();
	}
	
	public void doGet (HttpServletRequest req,
                       HttpServletResponse res) throws ServletException, IOException
	{
        PrintWriter out = res.getWriter();
        String delims = "[/]+";
        String[] path = req.getPathInfo().split(delims);
        if(path[1].equalsIgnoreCase("user"))
        {
        	User u = data.getUser(Integer.parseInt(path[2]));
        	out.println(u.toJSON());
        }
        else
        {
        	Project p = data.getProject(Integer.parseInt(path[2]));
        	out.println(p.toJSON());
        }
        out.close();
	}
	
	public void doPut (HttpServletRequest req,
            HttpServletResponse res) throws ServletException, IOException
    {
		BufferedReader in = req.getReader();
		String delims = "[/]+";
        String[] path = req.getPathInfo().split(delims);
        if(path[1].equalsIgnoreCase("user"))
        {
    		data.addUser(in.readLine());

        }
        else
        {
    		data.addProject(in.readLine());

        }
    }
}