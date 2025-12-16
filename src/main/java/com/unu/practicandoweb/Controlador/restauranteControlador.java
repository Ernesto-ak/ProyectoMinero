package com.unu.practicandoweb.Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.unu.practicandoweb.beans.Restaurante;
import com.unu.practicandoweb.modelo.restauranteModelo;

/**
 * Servlet implementation class restauranteControlador
 */
@WebServlet("/restauranteControlador")
@MultipartConfig
public class restauranteControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	restauranteModelo modelo = new restauranteModelo();

	public restauranteControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void processRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("op");
		if (operacion == null) {
			operacion = "listar";
		}

		switch (operacion) {
		case "listar":
			this.listar(request, response);
			break;
		case "nuevo":
            nuevo(request, response);
            break;
		case "insertarAjax":
			this.insertar(request, response);
			break;
		case "editar":
			editar(request,response);
			break;
		case "eliminar":
			this.eliminar(request, response);
			break;

		case "modificarAjax":
			this.modificar(request, response);
			break;

		}
	}

	public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html , UTF-8");
		request.setAttribute("listaRestaurantes", modelo.listaRestaurante());
		request.getRequestDispatcher("/restaurantes/listaRestaurante.jsp").forward(request, response);
	}
	public void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html , UTF-8");
		
		request.getRequestDispatcher("/restaurantes/nuevoRestaurante.jsp").forward(request, response);
	}
	public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html , UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("restaurante", modelo.obtenerRestaurante(id));
		request.getRequestDispatcher("/restaurantes/editarRestaurante.jsp").forward(request, response);
	}
	public void insertar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html , UTF-8");
		Restaurante restaurante = new Restaurante();
		restaurante.setRazonSocial(request.getParameter("restaurante"));
		restaurante.setRUC(request.getParameter("ruc"));
		restaurante.setSerie(request.getParameter("serie"));
		int resultado=modelo.insertarRestaurante(restaurante);
		enviarJSON(response, resultado > 0, resultado > 0 ? "Restaurant registrado exitosamente" : "Error al registrar Restaurant");
	}

	public void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html , UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		modelo.eliminarRestaurante(id);
		response.sendRedirect("restauranteControlador?op=listar");
	}

	public void modificar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html , UTF-8");
		Restaurante restaurante = new Restaurante();
		restaurante.setIdRestaurante(Integer.parseInt(request.getParameter("id")));
		restaurante.setRazonSocial(request.getParameter("razonsocial"));
		restaurante.setRUC(request.getParameter("ruc"));
		restaurante.setSerie(request.getParameter("serie"));
		int resultado=modelo.modificarRestaurante(restaurante);
		enviarJSON(response, resultado > 0, resultado > 0 ? "Restaurant modificar exitosamente" : "Error al modificar Restaurant");
		listar(request, response);
	}

	
	  private void enviarJSON(HttpServletResponse response, boolean success, String mensaje) {
	        try {
	            response.reset();
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.setHeader("Cache-Control", "no-cache");

	            String mensajeLimpio = mensaje.replace("\"", "'").replace("\n", " ").replace("\r", " ");
	            String json = "{\"success\":" + success + ",\"mensaje\":\"" + mensajeLimpio + "\"}";

	            PrintWriter out = response.getWriter();
	            out.write(json);
	            out.flush();
	            out.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequests(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequests(request, response);
	}

}
