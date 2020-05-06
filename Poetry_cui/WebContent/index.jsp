<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
/* List list =(List)request.getAttribute( "TestAll"); */
String all =(String)request.getAttribute( "TestAll");
String phone =(String)request.getAttribute("phone");
String TestServlet =(String)request.getAttribute("TestServlet");
String password = (String)request.getAttribute("password");
String name = (String)request.getAttribute("name");
String check = (String)request.getAttribute("check");
%>
	
	<p href="">所有用户列表:<%=all %></p>
	
	<p>phone:<%=phone %></p>
	<p>TestServlet:<%=TestServlet %></p>
	<p>password:<%=password %></p>
	<p>name:<%=name %></p>
	<p>check:<%=check %></p>
	<%-- <table border="1">
		<c:forEach items="${list }" var="pianos">			
			<tr>
				<td width="80px">aaa：${list}</td>
				
			</tr>
		</c:forEach>
	</table> --%>

</body>
</html>