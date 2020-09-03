<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>  

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写入cookie</title>
</head>
<body>

<%


request.setCharacterEncoding("GB18030");
String user=URLEncoder.encode(request.getParameter("user"),"utf-8");//获取用户名
//创建和实例化cookies
Cookie cookie=new Cookie("mrCookie",user);
cookie.setMaxAge(60*60*24*30);//设置cookies有效期30天
response.addCookie(cookie);//保存cookies
%>
<script type="text/javascript">window.location.herf="book_list.jsp"</script>
</body>
</html>