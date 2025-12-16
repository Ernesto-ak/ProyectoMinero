package com.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurant.model.DetallePedidos;
import com.restaurant.model.Pedidos;
import com.restaurant.model.Producto;
import com.restaurant.model.Usuario;
import com.restaurant.service.DetallePedidoService;
import com.restaurant.service.PedidoService;
import com.restaurant.service.ProductoService;

import jakarta.servlet.http.HttpSession;
=======

import com.restaurant.model.Pedidos;
import com.restaurant.service.PedidoService;
import com.restaurant.service.UsuarioService;
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c

@Controller
@RequestMapping("/web/pedidos")
public class PedidoWebController {

	@Autowired
<<<<<<< HEAD
	private DetallePedidoService detallePedidoService;

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProductoService productoService;

	PedidoWebController(DetallePedidoService detallePedidoService) {
		this.detallePedidoService = detallePedidoService;
	}
=======
	private PedidoService pedidoService;
	@Autowired
	private UsuarioService usuarioService;
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c

	@GetMapping
	public String listarPedidos(Model model) {
		model.addAttribute("pedidos", pedidoService.obtenerTodo());
<<<<<<< HEAD
		// model.addAttribute("pedidos",pedidoService)
=======
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
		return "pedidos/lista";
	}

	@GetMapping("/nuevo")
	public String formularioNuevo(Model model) {
		model.addAttribute("pedido", new Pedidos());
<<<<<<< HEAD
		// model.addAttribute("usuarios", usuarioService.obtenerTodos());
		return "pedidos/formulario";
	}

	@PostMapping("/pedido")
	public String pedirProducto(@RequestParam("pProducto") Integer productoId,
			@RequestParam("pRestaurant") Integer restaurantId, @RequestParam Integer cantidad, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/login";
		}

		Pedidos pedido = pedidoService.buscarPorUsuarioEstado(usuario, "ACTIVO");

		if (pedido == null) {
			pedido = new Pedidos();
			pedido.setUsuario(usuario);
			pedido.setEstado("ACTIVO");
			pedido.setTotal(0.0);
			pedidoService.guardar(pedido);
		}

		Producto producto = productoService.obtenerPorId(productoId)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		DetallePedidos detalle = new DetallePedidos();
		detalle.setPedido(pedido);
		detalle.setProducto(producto);
		detalle.setCantidad(cantidad);
		detalle.setSubtotal(producto.getPrecioUnitario() * cantidad);

		detallePedidoService.guardar(detalle);

		// 3️⃣ Actualizar total
		pedido.setTotal(pedido.getTotal() + detalle.getSubtotal());
		pedidoService.guardar(pedido);

		return "redirect:/web/productos";
=======
		model.addAttribute("usuarios", usuarioService.obtenerTodos());
		return "pedidos/formulario";
	}

	@PostMapping
	public String guardarPedido(Pedidos pedido) {

		pedidoService.guardar(pedido);

		return "redirect:/web/pedidos";
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
	}

	@GetMapping("/{id}/editar")
	public String editar(@PathVariable Integer id, Model model) {
		Optional<Pedidos> pedido = pedidoService.buscarPorId(id);

<<<<<<< HEAD
		if (pedido != null && pedido.isPresent()) {
			model.addAttribute("pedido", pedido.get());
			// model.addAttribute("usuarios", usuarioService.obtenerTodos());
=======
		if (pedido != null && !pedido.isEmpty()) {
			model.addAttribute("pedido", pedido.get());
			model.addAttribute("usuarios", usuarioService.obtenerTodos());
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
			return "pedidos/formulario";
		}
		return "redirect:/web/pedidos";
	}

}
