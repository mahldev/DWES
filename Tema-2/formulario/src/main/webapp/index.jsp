<%@ page import="org.iesbelen.utils.CreationPersonResult" %>
<%@ page import="org.iesbelen.utils.ValidateResult" %>
<%@ page import="org.iesbelen.entitys.Person" %>
<%@ page import="org.iesbelen.utils.ValidationError" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    var creationResult = (CreationPersonResult) request.getAttribute("creationResult");
    creationResult = (creationResult == null) ? new CreationPersonResult(new Person()) : creationResult;
    var person = creationResult.getPerson();
    ValidateResult resultErrors = creationResult.getErrors();
%>
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
                <input type="text" name="name" id="name-input"
                       value="<%= resultErrors.getErrors().containsKey("name") ?  "" : person.getName() %>">
            </div>

            <div class="container">
                <label for="surname-input" class="bold">Apellidos:</label>
                <input type="text" name="surnames" id="surname-input"
                       value="<%= resultErrors.getErrors().containsKey("surname") ? "" : person.getSurnnames()%>">
            </div>

            <div class="container">

                <label class="bold">Edad:</label>
                <select name="age">
                    <option <%= person.getAge() == 0 ? "selected" : ""%> value="">...</option>
                    <option <%= person.getAge() == 18 ? "selected" : ""%> value="18">18</option>
                    <option <%= person.getAge() == 19 ? "selected" : ""%> value="19">19</option>
                    <option <%= person.getAge() == 20 ? "selected" : ""%> value="20">20</option>
                    <option <%= person.getAge() == 21 ? "selected" : ""%> value="21">21</option>
                    <option <%= person.getAge() == 22 ? "selected" : ""%> value="22">22</option>
                    <option <%= person.getAge() == 23 ? "selected" : ""%> value="23">23</option>
                    <option <%= person.getAge() == 24 ? "selected" : ""%> value="24">24</option>
                    <option <%= person.getAge() == 25 ? "selected" : ""%> value="25">25</option>
                    <option <%= person.getAge() == 26 ? "selected" : ""%> value="26">26</option>
                    <option <%= person.getAge() == 27 ? "selected" : ""%> value="27">27</option>
                    <option <%= person.getAge() == 28 ? "selected" : ""%> value="28">28</option>
                    <option <%= person.getAge() == 29 ? "selected" : ""%> value="29">29</option>
                    <option <%= person.getAge() == 30 ? "selected" : ""%> value="30">30</option>
                </select>
            </div>

            <div class="container">
                <label class="bold">Peso:</label>
                <label><input type="number" name="weight"
                              value="<%= resultErrors.getErrors().containsKey("age") ? "" : person.getWeight()%>">
                    kg</label>
            </div>

            <div class="container">
                <label class="bold">Sexo:</label>
                <span>
                            <label><input type="radio" value="Male" name="gender"
                                <%= person.getGender().equals("Male") ? "checked" : ""%>
                            >Hombre</label>
                            <label><input type="radio" value="Female" name="gender"
                                <%= person.getGender().equals("Female") ? "checked" : ""%>
                            >Mujer</label>
                        </span>
            </div>

            <div class="container">
                <label class="bold">Estado Civil:</label>
                <span class="marital-container">
                            <label><input type="radio" value="Single" name="marital"
                                <%= person.getMaritalStatus().equals("Single") ? "checked" : ""%>
                            >Soltero</label>
                            <label><input type="radio" value="Married" name="marital"
                                <%= person.getMaritalStatus().equals("Married") ? "checked" : ""%>
                            >Casado</label>
                            <label><input type="radio" value="Other" name="marital"
                                <%= person.getMaritalStatus().equals("Other") ? "checked" : ""%>
                            >Otro</label>
                        </span>
            </div>
        </div>

        <div class="wrapper-2">
            <div class="hobbies-container">
                <label class="bold">Aficiones:</label>
                <div class="hobbies-inputs-container">
                    <label><input name="hobbies" value="Cine" type="checkbox"
                        <%= person.getHobbies().contains("Cine") ? "checked" : ""%>
                    >Cine</label>
                    <label><input name="hobbies" value="Literatura" type="checkbox"
                        <%= person.getHobbies().contains("Literatura") ? "checked" : ""%>
                    >Literatura</label>
                    <label><input name="hobbies" value="Tebeos" type="checkbox"
                        <%= person.getHobbies().contains("Tebeos") ? "checked" : ""%>
                    >Tebeos</label>
                    <label><input name="hobbies" value="Deportes" type="checkbox"
                        <%= person.getHobbies().contains("Deportes") ? "checked" : ""%>
                    >Deportes</label>
                    <label><input name="hobbies" value="Música" type="checkbox"
                        <%= person.getHobbies().contains("Música") ? "checked" : ""%>
                    >Música</label>
                    <label><input name="hobbies" value="Televisión" type="checkbox"
                        <%= person.getHobbies().contains("Televisión") ? "checked" : ""%>
                    >Televisión</label>
                </div>
            </div>

            <div>
                <button>Enviar</button>
                <button type="reset">Borrar</button>
            </div>
        </div>
    </form>

    <div class="error-container">
        <%
            for (ValidationError error : resultErrors.getErrors().values()) {
                String message = error.getMessage();
        %>
        <p class="error-message">
            <%= message %>
        </p>
        <%
            }
        %>
    </div>

</main>
</body>

</html>