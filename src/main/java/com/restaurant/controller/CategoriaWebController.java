package com.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.model.Categoria;
import com.restaurant.service.CategoriaService;



@Controller
@RequestMapping("/web/categorias")
public class CategoriaWebController {
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String listarCategorias(Model model) {
		model.addAttribute("categorias", categoriaService.obtenerTodos());
		return "categorias/lista";
	}
	
	@GetMapping("/nuevo")
	public String formularioNuevo(Model model) {
		model.addAttribute("categoria",new Categoria());
		return "categorias/formulario";
	}
	
	@GetMapping("/{id}/editar")
	public String formularioEditar(@PathVariable Integer id, Model model) {
		Optional<Categoria> categoria = categoriaService.obtenerPorId(id);
		if(categoria.isPresent()) {
			model.addAttribute("categoria",categoria.get());
			return "categorias/formulario";
		}
		return "redirect:/web/categorias";
		
	}
	
	@PostMapping
	public String guardarCategoria(@ModelAttribute Categoria categoria) {
		categoriaService.guardar(categoria);
		return "redirect:/web/categorias";
	}
	
	@GetMapping("/{id}/eliminar")
	public String eliminarCategoria(@PathVariable Integer id) {
		categoriaService.eliminar(id);
		return "redirect:/web/categorias";
		
	}
}
