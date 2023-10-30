<%@ page contentType="text/html; charset=UTF-8" %>
    <html>

    <head>
        <title>Formulario</title>
        <link rel="stylesheet" href="./css/index.css">
    </head>

    <body>
        <main>
            <form action="${pageContext.request.contextPath}/person" method="POST">
                <h2>Datos Personales</h2>
                <div class="wrapper">

                    <div class="container">
                        <label for="name-input" class="bold">Nombre:</label>
                        <input type="text" name="name" id="name-input">
                    </div>

                    <div class="container">
                        <label for="surname-input" class="bold">Apellidos:</label>
                        <input type="text" name="surnames" id="surname-input">
                    </div>

                    <div class="container">
                        <label class="bold">Edad:</label>
                        <select name="age">
                            <option value="">...</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="30">30</option>
                            <option value="30">30</option>
                        </select>
                    </div>

                    <div class="container">
                        <label class="bold">Peso:</label>
                        <label><input type="number" name="weight"> kg</label>
                    </div>

                    <div class="container">
                        <label class="bold">Sexo:</label>
                        <span>
                            <label><input type="radio" value="Hombre" name="gender">Hombre</label>
                            <label><input type="radio" value="Mujer" name="gender">Mujer</label>
                        </span>
                    </div>

                    <div class="container">
                        <label class="bold">Estado Civil:</label>
                        <span class="marital-container">
                            <label><input type="radio" value="Soltero" name="marital">Soltero</label>
                            <label><input type="radio" value="Casado" name="marital">Casado</label>
                            <label><input type="radio" value="Otro" name="marital">Otro</label>
                        </span>
                    </div>
                </div>

                <div class="wrapper-2">
                    <div class="hobbies-container">
                        <label class="bold">Aficiones:</label>
                        <div class="hobbies-inputs-container">
                            <label><input name="hobbies" value="Cine" type="checkbox">Cine</label>
                            <label><input name="hobbies" value="Literatura" type="checkbox">Literatura</label>
                            <label><input name="hobbies" value="Tebeos" type="checkbox">Tebeos</label>
                            <label><input name="hobbies" value="Deportes" type="checkbox">Deportes</label>
                            <label><input name="hobbies" value="Música" type="checkbox">Música</label>
                            <label><input name="hobbies" value="Televisión" type="checkbox">Televisión</label>
                        </div>
                    </div>

                    <div>
                        <button>Enviar</button>
                        <button type="reset">Borrar</button>
                    </div>
                </div>
            </form>
        </main>
    </body>

    </html>