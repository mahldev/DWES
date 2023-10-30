<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Integer> listaDados = (List<Integer>) request.getAttribute("listaDados");
    List<Integer> listaDadosOrdenada = (List<Integer>) request.getAttribute("listaDadosOrdenada");
    Integer numeroDados = (Integer) request.getAttribute("numeroDados");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Ordenar dados</h1>
<p>Actualice la página para mostrar una nueva tirada.</p>
<h2>Tirada de <%=numeroDados%> dados</h2>
<%for (Integer dado : listaDados) { %>
<img src="./imgDados/<%=dado%>.svg">
<%} %>

<h2>Tirada ordenada</h2>
<%for (Integer dado : listaDadosOrdenada) { %>
<img src="./imgDados/<%=dado%>.svg">
<%} %>
</body>
</html>
