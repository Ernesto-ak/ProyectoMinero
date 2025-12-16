<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">


<%
String url = "http://localhost:8080/PROYECTO_POO-II/restauranteControlador";
%>

                    <form action="<%=url%>" method="post">
                        <input type="hidden" name="op" value="insertarAjax">

                        <!-- Restaurante -->
                        <div class="mb-3">
                            <label class="form-label">Restaurante</label>
                            <input type="text" class="form-control" 
                                   name="restaurante" id="restaurante"
                                   placeholder="Ingrese un nuevo restaurante" required>
                        </div>

                        <!-- RUC -->
                        <div class="mb-3">
                            <label class="form-label">RUC</label>
                            <input type="text" class="form-control" 
                                   name="ruc" id="ruc"
                                   placeholder="Ingrese RUC" required>
                        </div>

                        <!-- Serie -->
                        <div class="mb-3">
                            <label class="form-label">Serie</label>
                            <input type="text" class="form-control" 
                                   name="serie" id="serie"
                                   placeholder="Ingrese Serie" required>
                        </div>

                        <!-- Botones -->
                        <div class="d-flex justify-content-between mt-4">
                            <a href="javascript:history.back()" class="btn btn-secondary">
                                Volver
                            </a>
                            <button type="submit" class="btn btn-success">
                                Guardar
                            </button>
                        </div>

                    </form>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
