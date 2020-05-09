<%@ page contentType = "text/html; charset = UTF-8"  pageEncoding="utf-8"%>
<%@ page import="com.shenxinjie.Results"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>:: DETAILS ::</title>
		<!-- Description, Keywords and Author -->
		<meta name="description" content="Your description">
		<meta name="keywords" content="Your,Keywords">
		<meta name="author" content="ResponsiveWebInc">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">			
		
		<!-- Styles -->
		<!-- Bootstrap CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- Font awesome CSS -->
		<link href="css/font-awesome.min.css" rel="stylesheet">	
		<!-- Animate CSS -->
		<link href="css/animate.min.css" rel="stylesheet">
		<!-- Custom CSS -->
		<link href="css/style.css" rel="stylesheet">
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
	</head>
	<%!
		public String handleStr(String s)
		{
			try
			{
				byte [] word = s.getBytes("iso-8859-1"); //iso-8859-1
				s = new String(word, "UTF-8"); // 必须要设置UTF-8属性 服务器默认 ANSI 
			}
			catch(Exception exp)
			{}
			return s;
		}
	%>
	<body>
		
		<div class="">
			
			<!-- 主页 Start -->
			
			<div class="main" style="margin-left:0;">
			
				<div class="hero">
						<div class="row">
							<div class="col-md-12">
								<h2>代码查重详情</h2>
								<p style="font-family:YouYuan">网页会标示有重复嫌疑的代码.</p>
								
							</div>
						</div>
				</div>
				<%int showFlag = (session.getAttribute("showFlag") != null) ? Integer.parseInt(session.getAttribute("showFlag").toString()) : 0; %>
				<div class="sidebar-search" style="width:100%;">
					<form method="post" action="showCRs"><!-- enctype="multipart/form-data"-->
						<div class="input-group" style="margin:50px auto;">
							<label for="allcode"><input id="allcode" type="checkbox" name="allCode" value="1" <%if(showFlag==0||showFlag==1||showFlag==2||showFlag==3){System.out.println("compare_details.jsp:showFlag1: "+showFlag);out.print("checked");}%>/>全部结果</label>
							<label for="space"><input id="space" type="checkbox" name="considerSpace" value="1"<%if(showFlag==0||showFlag==2||showFlag==4||showFlag==6){System.out.println("compare_details.jsp:showFlag2: "+showFlag);out.print("checked");}%>/>区分空格</label>
							<label for="capital"><input id="capital" type="checkbox" name="considerCapital" value="1" <%if(showFlag==0||showFlag==1||showFlag==4||showFlag==5){System.out.println("compare_details.jsp:showFlag3: "+showFlag);out.print("checked");}%>/>区分大小写</label>
							<input type="submit" value="确定" />
						</div>
					</form>
				</div>
				<table id="mytable" style="width: 100%;margin: auto;" class="table table-bordered table-striped table-hover">
						<tr>
							<th style="width: 50%; text-align:center;">原文件</th>
							<th style="width: 50%; text-align:center;">对比文件</th>
						</tr>
						<%
							Results rs = (Results)session.getAttribute("resultset");
							String[] cppFiles = ((String)session.getAttribute("cppfiles")).split(";");
							String xx = request.getParameter("x");
							String yy = request.getParameter("y");
							if(xx!=null)
							{session.setAttribute("x", xx);}
							if(yy!=null)
							{session.setAttribute("y", yy);}
							if(rs != null && cppFiles != null)
							{
								int x = Integer.parseInt(xx);
								int y = Integer.parseInt(yy);
						%>
						<tr>
							<td style="text-align:center;"><%=cppFiles[x-1]%></td>
							<td style="text-align:center;"><%=cppFiles[y-1]%></td>
						</tr>
						<%
								int[][] matchR = rs.getMatchR();
								int[][][][] matchResults = rs.getMatchResults();
								String[][] fileContents = rs.getFileContents();
								//Integer.parseInt(session.getAttribute("I").toString()) : 1;
								int ii  = (session.getAttribute("II") != null) ? Integer.parseInt((String)session.getAttribute("II")) : 1;
								//0默认全部+考虑大小写+考虑空格 1全部+考虑大小写+不考虑空格 2全部+不考虑大小写+不考虑空格
								//3单行+考虑大小写+考虑空格 4单行+考虑大小写+不考虑空格 5单行+不考虑大小写+不考虑空格
								//PrintWriter out = response.getWriter();
								if(matchR != null && matchResults != null && fileContents != null)
								{
									int length = fileContents[x].length>fileContents[y].length ? fileContents[x].length:fileContents[y].length;
									//if(showFlag == 0 || showFlag == 1 || showFlag == 2){
									for(int i = 1; i <= length; ++i)
									{
										if(fileContents[x][i] != null || fileContents[y][i] != null)
										{System.out.println("matchR[" + x + "]" + "[" + i + "]:" + matchR[x][i] );%>
						<tr>
							<td <%if(showFlag == 0 || showFlag == 1 || showFlag == 2){if(matchR[x][i] == 1){out.print("style=\"color:red;\"");}}else if(showFlag == 3 || showFlag == 4 || showFlag == 5){if(i == ii){out.print("style=\"color:red;\"");}}%>><a style="color:inherit; "href="showCRs?i=<%=x%>&ii=<%=i%>"><%
								if(fileContents[x][i] != null)
								{
									if(fileContents[x][i].contains("<"))
									{
										fileContents[x][i] = fileContents[x][i].replace("<", "&lt");
									}
									if(fileContents[x][i].contains(">"))
									{
										fileContents[x][i] = fileContents[x][i].replace(">", "&gt");
									}
									out.print(fileContents[x][i]);
								}else{}%></a></td>
							<td <%if(showFlag == 0 || showFlag == 1 || showFlag == 2){if(matchR[y][i] == 1){out.print("style=\"color:red;\"");}}else if(showFlag == 3 || showFlag == 4 || showFlag == 5){if(matchResults[x][ii][y][i] == 1){out.print("style=\"color:red;\"");}}%>><%
								if(fileContents[y][i] != null)
								{
									if(fileContents[y][i].contains("<"))
									{
										fileContents[y][i] = fileContents[y][i].replace("<", "&lt");
									}
									if(fileContents[y][i].contains(">"))
									{
										fileContents[y][i] = fileContents[y][i].replace(">", "&gt");
									}
									out.print(fileContents[y][i]);
								}else{}
								}else{break;}%></td>
						</tr>
							<%		}
								}
								/*else if(showFlag == 3 || showFlag == 4 || showFlag == 5)
								{
									//int i = (session.getAttribute("I") != null) ? Integer.parseInt(session.getAttribute("I").toString()) : 1;
									int ii  = (session.getAttribute("II") != null) ? Integer.parseInt((String)session.getAttribute("II")) : 1;
								%>
						
							<%
								for(int i = 1; i <= length; ++i)
								{if(fileContents[x][i]==null && fileContents[y][i]==null){break;}%>
								<tr>
								<td <%if(i == ii){out.print("style=\"color:red;\"");}%>><%=fileContents[x][i]%></td>
								<%//}
								//for(int j = 1; j <= length; ++j)
								//{
								%>
								<td <%if(matchResults[x][ii][y][i] == 1){out.print("style=\"color:red;\"");}%>><%=fileContents[y][j]%></td>
								<tr/>
								<%}//}%>
						
								<%}*/else
								{%>
						<tr>
							<td>暂无</td>
							<td>暂无</td>
						</tr>
								<%}
							}//}
							rs=null; cppFiles = null;session.setAttribute("showFlag", null);%>
				</table>
				
					<!-- 底部 -->
				<footer>
					<div class="row">
						<div class="col-md-12">
							<hr />
							<br />
							<p class="text-center">浙A2-20110026 浙ICP备11017770号 Copyright &copy; 2016-2017 浙江A&F信息工程学院版权所有 - Collect from <a href="http://ie.zafu.edu.cn/">浙江农林大学信息工程学院</a></p>
							<br />
						</div>
					</div>
				</footer>
				
				</div>
				
				
				
			</div>	<!-- This end div for main class -->
		</div>
		
		<div class="clearfix"></div>
		
		
		<!-- Scroll to top -->
      <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 

		
		<!-- Javascript files -->
		<!-- jQuery -->
		<script src="js/jquery.js"></script>
		<!-- Bootstrap JS -->
		<script src="js/bootstrap.min.js"></script>
		<!-- jquery Anchor JS -->
		<script src="js/jquery.arbitrary-anchor.js"></script>
		<!-- jQuery way points -->
		<script src="js/waypoints.min.js"></script>
		<!-- Respond JS for IE8 -->
		<script src="js/respond.min.js"></script>
		<!-- HTML5 Support for IE -->
		<script src="js/html5shiv.js"></script>
		<!-- Custom JS -->
		<script src="js/custom.js"></script>

		<script src="js/getpath.js"></script>
	</body>	
</html>