<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="org.iesbelen.model.Fabricante" %>
<%@page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Fabricante</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            font-family: system-ui;
        }

        body {
            background-color: #f1f1f1;
            margin: 0 auto;
        }

        main {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: #fff;
            border-radius: 8px;
            padding: 3em;
            margin-top: 2em;
            max-width: 800px;
            margin-left: auto;
            margin-right: auto;
            position: relative;
        }

        .wrapper-main {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            margin-bottom: 2em;
        }

        .button {
            height: 40px;
            color: #fff;
            padding: 10px 15px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            border-radius: 20px;
            border: 2px solid #000;
            outline: none;
            background: #000;
        }

        .button:hover {
            background: #fff;
            color: #000
        }

        .button:active {
            transition: none;
            background: #000;
            color: #fff;
        }

        #datos {
            display: grid;
            grid-template-columns: 1fr 3fr;
            width: 100%;
            align-items: center;
            gap: 1em;
        }

        #datos input {
            padding: .5em .8em;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

    </style>
</head>
<body>
<%@include file="../../components/header.jspf" %>

<main>
    <% Optional<Fabricante> optFab = (Optional<Fabricante>) request.getAttribute("fabricante");
        if (optFab.isPresent()) {
    %>
    <div class="wrapper-main">
        <h3>Editar Fabricante</h3>
        <button form="datos" class="crearNuevoFab-Button">Guardar</button>
    </div>


    <form id="datos" action="${pageContext.request.contextPath}/tienda/fabricantes/editar/" method="post">
        <input type="hidden" name="__method__" value="put" />

        <label for="codigo">Código</label>
        <input id="codigo" type="text" name="codigo" value="<%= optFab.get().getIdFabricante() %>">

        <label for="nombre">Nombre</label>
        <input id="nombre" type="text" name="nombre" value="<%= optFab.get().getNombre() %>">

    </form>
    <% }
        else { %>
        <h2>404 - Error: No se encontró al fabricante</h2>
    <%}%>
</main>

</body>
</html>