<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%List<String> fabricantes = (List<String>) request.getAttribute("fabricantes"); %>
<html>

<head>
    <title>Crear producto</title>
    <style>
        body {
            max-width: 900px;
            margin: 0 auto;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: relative;
            width: 100%;
            margin-top: .5em;
        }


        header::after {
            content: "";
            position: absolute;
            width: 100%;
            bottom: 0;
            border-bottom: solid 2px rgb(128, 128, 128);
        }

        .container {
            display: flex;
            gap: .5em;

        }

        main form {
            display: grid;
            grid-template-columns: 1fr 2fr;
            margin-top: 1em;
            gap: .5em;
        }

        input,
        select {
            max-width: 150px;
        }

        main form button {
            margin-top: 2em;
            justify-self: start;
        }
    </style>
</head>

<body>

<header>
    <h1>Crear Producto</h1>
    <div class="container">
        <div>
            <button form="productoForm" type="submit">Crear</button>
        </div>
        <form action="${pageContext.request.contextPath}/tienda/productos/">
            <button>Volver</button>
        </form>
    </div>
</header>


<main>
    <form id="productoForm" action="${pageContext.request.contextPath}/tienda/productos/" method="post">

        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" required>

        <label for="precio">Precio</label>
        <input type="text" name="precio" id="precio" required>

        <label for="fabricante">Fabricante</label>
        <select id="fabricante" name="fabricante">
            <% for (String nombre : fabricantes) { %>
            <option value="<%=nombre%>"><%=nombre%>
            </option>
            <% } %>
        </select>
        
    </form>
</main>
</body>

</html>