<%@ page contentType = "text/html; charset = UTF-8"  pageEncoding="utf-8"%>
<%@ page import="com.shenxinjie.Results"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.shenxinjie.CompareTwoCpp" %>
<%@ page import="java.util.*" %>
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
						<%
							ArrayList<String> cppFiles = (ArrayList<String> )session.getAttribute("pathlist");
							Results rs;
							int max_line = 0;
							String xx = request.getParameter("x");
							String yy = request.getParameter("y");
							String zz = request.getParameter("z");
							String f = request.getParameter("f");
							
							if(cppFiles != null)
							{
								int xth = Integer.parseInt(xx);
								int yth = Integer.parseInt(yy);
								int zth = Integer.parseInt(zz);
								int ff = Integer.parseInt(f);
								System.out.println("cppFiles: "+cppFiles);
								rs = CompareTwoCpp.getResult(cppFiles, xth, yth);
								int[][] matchResults = rs.getMatchResults();
								int[][] matchR = rs.getMatchR();
								String[][] fileContents = rs.getFileContents();
								int[] FileLength = rs.getFileLength();
						%>
			<div class="main" style="margin-left:0;">
			
				<div class="hero">
						<div class="row">
							<div class="col-md-12" style="height:60px">
								<h2>代码查重详情</h2>
								<p style="font-family:YouYuan">网页会标示有重复嫌疑的代码.</p>
							</div>
						</div>
				</div>
				
				<div class="sidebar-search" style="width:100%;height:40px;">
					<form method="post" action="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=0%>&f=<%=0%>">
						<div class="input-group" style="margin:50px auto;">
							<label><%="The Similarity is "+rs.getResults()[0]%></label>
							<input type="submit" value="Show All" />
						</div>
					</form>
				</div>
				<table id="mytable" style="width: 90%;margin: auto;font-size:12px;" class="table table-bordered table-striped table-hover">
						<tr>
							<th style="width: 50%; text-align:center;">原文件</th>
							<th style="width: 50%; text-align:center;">对比文件</th>
						</tr>
						
						<tr>
							<td style="text-align:center;"><%=cppFiles.get(xth) %></td>
							<td style="text-align:center;"><%=cppFiles.get(yth) %></td>
						</tr>
						<%
								if (ff == 0) {
									if(matchResults != null && fileContents != null)
									{
										max_line = Math.max(FileLength[0], FileLength[1]);
										for (int i = 0; i < max_line; i++) {
						%>
						<tr>
						<%
											if (matchR[0][i] == 1) {
						%>
							<td style="color:red">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>"> 
						<%
												out.print(fileContents[0][i]);
						%>
								</a>
							</td>
						<% 
											}else {
						%>
							<td style="color:black">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>">
						<%
												out.print(fileContents[0][i]);
						%>
								</a>
							</td>
						<%
										}
						%>	
						<%
										if (matchR[1][i] == 1) {
						%>
							<td style="color:red">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>"> 
						<%
												out.print(fileContents[1][i]);
						%>
								</a>
							</td>
						<%
										}else {
						%>
							<td style="color:black">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>">
						<%
												out.print(fileContents[1][i]);
						%>
								</a>
							</td>
						<%
										}
						%>	
						</tr>
						<%				
									}
								}
							}else {
								if(matchResults != null && fileContents != null)
								{
									max_line = Math.max(FileLength[0], FileLength[1]);
									if (zth < max_line) {
										for (int i = 0; i < max_line; i++) {
						%>
						<tr>
						<%
											if (i == zth) {
						%>
							<td style="color:red">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>"> 
						<%
												out.print(fileContents[0][i]);
						%>
								</a>
							</td>
						<%
											}else {
						%>
							<td style="color:black">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>"> 
						<%
												out.print(fileContents[0][i]);
						%>
								</a>
							</td>
						<%
											}
											if (matchResults[zth][i] == 1) {
						%>
							<td style="color:red">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i+max_line%>&f=<%=1%>">
						<%
												out.print(fileContents[1][i]);		
						%>
								</a>
							</td>
						<%			
											}else {
						%>
							<td style="color:black">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i+max_line%>&f=<%=1%>"> 
						<%	
												out.print(fileContents[1][i]);
						%> 
								</a>
							</td>
						<%					
											}
						%>
						</tr>
						<%
										}
									}else {
										for (int i = 0; i < max_line; i++) {
						%>
						<tr>
						<%
											if (matchResults[i][zth-max_line] == 1) {
						%>
							<td style="color:red">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>"> 
						<%
												out.print(fileContents[0][i]);
						%>
								</a>
							</td>
						<%
											}else {
						%>
							<td style="color:black">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i%>&f=<%=1%>"> 
						<%
												out.print(fileContents[0][i]);
						%>
								</a>
							</td>
						<%
											}
											if (i == zth-max_line) {
						%>
							<td style="color:red">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i+max_line%>&f=<%=1%>">
						<%
												out.print(fileContents[1][i]);		
						%>
								</a>
							</td>
						<%			
											}else {
						%>
							<td style="color:black">
								<a style="color:inherit; "href="showCRs?i=<%=xth%>&ii=<%=yth%>&j=<%=i+max_line%>&f=<%=1%>"> 
						<%	
												out.print(fileContents[1][i]);
						%> 
								</a>
							</td>
						<%
											}
						%>
						</tr>
						<%				
										}
									}
								}
							}
						}
						%>
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
		<script>
			function ReFlash(obj, len) {
				alert(obj.id);
			}
		</script>
	</body>	
</html>