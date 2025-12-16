package com.restaurant.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurant.model.Producto;
import com.restaurant.service.CategoriaService;
import com.restaurant.service.ProductoService;
import com.restaurant.service.RestaurantService;

@Controller
@RequestMapping("/web/productos")
public class ProductoWebController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public String listarProductos(Model model) {
		model.addAttribute("productos", productoService.obtenerTodos());
		return "productos/lista";
	}

<<<<<<< HEAD
	@GetMapping("/restaurant={id}")
	public String listarProductosPorRestaurante(@PathVariable Integer id, Model model) {
		model.addAttribute("productos", productoService.buscarPorRestaurant(id));
		model.addAttribute("restaurants", restaurantService.obtenerPorId(id));
		return "productos/productosRestaurant";
	}

=======
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
	@GetMapping("/nuevo")
	public String formularioNuevo(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("restaurantes", restaurantService.obtenerTodos());
		model.addAttribute("categorias", categoriaService.obtenerTodos());
		return "productos/formulario";
	}

	@GetMapping("/{id}/editar")
	public String formularioEditar(@PathVariable Integer id, Model model) {
		Optional<Producto> producto = productoService.obtenerPorId(id);
		if (producto.isPresent()) {
			model.addAttribute("producto", producto.get()); // 🛑 CORREGIDO: La variable es "producto"
			model.addAttribute("restaurantes", restaurantService.obtenerTodos());
			model.addAttribute("categorias", categoriaService.obtenerTodos());
			return "productos/formulario";
		}
		return "redirect:/web/productos";
	}

	@PostMapping
<<<<<<< HEAD
	public String guardarProducto(@ModelAttribute Producto producto, @RequestParam MultipartFile logoFile,
=======
	public String guardarProducto(@ModelAttribute Producto producto, @RequestParam("logoFile") MultipartFile logoFile,
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
			RedirectAttributes redirectAttributes) {
		if (!logoFile.isEmpty()) {
			try {
				producto.setLogo(logoFile.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("error", "Error al subir la imagen.");
				return "redirect:/web/productos";
			}
		} else if (producto.getId() != null) {
			// Conservar el logo existente si no se sube uno nuevo durante la edición
			productoService.obtenerPorId(producto.getId()).ifPresent(existingProduct -> {
				if (existingProduct.getLogo() != null) {
					producto.setLogo(existingProduct.getLogo());
				}
			});
		}
		productoService.guardar(producto);
		redirectAttributes.addFlashAttribute("success", "Producto guardado exitosamente.");
		return "redirect:/web/productos";
	}

	@GetMapping("/logo/{id}")
	public ResponseEntity<byte[]> obtenerLogo(@PathVariable Integer id) {
		Optional<Producto> optionalRest = productoService.obtenerPorId(id);

		if (optionalRest.isPresent() && optionalRest.get().getLogo() != null) {
			byte[] logo = optionalRest.get().getLogo();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			headers.setContentLength(logo.length);

			return new ResponseEntity<>(logo, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/eliminar")
	public String eliminarProducto(@PathVariable Integer id) {
		productoService.eliminar(id);
		return "redirect:/web/productos";

	}
}
