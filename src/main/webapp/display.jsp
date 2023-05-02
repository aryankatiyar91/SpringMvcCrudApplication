<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@page import="com.techpalle.model.Student"%>
	<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<style>
	<%@ include file="css/style.css"%>
</style>

<script type="text/javascript">
	<%@ include file="js/sample.js" %>
</script>
<head>
<meta charset="ISO-8859-1">
<title>Display Page</title>
</head>
<body>

	<h1>Student List:</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Sno: </th>
				<th>Name: </th>
				<th>Email: </th>
				<th>Password: </th>
				<th>Mobile: </th>
				<th>Actions: </th>
			</tr>
		</thead>
		
		<tbody>
		<% ArrayList<Student> stud=(ArrayList<Student>) request.getAttribute("student"); 
			for(Student s:stud)
			{
		%>
				<tr>
					<td><c:out value="<%=s.getSno()%>"></c:out></td>
					<td><c:out value="<%=s.getName()%>"></c:out></td>
					<td><c:out value="<%=s.getEmail()%>"></c:out></td>
					<td><c:out value="<%=s.getPassword()%>"></c:out></td>
					<td><c:out value="<%=s.getMobile()%>"></c:out></td>
					<td>
						<a href="editForm?id=<c:out value="<%=s.getSno()%>"></c:out>" >Edit</a>  
				  		&nbsp; &nbsp;  
				  		<a href="delete?id=<c:out value="<%=s.getSno()%>"></c:out>" onclick="confirm('Are you sure?\nStudent record will be deleted permanently!!')">Delete</a>
					</td>
				</tr>
		<%
			}
		%>
		</tbody>
	</table>
	
</body>
</html>