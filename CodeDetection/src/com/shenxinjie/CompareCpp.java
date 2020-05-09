package com.shenxinjie;

//import bean.Results;
//import util.CompareUtil;
import java.io.*;
import java.util.*;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.servlet.*;
import javax.servlet.http.*;

public class CompareCpp extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sit = request.getParameter("selected");
		int it = (sit != null && sit != "") ? Integer.parseInt(sit) : 0; //获得一个编号，显示一个it号cpp与其他cpp的比较信息。 
		ArrayList<String> fileListNames = (ArrayList<String>)session.getAttribute("pathlist");
		String selectPath = (fileListNames != null) ? (String)fileListNames.get(it) : "";
		//System.out.println("CompareCpp: it = " + it + " showname: " + selectPath);
		//session.setAttribute("showname", selectPath);
		if (fileListNames != null && fileListNames.size() != 0 && selectPath != null && !selectPath.equals(""))
		{
			session.setAttribute("showname", selectPath);
			//Results results = CompareUtil.getResultSet(fileListNames);
			//session.setAttribute("resultset", results);
			session.setAttribute("keep", true);//这keep有什么用？
		}
		/*try
		{
			response.sendRedirect("compare.jsp");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}*/
		response.sendRedirect("compare.jsp");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doPost(request, response);
	}
}



