package com.shenxinjie;

//import bean.Results;
//import util.CompareUtil;
import java.io.*;
import java.util.*;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.servlet.*;
import javax.servlet.http.*;

public class CompareCppCode extends HttpServlet
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

		String i = request.getParameter("i");
		String ii = request.getParameter("ii");
		String j = request.getParameter("j");
		String f = request.getParameter("f");
		
		int xx = Integer.parseInt(i);
		int yy = Integer.parseInt(ii);
		int jj = Integer.parseInt(j);
		response.sendRedirect("compare_details.jsp?x="+xx+"&y="+yy+"&z="+jj+"&f="+f);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doPost(request, response);
	}
}
