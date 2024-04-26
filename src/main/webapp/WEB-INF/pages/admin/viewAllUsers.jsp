<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<thead>
       <tr>
           <th>FirstName</th>
           <th>LastName</th>
           <th>Ema il</th>
           <th>Gender</th>
           <th>Username</th>
           <th>PhoneNumber</th>
           <th>Role</th>
</tr>
</thead>
           <tbody>
                 <c:forEach var="student" items="${listOfStudent }">
                 <tr>
                     <td><c:out value="${student.firstName }"></c:out></td>
                     <td><c:out value="${student.lastName}"></c:out></td>
                     <td><c:out value="${student.username }"></c:out></td>
                     <td><c:out value="${student.dob }"></c:out></td>
                     <td><c:out value="${student.gender }"></c:out></td>
                     <td><c:out value="${student.email }"></c:out></td>
                     <td><c:out value="${student.phoneNumber }"></c:out></td>
                 	<td><c:out value="${student.subject }"></c:out></td>
                 
                 </tr>
                 
                 
                 
                 </c:forEach>
           
           
           
           
           </tbody>



</table>

</body>
</html>