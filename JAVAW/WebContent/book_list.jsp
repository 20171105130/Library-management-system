<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="com.lyq.bean.*"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有图书信息</title>
<link href="css/style1.css" type="text/css" rel="stylesheet" />
<link href="css/indexcss.css" type="text/css" rel="stylesheet" />

    
    
<%
DateFormat ddf = DateFormat.getDateInstance();  
DateFormat dtf = DateFormat.getTimeInstance();  
DateFormat ddtf = DateFormat.getDateTimeInstance();  
Date date = new Date(); 
SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();
	Cookie[] cookies = request.getCookies();
	String user = "";
	String data = "";
	if (cookies != null){
		for (int i = 0;i < cookies.length;i++){
			if(cookies[i].getName().equals("mrCookie")){
				user = URLDecoder.decode(cookies[i].getValue().split("#")[0]);
				//data = cookies[i].getValue().split("#")[1];
			}
		
		}
	}
	if("".equals(user) && "".equals(data)){			//如果没有注册
%>	
		你好，欢迎初次开启该系统
		<form action = "deal.jsp" method="post">
			输入姓名：<input name="user"type="text"value="">
			<input type="submit"value="OK">
		</form>
<%
	}else{
%>
		欢迎<b><%=user %></b>再次光临<br>
		当前时间为：<%=sdf.format(date) %>
<%
	}
%>
<style type="text/css">
	form{margin: 0px;}
	td{font-size: 12px;}
	h2{margin: 2px}
</style>
<script type="text/javascript">
	function check(form){
		with(form){
			if(bookCount.value == ""){
				alert("请输入更新数量！");
				return false;
			}
			if(isNaN(bookCount.value)){
				alert("格式错误！");
				return false;
			}
			return true;;
		}
	}
</script>
</head>
<body>
	<div class="wrapper">
		<div id="web_bg" style="background-image: url(./images/5555.jpg);"></div>
	<table align="center" width="500" border="1" height="170" bordercolor="white" bgcolor="black" cellpadding="1" cellspacing="1">
		<tr bgcolor="white">
			<td align="center" colspan="7">
				<h2>所有图书信息</h2>
			</td>
		</tr>
		<tr align="center" bgcolor="#e1ffc1" >
			<td><b>ID</b></td>
			<td><b>图书名称</b></td>
			<td><b>价格</b></td>
			<td><b>数量</b></td>
			<td><b>作者</b></td>
			<td><b>修改数量</b></td>
			<td><b>删   除</b></td>
		</tr>
			<%
				// 获取图书信息集合
					List<Book> list = (List<Book>)request.getAttribute("list");
					// 判断集合是否有效
					if(list == null || list.size() < 1){
						out.print("没有数据！");
					}else{
						// 遍历图书集合中的数据
						for(Book book : list){
			%>
							<tr align="center" bgcolor="white">
								<td><%=book.getId()%></td>
								<td><%=book.getName()%></td>
								<td><%=book.getPrice()%></td>
								<td><%=book.getBookCount()%></td>
								<td><%=book.getAuthor()%></td>
					
								<td>
									<form action="UpdateServlet" method="post" onsubmit="return check(this);">
									<input type="hidden" name="id" value="<%=book.getId()%>"><!-- 会话跟踪之隐藏表单域 -->
									<input type="text" name="bookCount" size="3">
									<input type="submit" value="修　改">
									</form>
									<td><a href="DeleteServlet?id=<%= book.getId()%>">删除</a></td>
						
						
								</td>
							</tr>
			<%
						}
					}
			%>
			<tr>
		<td align="center" colspan="7" bgcolor="white">
			<%=request.getAttribute("bar")%>
		</td>
	</tr>
	</table>
<!-- 	<div class="header"></div> -->
</div>
</body>
</html>