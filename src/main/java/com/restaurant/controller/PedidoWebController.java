package com.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.model.Pedidos;
import com.restaurant.service.PedidoService;
import com.restaurant.service.UsuarioService;

@Controller
@RequestMapping("/web/pedidos")
public class PedidoWebController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public String listarPedidos(Model model) {
		model.addAttribute("pedidos", pedidoService.obtenerTodo());
		return "pedidos/lista";
	}

	@GetMapping("/nuevo")
	public String formularioNuevo(Model model) {
		model.addAttribute("pedido", new Pedidos());
		model.addAttribute("usuarios", usuarioService.obtenerTodos());
		return "pedidos/formulario";
	}

	@PostMapping
	public String guardarPedido(Pedidos pedido) {

		pedidoService.guardar(pedido);

		return "redirect:/web/pedidos";
	}

	@GetMapping("/{id}/editar")
	public String editar(@PathVariable Integer id, Model model) {
		Optional<Pedidos> pedido = pedidoService.buscarPorId(id);

		if (pedido != null && !pedido.isEmpty()) {
			model.addAttribute("pedido", pedido.get());
			model.addAttribute("usuarios", usuarioService.obtenerTodos());
			return "pedidos/formulario";
		}
		return "redirect:/web/pedidos";
	}

}
