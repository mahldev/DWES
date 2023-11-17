<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@ page import="static java.util.Objects.isNull" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Productos</title>
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


        #search-bar {
            flex: 1;
            padding: .5em .8em;
            border: 1px solid #ddd;
            border-radius: 3px;
            margin-bottom: 1.5em;
        }

        #search-form {
            display: flex;
            width: 90%;
        }

        #search-bar::placeholder {
            font-style: italic;
        }

        #search-form[hidden] {
            display: none;
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

        .icon-svg:active {
            display: inline-block;
            position: relative;
            transform: translateY(1px)
        }
    </style>
</head>

<body>

<%@include file="../../components/header.jspf" %>

<main>
    <div class="wrapper-main">

        <h2>Productos</h2>
        <div class="wrapper-option">
            <button id="search" class="button">
                <svg xmlns="http://www.w3.org/2000/svg" class="icon-svg" width="24" height="24"
                     viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                    <path d="M10 10m-7 0a7 7 0 1 0 14 0a7 7 0 1 0 -14 0"></path>
                    <path d="M21 21l-6 -6"></path>
                </svg>
            </button>

            <button id="filter" class="button">
                <svg class="icon-svg" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                     viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                    <path d="M4 4h16v2.172a2 2 0 0 1 -.586 1.414l-4.414 4.414v7l-6 2v-8.5l-4.48 -4.928a2 2 0 0 1 -.52 -1.345v-2.227z"></path>
                </svg>
            </button>

            <button class="button">
                <svg class="icon-svg" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                     stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                    <path d="M12 21h-7a2 2 0 0 1 -2 -2v-14a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v7"></path>
                    <path d="M3 10h18"></path>
                    <path d="M10 3v18"></path>
                    <path d="M19.001 19m-2 0a2 2 0 1 0 4 0a2 2 0 1 0 -4 0"></path>
                    <path d="M19.001 15.5v1.5"></path>
                    <path d="M19.001 21v1.5"></path>
                    <path d="M22.032 17.25l-1.299 .75"></path>
                    <path d="M17.27 20l-1.3 .75"></path>
                    <path d="M15.97 17.25l1.3 .75"></path>
                    <path d="M20.733 20l1.3 .75"></path>
                </svg>
            </button>
            <form action="${pageContext.request.contextPath}/tienda/productos/crear">
                <button class="button">
                    <svg class="icon icon-tabler icon-tabler-plus" width="24" height="24" viewBox="0 0 24 24"
                         stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round"
                         stroke-linejoin="round">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                        <path d="M12 5l0 14"></path>
                        <path d="M5 12l14 0"></path>
                    </svg>
                </button>
            </form>
        </div>

    </div>

    <%
        String searchFormHidden = (String) request.getAttribute("searchFormHidden");
        searchFormHidden = searchFormHidden == null ? "true" : searchFormHidden;
        String searchQuery = (String) request.getAttribute("searchQuery");
        if (isNull(searchQuery)) {
            searchQuery = "";
        }
    %>
    <form <%="true".equals(searchFormHidden) ? "hidden" : "" %>
            action="${pageContext.request.contextPath}/tienda/productos" method="get"
            id="search-form">
        <input type="search" name="filtrar-por-nombre" id="search-bar"
               placeholder="Monitor, Portatil, Disco... " value="<%=searchQuery%>" autofocus>
        <button class="icon"></button>
        <input type="hidden" name="searchFormHidden" value="<%= searchFormHidden%>">
    </form>

    <%
        List<String> productoColumnNames = List.of("ID", "Nombre", "Precio");
        List<String> productoPropertyNames = List.of("idProducto", "nombre", "precio");
        String productoElementPath = "/tienda/productos";
        String productoIdPropertyName = "idProducto";
        request.setAttribute("columnNames", productoColumnNames);
        request.setAttribute("propertyNames", productoPropertyNames);
        request.setAttribute("elementPath", productoElementPath);
        request.setAttribute("idPropertyName", productoIdPropertyName);
        request.setAttribute("listaElementos", request.getAttribute("listaProductos"));
    %>
    <%@include file="../../components/table.jspf" %>

</main>
<script>
    const searhForm = document.querySelector('#search-form')
    const searchBar = document.querySelector('#search-bar')
    const toggleHidden = (element) => {
        element.hidden = !element.hidden;
        document.querySelector('input[name="searchFormHidden"]').value = element.hidden.toString();
    };
    let intervalid

    document.querySelector('#search-bar').addEventListener('keyup', () => {
        clearInterval(intervalid)
        intervalid = setInterval(() => {
            searhForm.submit()
        }, 300)
    })

    document.querySelector('#search').addEventListener('click', () => {
        toggleHidden(searhForm)
    })

    document.addEventListener('DOMContentLoaded', () => {
        searchBar.setSelectionRange(searchBar.value.length, searchBar.value.length)
    })

</script>
</body>

</html>