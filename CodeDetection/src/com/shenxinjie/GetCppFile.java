package com.shenxinjie;
//import Results;
//import util.CompareUtil;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class GetCppFile extends HttpServlet 
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);		
		
		String path = "ddd";
		String allFlag = "";
		//非enctype=multipart/form-data形式
		path = request.getParameter("path");//获得路径
		System.out.println("path222: "+path);
		allFlag = request.getParameter("all");//这得到的是什么？？？
		if (allFlag != null && !allFlag.equals(""))
		{
			//session.setAttribute("temppath", path);
			session.setAttribute("showname", null);
			session.setAttribute("showAll", true);
			session.setAttribute("keep", true);
		}
		try
		{
			//List items = servletFileUpload.parseRequest(request);//解析request
            //Iterator iterator = items.iterator();

			//
			/*while (iterator.hasNext()) 
			{
                FileItem item = (FileItem) iterator.next();
                if(item.isFormField())
				{//表单的参数字段
                    System.out.println("表单的参数名称："+item.getFieldName()+",表单的参数值："+item.getString("UTF-8"));
                }else
				{
                    if(item.getName()!=null && !item.getName().equals(""))
					{//一个上传的文件
                        //System.out.println("文件的名称："+item.getName());
                        //System.out.println("文件的大小："+item.getSize());
                        //System.out.println("文件的类型："+item.getContentType());
                        path = item.getName();
                        //File tempFile = new File(item.getName());//getName得到的文件名称包含了它在客户端的路径
                        //File file = new File(sc.getRealPath("/")+savePath,tempFile.getName());
                        //item.write(file);//将上传的文件写入到file中
                        
                        //request.setAttribute("message", "上传文件成功！");
                    }//else{
                        //request.setAttribute("message", "没有选择上传文件！");
                    //}
                }
            }*/
			//
			//System.out.println("GetCppFiles: path: "+path);
			//if(path == null||path.equals(""))
			//{
			//	response.sendRedirect("compare.jsp");
			//}
			if(path != null && !path.equals(""))
			{
				if(path.contains("."))
				{
					if (allFlag != null && !allFlag.equals(""))
					{
						session.setAttribute("temppath", path);
					}
					if(path.contains("/"))
					{
						//path = path.split(path.substring(path.lastIndexOf("/")+1));
						path = path.replace(path.substring(path.lastIndexOf("/")+1), "");
					}
					else if(path.contains("\\"))
					{
						//path = path.split(path.substring(path.lastIndexOf("\\")+1));
						path = path.replace(path.substring(path.lastIndexOf("\\")+1), "");
					}
				}
				File files = new File(path);
				//System.out.println(path);
				File[] fileList = files.listFiles();
				String cppFiles = "", tempPath = "";
				ArrayList<String> pathList = new ArrayList<String>();
				if(fileList != null)
				{
					for(int i = 0; i < fileList.length; ++i)
					{
						if(fileList[i].isFile()&&(fileList[i].toString().contains(".cpp")))
						{
							tempPath = fileList[i].toString();
							pathList.add(tempPath);
							//System.out.println("GetCppFile: pathList:"+pathList);
							if(tempPath.contains("\\"))
							{
								cppFiles = cppFiles + tempPath.substring(tempPath.lastIndexOf("\\")+1) + ";";
							}
							else if(tempPath.contains("/"))
							{
								cppFiles = cppFiles + tempPath.substring(tempPath.lastIndexOf("/")+1) + ";";
							}
							
						}
					}
				}
//				//request.setAttribute("cppfiles", cppFiles);
				Results results = CompareUtil.getResultSet(pathList, 2, true, true);//获取results
				session.setAttribute("resultset", results);
				session.setAttribute("pathlist", pathList);
				session.setAttribute("cppfiles", cppFiles);
				System.out.println("GetCppFile.java: "+ cppFiles);
				session.setAttribute("filecontents", results.getFileContents());
				session.setAttribute("matchresults", results.getMatchResults());
				session.setAttribute("matchr", results.getMatchR());
				session.setAttribute("keep", true);
			}
			response.sendRedirect("compare.jsp");
		}
		catch (Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doPost(request, response);
	}
}
