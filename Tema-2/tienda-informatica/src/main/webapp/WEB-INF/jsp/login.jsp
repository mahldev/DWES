<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/tienda/usuarios/login/" method="post">
    <label>Nombre
        <input type="text" name="usuario" >
    </label>
    <label>Password
        <input type="text" name="password" >
    </label>
    <button>Send</button>
</form>

</body>
</html>
