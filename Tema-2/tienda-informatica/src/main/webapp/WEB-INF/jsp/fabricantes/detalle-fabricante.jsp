<%@ page import="org.iesbelen.model.FabricanteDTO" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle Fabricante</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<style>

</style>
</head>
<body>

	<main>
		<% 	Optional<FabricanteDTO> optFab = (Optional<FabricanteDTO>)request.getAttribute("fabricante");
			if (optFab.isPresent()) {
		%>

	</main>
		
		
		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 33%">
				<label>Código</label>
			</div>
			<div style="float: none;width: auto;overflow: hidden;">
				<input value="<%= optFab.get().getIdFabricante() %>" readonly="readonly"/>
			</div> 
		</div>
		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 33%">
				<label>Nombre</label>
			</div>
			<div style="float: none;width: auto;overflow: hidden;">
				<input value="<%= optFab.get().getNombre() %>" readonly="readonly"/>
			</div> 
		</div>
		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 33%">
				<label>Cantidad de productos</label>
			</div>
			<div style="float: none;width: auto;overflow: hidden;">
				<input value="<%= optFab.get().getCantidadProductos() %>" readonly="readonly"/>
			</div>
		</div>
		
		<% 	} else { %>
			
				request.sendRedirect("fabricantes/");
		
		<% 	} %>
		
</div>

</body>
</html>