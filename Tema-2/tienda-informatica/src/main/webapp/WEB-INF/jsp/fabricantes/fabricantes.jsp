<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.FabricanteDTO" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fabricantes</title>
	<style>
		.clearfix::after {
			content: "";
			display: block;
			clear: both;
		}
	</style>
</head>
<body>

	<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
		<div class="clearfix">
			<div style="float: left; width: 50%">
				<h1>Fabricantes</h1>
			</div>

			<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
				<div style="position: absolute; left: 39%; top : 39%;">

					<form action="${pageContext.request.contextPath}/tienda/fabricantes/crear">
						<input type="submit" value="Crear">
					</form>
				</div>
				
			</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>


		<form action="${pageContext.request.contextPath}/tienda/fabricantes" method="get">
			<select name="ordenar-por">
				<option value="codigo">Codigo</option>
				<option value="nombre">Nombre</option>
			</select>
			<select name="modo-ordenar">
				<option value="asc">Ascendente</option>
				<option value="desc">Descendente</option>
			</select>
			<button>Ordenar</button>
		</form>

		<div class="clearfix">
			<hr/>
		</div>
		<div class="clearfix">
			<div style="float: left;width: 25%">Código</div>
			<div style="float: left;width: 25%">Nombre</div>
			<div style="float: left;width: 25%">Cantidad de Productos</div>
			<div style="float: none;width: auto;overflow: hidden;">Acción</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
	<% 
        if (request.getAttribute("listaFabricantes") != null) {
            List<FabricanteDTO> listaFabricante = (List<FabricanteDTO>)request.getAttribute("listaFabricantes");
            
            for (FabricanteDTO fabricante : listaFabricante) {
    %>

		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 25%"><%= fabricante.getIdFabricante()%></div>
			<div style="float: left;width: 25%"><%= fabricante.getNombre()%></div>
			<div style="float: left;width: 25%"><%= fabricante.getCantidadProductos()%></div>
			<div style="float: none;width: auto;overflow: hidden;">
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/<%= fabricante.getIdFabricante()%>" style="display: inline;">
    				<input type="submit" value="Ver Detalle" />
				</form>
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/editar/<%= fabricante.getIdFabricante()%>" style="display: inline;">
    				<input type="submit" value="Editar" />
				</form>
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/borrar/" method="post" style="display: inline;">
					<input type="hidden" name="__method__" value="delete"/>
					<input type="hidden" name="codigo" value="<%= fabricante.getIdFabricante()%>"/>
    				<input type="submit" value="Eliminar" />
				</form>
			</div>
		</div>

	<% 
            }
        } else { 
    %>
		No hay registros de fabricante
	<% } %>
	</div>
</body>
</html>