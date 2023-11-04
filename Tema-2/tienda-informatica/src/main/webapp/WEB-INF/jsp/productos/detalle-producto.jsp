<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.Optional" %>
<%@ page import="static org.iesbelen.utils.HTMLGenerator.getProductoHTML" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Optional<Producto> producto = (Optional<Producto>) request.getAttribute("producto");%>
<html>
<head>
    <title>detalles producto</title>
    <style>
        :root {margin: 0; padding: 0; box-sizing: border-box}
        body { max-width: 1000px; margin: 0 auto; }
        main { display: flex; justify-content: center; align-items: center; margin-top: 2em; }
        .producto { display: flex; flex-direction: column; gap: .5em; }
    </style>
</head>
<body>
    <main>
        <%= getProductoHTML(producto)%>
    </main>
</body>
</html>
