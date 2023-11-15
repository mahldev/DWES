<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
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
            max-width: 1000px;
            margin-left: auto;
            margin-right: auto;
            position: relative;
        }

        table {
            border-collapse: collapse;
            margin-bottom: 20px;
            width: 90%;
        }

        thead td {
            font-weight: bold;
            color: #212529;
        }

        tbody td {
            min-width: 150px;
        }

        th,
        td {
            border-bottom: 1px solid #dddddd;
            padding: 8px;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #d7d7d7;
        }

        .wrapper-main {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            margin-bottom: 2em;
        }

        .crearNuevoUsuario-Button {
            height: 40px;
            color: #fff;
            padding: 10px 15px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            border-radius: 20px;
            border: 2px solid;
            outline: none;
            background: #000;
        }

        .crearNuevoUsuario-Button:hover {
            background: #fff;
            color: #000
        }

        .crearNuevoUsuario-Button:active {
            transition: none;
            background: #000;
            color: #fff;
        }

        .wrapper {
            display: flex;
            justify-content: end;
            gap: .5em;
        }

        .icon {
            background-color: transparent;
            border: none;
            cursor: pointer;
        }
    </style>
    <title>Usuario</title>
</head>
<body>
<%@include file="../../components/header.jspf" %>

<main>
    <div class="wrapper-main">
        <h3>Usuarios</h3>
        <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
            <button class="crearNuevoUsuario-Button">+ Crear nuevo</button>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>Usuario</td>
            <td>Password</td>
            <td>Rol</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <% List<Usuario> listaUsuarios = (List<Usuario>)
                request.getAttribute("listaUsuarios");

            for (Usuario usuario : listaUsuarios) {
        %>
        <tr>
            <td>
                <a
                        href="${pageContext.request.contextPath}/tienda/usuarios/<%=usuario.getId()%>">
                    <%=usuario.getId()%>
                </a>
            </td>
            <td>
                <a
                        href="${pageContext.request.contextPath}/tienda/usuarios/<%=usuario.getUsuario()%>">
                    <%=usuario.getUsuario()%>
                </a>
            </td>
            <td>
                <a
                        href="${pageContext.request.contextPath}/tienda/usuarios/<%=usuario.getUsuario()%>">
                    <%=usuario.getPassword()%>
                </a>
            </td>
            <td>
                <a
                        href="${pageContext.request.contextPath}/tienda/usuarios/<%=usuario.getUsuario()%>">
                    <%=usuario.getRol()%>
                </a>
            </td>
            <td>
                <div class="wrapper">
                    <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%=usuario.getUsuario()%>">
                        <button class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-pencil"
                                 width="24"
                                 height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none"
                                 stroke-linecap="round" stroke-linejoin="round">
                                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                                <path d="M4 20h4l10.5 -10.5a2.828 2.828 0 1 0 -4 -4l-10.5 10.5v4"></path>
                                <path d="M13.5 6.5l4 4"></path>
                            </svg>
                        </button>
                    </form>
                    <form action="${pageContext.request.contextPath}/tienda/usuarios/borrar/" method="post">
                        <input type="hidden" name="__method__" value="delete"/>
                        <input type="hidden" name="codigo" value="<%= usuario.getId()%>"/>
                        <button class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg"
                                 class="icon icon-tabler icon-tabler-trash" width="24"
                                 height="24" viewBox="0 0 24 24" stroke-width="2"
                                 stroke="currentColor" fill="none" stroke-linecap="round"
                                 stroke-linejoin="round">
                                <path stroke="none" d="M0 0h24v24H0z" fill="none">
                                </path>
                                <path d="M4 7l16 0"></path>
                                <path d="M10 11l0 6"></path>
                                <path d="M14 11l0 6"></path>
                                <path
                                        d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12">
                                </path>
                                <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3">
                                </path>
                            </svg>
                        </button>
                    </form>
                </div>

            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</main>
</body>
</html>
