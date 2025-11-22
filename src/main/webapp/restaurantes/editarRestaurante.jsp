<%@page import="com.unu.practicandoweb.beans.Restaurante"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<%
String url = "http://localhost:8080/PROYECTO_POO-II/";
Restaurante restaurante = (Restaurante) request.getAttribute("restaurante");

%>

<form action="<%=url%>restauranteControlador" method="post">

	<input type="hidden" name="op" value="modificarAjax"> 
	<input type="hidden" name="id" value="<%=restaurante.getIdRestaurante()%>">

	<!-- Restaurante -->
	<div class="mb-3">
		<label class="form-label">Restaurante</label> <input type="text"
			class="form-control" name="razonsocial"
			value="<%=restaurante.getRazonSocial()%>" required>
	</div>

	<!-- RUC -->
	<div class="mb-3">
		<label class="form-label">RUC</label> <input type="text"
			class="form-control" name="ruc" value="<%=restaurante.getRUC()%>"
			required>
	</div>

	<!-- Serie -->
	<div class="mb-3">
		<label class="form-label">Serie</label> <input type="text"
			class="form-control" name="serie" value="<%=restaurante.getSerie()%>"
			required>
	</div>


	<!-- Botones -->
	<div class="d-flex justify-content-between mt-4">
		<a href="<%=url%>restauranteControlador?op=listar"
			class="btn btn-secondary"> Retornar </a>

		<button type="submit" class="btn btn-success">Guardar Cambios
		</button>
	</div>

</form>


<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
// Activar validación de Bootstrap
(function() {
    'use strict';
    const form = document.getElementById('formRestaurant');
    if(form) {
        form.classList.add('was-validated');
    }
})();
</script>

