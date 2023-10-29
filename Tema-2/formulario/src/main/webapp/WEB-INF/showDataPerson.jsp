<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.iesbelen.entitys.Person" %>
<% Person person = (Person) request.getAttribute("person");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=person.toString()%>
</body>
</html>
