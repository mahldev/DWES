<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String animalImg = (String) request.getAttribute("animal-img");
    String animalName = (String) request.getAttribute("animal-name");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
    <main>
        <h1>Nombres de animales</h1>
        <p>Actualice la página para mostrar un nuevo animal.</p>
        <h2><%=animalName%>
        </h2>
        <img src="./imgAnimales/<%=animalImg%>.svg">
    </main>
</body>
</html>
