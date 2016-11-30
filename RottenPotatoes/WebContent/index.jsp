<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="umlClasses.Comments"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rotten Potatoes</title>
</head>
<body>
<form action="Registration.jsp" method="get">
<%
    Date d = new Date();
	SimpleDateFormat ft = 
		new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	Comments c = new Comments("Just a comment", d);
%>
  <% out.print("<h2 align=\"center\">" + c.getCommentText() + "</h2>");
  	 out.print("<h2 align=\"center\">" + ft.format(c.getDate()) + "</h2>");%>
<input type="submit">
</form>
</body>
</html>