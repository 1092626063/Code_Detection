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
		String allCode = request.getParameter("allCode");
		String considerSpace = request.getParameter("considerSpace");
		String considerCapital = request.getParameter("considerCapital");
		System.out.println("CompareCppCode: aC: "+allCode+" cS: "+considerSpace+" cC: "+considerCapital);
		String i = request.getParameter("i");
		String ii = request.getParameter("ii");
		if(i != null && ii != null)
		{ 
			allCode = null;
			considerSpace = "1";
			considerCapital = "1";
		}
		boolean aC = true, cS = true, cC = true;
		if(allCode != null && considerSpace != null && considerCapital != null)
		{
			session.setAttribute("showFlag", 0);
		}
		else if(allCode != null && considerSpace == null && considerCapital != null)
		{
			session.setAttribute("showFlag", 1);
			cS = false;
		}
		else if(allCode != null && considerSpace != null && considerCapital == null)
		{
			session.setAttribute("showFlag", 2);
			cC = false;
		}
		else if(allCode != null && considerSpace == null && considerCapital == null)
		{
			session.setAttribute("showFlag", 3);
			cS = false; cC = false;
		}
		else if(allCode == null && considerSpace != null && considerCapital != null)
		{
			session.setAttribute("showFlag", 4);
			session.setAttribute("I", i);
			session.setAttribute("II", ii);
			aC = false;
		}
		else if(allCode == null && considerSpace == null && considerCapital != null)
		{
			session.setAttribute("showFlag", 5);
			//session.setAttribute("I", request.getParameter("i"));
			//session.setAttribute("II", request.getParameter("ii"));
			session.setAttribute("I", i);
			session.setAttribute("II", ii);
			aC = false; 
			cS = false;
		}
		else if(allCode == null && considerSpace != null && considerCapital == null)
		{
			session.setAttribute("showFlag", 6);
			//session.setAttribute("I", request.getParameter("i"));
			//session.setAttribute("II", request.getParameter("ii"));
			session.setAttribute("I", i);
			session.setAttribute("II", ii);
			aC = false; 
			cC = false;
		}
		else if(allCode == null && considerSpace == null && considerCapital == null)
		{
			session.setAttribute("showFlag", 7);
			//session.setAttribute("I", request.getParameter("i"));
			//session.setAttribute("II", request.getParameter("ii"));
			session.setAttribute("I", i);
			session.setAttribute("II", ii);
			aC = false; 
			cS = false; cC = false;
		}
		session.setAttribute("resultset", null);
		ArrayList<String> pathList = (ArrayList<String>)session.getAttribute("pathlist");
		session.setAttribute("resultset", CompareUtil.getResultSet(pathList, 2, cS, cC));
		String xx = (session.getAttribute("x") != null) ? ((String)session.getAttribute("x")) : "1";
		String yy = (session.getAttribute("y") != null) ? ((String)session.getAttribute("y")) : "1";
		response.sendRedirect("compare_details.jsp?x="+xx+"&y="+yy);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doPost(request, response);
	}
}
