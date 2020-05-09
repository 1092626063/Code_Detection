<%@ page contentType = "text/html; charset = UTF-8"  pageEncoding="utf-8"%>
<%--<%@ page import="java.util.*"%>--%>
<%@ page import="com.shenxinjie.Results"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Title here -->
		<title>:: PENNY ::</title>
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
	<%
		boolean keep = (session.getAttribute("keep") != null) ? Boolean.parseBoolean(session.getAttribute("keep").toString()) : true;
		if(!keep)
		{
			session.setAttribute("cppfiles", null);
			session.setAttribute("showname", null);
			session.setAttribute("resultset", null);
			session.setAttribute("pathlist", null);
			//session.setAttribute("keep", true);
		}
	%>
	<body>
		
		<div class="outer">
		
			<!-- 左侧边栏 Start-->
			<!-- 平板电脑和手机的菜单 -->
			<div class="navigation">
				<a href="#">菜单</a>
			</div>
			<div class="sidebar">
				<!-- 标志 -->
				<div class="logo text-center">
					<h1><a href="index.jsp">主页</a></h1>
					<p>Home</p>
				</div>
				<div class="sidebar-search">
					<form method="post" action="getCpp"><!-- enctype="multipart/form-data"-->
						<div class="input-group">
							<input type="hidden" id="path" name="path" value="">
							<input type="file" id = "text" class="form-control" onchange="getvl(this)"> <!--webkitdirectory directory-->
							<input type="submit" value="获取">
							<!-- <input type="radio" id="single" name="mode" value="1" style="color:#fff;margin-left:3%;">
							<label for="single" style="color:#fff;">单项比较</label>
							<input type="radio" id="all" name="mode" value="2" style="color:#fff;margin-left:3%;">
							<label for="all" style="color:#fff;">全部结果</label> -->
							<input type="submit" name="all" value="全部结果" style="background-color:#fff;margin-left:50%;">
						</div>
					</form>
				</div>
				<!-- 导航菜单 -->
				<!-- <form action=""> -->
					<ul class="list-unstyled list">
						<!-- <li><a href="#home" class="anchorLink"><i class="icon-home scolor"></i> 主页</a></li>
						<li><a href="#.feature" class="anchorLink"><i class="icon-road scolor"></i> 特征</a></li>
						<li><a href="#.testimonial" class="anchorLink"><i class="icon-info scolor"></i> 介绍</a></li>
						<li><a href="#.team" class="anchorLink"><i class="icon-user scolor"></i> 关于我们</a></li>
						<li><a href="#.contact" class="anchorLink"><i class="icon-envelope scolor"></i> 联系我们</a></li> -->
						<%
							String[] cppFiles = null;//, pathList = null;
							//ArrayList<String> pathList = (ArrayList<String>)session.getAttribute("pathlist");
							String cppFile = (String)session.getAttribute("cppfiles");
							//String pathList = (String)session.getAttribute("pathList");
							//System.out.println(pathList);
							System.out.println("compare.jsp cppFile: "+cppFile);
							if(cppFile != null) // && pathList != null && pathList.size() != 0)
							{
								//String wholename = "";
								cppFiles = cppFile.split(";");
								for(int i = 0; i < cppFiles.length; ++i)
								{
									//wholename = (String)pathList.get(i);
									%>
									<li><a href="showRs?selected=<%=i%>" class="anchorLink"><i class="icon-info scolor"></i><%=cppFiles[i]%></a></li>
									<%
								}
							}
							//session.setAttribute("cppfiles", null);
						%>
					</ul>
				<!-- </form> -->
			</div>
			
			<!-- 左侧边栏 End -->
			
			
			<!-- 主页 Start -->
			
			<div class="main">
			
				<div class="hero">
						<div class="row">
							<div class="col-md-12">
								<h2>代码相似度对比</h2>
								<p style="font-family:YouYuan">网页会通过一定的算法规则计算代码之间的相似度水平并以相似度水平从高到低的顺序排列.</p>
								<p style="font-family:YouYuan">提供的参考相似度阈值为:0.86</p>
							</div>
						</div>
					</div>
				<table id="mytable" style="width: 80%;margin: auto;" class="table table-bordered table-striped table-hover">
						<tr>
							<th style="width: 25%; text-align:center;">原文件</th>
							<th style="width: 25%; text-align:center;">对比文件</th>
							<th style="width: 25%; text-align:center;">相似度</th>
							<th style="width: 25%; text-align:center;">详情</th>
						</tr>
					<%
						Results rs = (Results)session.getAttribute("resultset");
						String name = (String)session.getAttribute("showname");
						System.out.println("compare.jsp rs: "+rs+"  name: "+name);
						name = name != null ? (name.contains("\\") ? name.substring(name.lastIndexOf("\\")+1) : (name.contains("/") ? name.substring(name.lastIndexOf("/")+1) : name)) : name;
						if (rs != null && name != null)
						{
							int length = rs.getNum();
							String[] orFile = rs.getOrFileNames();
							String[] cpFile = rs.getFileNames();
							String[] result = rs.getResults();
							//System.out.println("compare.jsp length: "+length+" orFile: "+orFile+" cpFile: "+cpFile+" result: "+result);
							if(orFile != null && cpFile != null && result != null)
							{
								for (int i = 0, n = 0; i < length; ++i)
								{
									//System.out.println("compare.jsp: x: " + (rs.getRecordX())[i] + " y: " + (rs.getRecordY())[i]);
									//System.out.println("compare.jsp orFiles: "+i+" "+orFile[i]+" cpFiles: "+i+" "+cpFile[i]+" results: "+i+"  "+result[i]);
									if (name.equals(orFile[i])||name.equals(cpFile[i]))
									{
					%>
						<tr class="cen" style="text-align:center">
							<%
							if(n == 0)
							{%>
							<td rowspan="<%=length%>"><%=name%></td>
							<%
								++n;
							}
							%>
							<%
							if(name.equals(orFile[i]))
							{%>
							<td><%=cpFile[i]%></td>
							<%
							}
							else
							{%>
							<td><%=orFile[i]%></td>
							<%
							}%>
							<td><%=result[i]%></td>
							<td><a href="javascript:void(0)" onclick="openWindow(<%=(rs.getRecordX())[i]%>,<%=(rs.getRecordY())[i]%>);">点击查看详情</a></td>
						</tr>
					<%
							
									}
								}
							}
							
							/*session.setAttribute("cppfiles", null);
							session.setAttribute("showname", null);
							session.setAttribute("resultset", null);
							session.setAttribute("pathlist", null);*/
							
						}
						else if(rs != null && name == null)
						{
							System.out.println("compare.jsp showAll: doing...");
							boolean all = (session.getAttribute("showAll") != null) ? Boolean.parseBoolean(session.getAttribute("showAll").toString()) : false;
							if (all)
							{
								String temppath = (String)session.getAttribute("temppath"); 
								if(temppath != null && !temppath.equals(""))
								{
							%>
							<script type="text/javascript">
								var subPath = "<%=temppath%>";
								document.getElementById("path").value = subPath;
							</script>
							<%	}
								System.out.println("compare.jsp showAll: Yes!!");
								int length = rs.getNum();
								String[] orFile = rs.getOrFileNames();
								String[] cpFile = rs.getFileNames();
								String[] result = rs.getResults();
								System.out.println("compare.jsp length: "+length+" orFile: "+orFile+" cpFile: "+cpFile+" result: "+result);
								if(orFile != null && cpFile != null && result != null)
								{
									
									for (int i = 0; i < length; ++i)
									{
					%>
						<tr class="cen" style="text-align:center">
							<td><%=orFile[i]%></td>
							<td><%=cpFile[i]%></td>
							<td><%=result[i]%></td>	
							<td><a href="javascript:void(0)" onclick="openWindow(<%=(rs.getRecordX())[i]%>,<%=(rs.getRecordY())[i]%>);">点击查看详情</a></td>
						</tr>
					<%
									}
								}
							}
						}
						else
						{
							
					%>
						<tr class="cen" style="text-align:center">
							<td>无结果</td>
							<td>无结果</td>
							<td>无结果</td>
							<td>无结果</td>
						</tr>
						
					<%
						}
						session.setAttribute("keep", false);
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

		<script src="js/openwindow.js"></script>
		
	</body>	
</html>