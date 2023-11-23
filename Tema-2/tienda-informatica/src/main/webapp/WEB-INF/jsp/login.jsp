<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Iniciar Sesion</title>
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
            display: grid;
            place-items: center;
            height: 80vh;
        }

        main {
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #fff;
            border-radius: 8px;
            padding: 3em;
            max-width: 370px;
            margin-left: auto;
            margin-right: auto;
            gap: 2em;
            height: 400px;
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
            min-width: 130px;
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
            row-gap: 1em;
        }

        #datos input {
            padding: .5em .8em;
            border: 1px solid #ddd;
            border-radius: 3px;
            min-width: 270px;
        }

        #datos input:focus {
            outline: none;
            border: 1px solid #8e8e8e;
        }

        label {
            display: flex;
            flex-direction: column;
            gap: 5px;
            font-size: large;
        }

        .buttons {
            display: flex;
            gap: 5px;
            height: 62px;
            align-items: end;
        }
    </style>
</head>

<body>
<%
    String referer = request.getHeader("Referer");
%>

<main>
    <h2>Bienvenido</h2>
    <form id="datos" action="${pageContext.request.contextPath}/tienda/login" method="post">

        <label>Usuario
            <input type="text" name="usuario">
        </label>

        <label>Contraseña
            <input type="password" name="password">
        </label>

    </form>

    <div class="buttons">
        <button form="volver" class="button">Volver</button>
        <button form="datos" class="button">Iniciar sesion</button>
    </div>

    <form hidden id="volver"
          action="<%=referer%>">
    </form>
</main>

</body>

</html>