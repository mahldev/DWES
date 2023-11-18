<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
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
            max-width: 1000px;
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
            display: flex;
            align-items: center;
            padding: 10px 10px;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            border-radius: 46px;
            border: 2px solid;
            outline: none;
            background: #000;
            gap: 5px;
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

        .wrapper-option {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 5px;
        }

        .icon-svg {
            cursor: pointer;
            animation: border .3ms;
        }

        .icon-svg:hover {}

        .icon-svg:active {
            display: inline-block;
            position: relative;
            transform: translateY(1px)
        }
        
    </style>
    <title>Usuario</title>
</head>

<body>
<%@include file="../../components/header.jspf" %>

<main>
    <div class="wrapper-main">
        <h2>Usuarios</h2>
        <div class="wrapper-option">

            <button class="button">
                <svg class="icon-svg" width="24" height="24" viewBox="0 0 24 24" stroke-width="2"
                     stroke="currentColor" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <path d="M10 10m-7 0a7 7 0 1 0 14 0a7 7 0 1 0 -14 0"/>
                    <path d="M21 21l-6 -6"/>
                </svg>
            </button>

            <button class="button">
                <svg id="filter" class="icon-svg" width="24" height="24" viewBox="0 0 24 24"
                     stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <path
                            d="M4 4h16v2.172a2 2 0 0 1 -.586 1.414l-4.414 4.414v7l-6 2v-8.5l-4.48 -4.928a2 2 0 0 1 -.52 -1.345v-2.227z"/>
                </svg>
            </button>

            <button class="button">
                <svg class="icon-svg" width="24" height="24" viewBox="0 0 24 24" stroke-width="2"
                     stroke="currentColor" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <path d="M12 21h-7a2 2 0 0 1 -2 -2v-14a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v7"/>
                    <path d="M3 10h18"/>
                    <path d="M10 3v18"/>
                    <path d="M19.001 19m-2 0a2 2 0 1 0 4 0a2 2 0 1 0 -4 0"/>
                    <path d="M19.001 15.5v1.5"/>
                    <path d="M19.001 21v1.5"/>
                    <path d="M22.032 17.25l-1.299 .75"/>
                    <path d="M17.27 20l-1.3 .75"/>
                    <path d="M15.97 17.25l1.3 .75"/>
                    <path d="M20.733 20l1.3 .75"/>
                </svg>
            </button>


            <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
                <button class="button">
                    <svg class="icon-svg" width="24" height="24" viewBox="0 0 24 24"
                         stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round"
                         stroke-linejoin="round">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                        <path d="M12 5l0 14"/>
                        <path d="M5 12l14 0"/>
                    </svg>
                </button>
            </form>
        </div>

    </div>

    <%
        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
        listaUsuarios = listaUsuarios.stream()
                .map(user -> {
                    user.setPassword(user.getPassword().substring(0, 4));
                    return user;
                })
                .toList();
        List<String> columnNamesUsuarios = List.of("ID", "Usuario", "Password", "Rol");
        List<String> propertyNamesUsuarios = List.of("idUsuario", "usuario", "password", "rol");
        String elementPathUsuarios = "/tienda/usuarios";
        String idPropertyNameUsuarios = "idUsuario";
        request.setAttribute("columnNames", columnNamesUsuarios);
        request.setAttribute("propertyNames", propertyNamesUsuarios);
        request.setAttribute("elementPath", elementPathUsuarios);
        request.setAttribute("idPropertyName", idPropertyNameUsuarios);
        request.setAttribute("listaElementos", listaUsuarios);
    %>

    <%@ include file="../../components/table.jspf" %>

</main>
</body>

</html>