<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>
    <title>Crear usuario</title>
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
            width: 90%;
            align-items: center;
            row-gap: 1em;
        }

        #datos input,
        #datos select {
            padding: .5em .8em;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

        #datos input::placeholder {
            font-style: italic;
        }

        #datos input:focus,
        #datos select:focus {
            outline: none;
            border: 1px solid #8e8e8e;
        }
    </style>
</head>

<body>

<%@include file="../../components/header.jspf" %>


<main>


    <div class="wrapper-main">
        <h3>Nuevo Usuario</h3>
        <button form="datos" class="button">Crear</button>
    </div>


    <form id="datos" action="${pageContext.request.contextPath}/tienda/usuarios/" method="post">

        <label for="usuario">Nombre</label>
        <input type="text" name="usuario" id="usuario" placeholder="Juan, Frank, Emilio...">

        <label for="password">Password</label>
        <input type="password" name="password" id="password" placeholder="12345678">

        <label for="rol">Rol</label>
        <select id="rol" name="rol">
            <option value="Administrador">Administrador</option>
            <option value="Vendedor">Vendedor</option>
            <option value="Cliente">Cliente</option>
        </select>
    </form>
</main>
</body>

</html>