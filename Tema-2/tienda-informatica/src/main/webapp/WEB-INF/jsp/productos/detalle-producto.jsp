<%@ page import="static org.iesbelen.utils.HTMLGenerator.getProductoHTML" %>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% Optional<Producto> producto = (Optional<Producto>) request.getAttribute("producto");%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>detalles producto</title>

    <style>
        :root {
            margin: 0;
            padding: 0;
            box-sizing: border-box
        }

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

        main {
            display: flex;
            flex-direction: column;
            margin-top: 1em;
        }

        .productos {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: .5em; 
        }
        
        input {
            max-width: 177px;
        }

    </style>
</head>

<body>

    <header>
        <h1>Detalle Producto</h1>
        <form action="${pageContext.request.contextPath}/tienda/productos/">
            <button>Volver</button>
        </form>
    </header>

    <main>
       <%= getProductoHTML(producto)%>
    </main>
</body>

</html>
